package com.spring.bookmanage.r3.KGBService;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bookmanage.common.AES256;
import com.spring.bookmanage.member.JGHmodel.MemberVO;
import com.spring.bookmanage.r3.KGBModel.InterKGBR3DAO;
import com.spring.bookmanage.r3.KGBModel.KGBBookVO;

@Service
public class KGBR3Service implements InterKGBR3Service{

	@Autowired
	InterKGBR3DAO r3DAO;
	
	@Autowired
	AES256 aes;
	
	@Override
	public List<MemberVO> findAllMemberBySearchWord(HashMap<String, String> paraMap) {
		
		List<MemberVO> memberList = r3DAO.findAllMemberBySearchWord(paraMap);
		
		return memberList;
		
	}// end of findAllMemberBySearchWord()--------------------------------------

	@Override
	public MemberVO findOneMemberBymemberid(String memberid) {
		
		MemberVO memberVO = r3DAO.findOneMemberBymemberid(memberid);
		
		try {
			memberVO.setEmail(aes.decrypt(memberVO.getEmail()) );
			memberVO.setPhone(aes.decrypt(memberVO.getPhone()) );
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
		}
		
		return memberVO;
	}// end of findOneMemberBymemberid()-----------------

	
	
	@Override
	public List<KGBBookVO> findAllBookBySearchWord(HashMap<String, String> paraMap) {
		
		List<KGBBookVO> bookList = r3DAO.findAllBookBySearchWord(paraMap);
		
		return bookList;
	}// end of findAllBookBySearchWord()----------------------------

	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	public int addAllRentalByIdAfterUpdate(HashMap<String, String> paraMap) throws Throwable {
		
		int n = 0;
		
		String reservebookid = r3DAO.findNoteqReservationByBookid(paraMap);
		
		if(reservebookid.length() > 0)
			r3DAO.deleteReservationByBookid(reservebookid);
		
		r3DAO.findAllOverdateByMeberid(paraMap);
		int n1 = r3DAO.updateAllBookByBookid(paraMap);
		int n2 = r3DAO.addAllRentalById(paraMap);
		r3DAO.findAllRentalByMemberid(paraMap);
		
		if(n1*n2 == 1) {
			n = 1;
		}
		
		return n;
	}// end of addAllRentalById()----------------------------------

	
	@Override
	public List<HashMap<String, String>> findAllRentalByCategory(HashMap<String, String> paraMap) {
		
		List<HashMap<String, String>> rentalList = r3DAO.findAllRentalByCategory(paraMap);
		
		return rentalList;
		
	}// end of findAllRentalByCategory()-----------------------

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	public int addReturnByBookid(HashMap<String, String> paraMap) throws Throwable{
		
		
		r3DAO.updateMemberByDeadeline(paraMap);
		
		String reserveBookid = r3DAO.findAllReservationByBookid(paraMap);
		
		r3DAO.updateBookstatusByBookid(paraMap, reserveBookid);
		r3DAO.insertReturnByRentalInfo(paraMap);
		r3DAO.deleteRentalByBookid(paraMap);
		
		return 1;
	}// end of addReturnByBookid()-----------------

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	public int updateRentalRenewByBookid(HashMap<String, String> paraMap) throws Throwable{
		
		r3DAO.findAllReservationCountByBookid(paraMap);
		r3DAO.UpdateAllRenewByBookid(paraMap);
		
		return 1;
		
	}// end of updateRentalByBookid()----------------

	
	@Override
	public List<HashMap<String, String>> findAllReserveRentalByCategory(HashMap<String, String> paraMap) {
		
		List<HashMap<String, String>> rentalList = r3DAO.findAllReserveRentalByCategory(paraMap);
		
		return rentalList;
		
	}// end of findAllRentalByCategory()-----------------------
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	public int insertReserveByRentalInfo(HashMap<String, String> paraMap) throws Throwable{
		
		r3DAO.findAllReservationCountByBookid(paraMap);
		r3DAO.insertReserveByRentalInfo(paraMap);
		r3DAO.findAllRentalByMemberid(paraMap);
		r3DAO.findAllOverdateByMeberid(paraMap);
		
		return 1;
		
	}// end of findAllReserveRentalByCategory()-------------------------------

	

	
	
	
	
}
