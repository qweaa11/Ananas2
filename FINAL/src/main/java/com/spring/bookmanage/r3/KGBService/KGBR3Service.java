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
		
		String reservebookid = r3DAO.findAllReservationByBookid(paraMap);
		System.out.println(reservebookid);
		if(reservebookid.length() > 0)
			r3DAO.deleteReservationByMemberid(reservebookid);
		
		int n1 = r3DAO.updateAllBookByBookid(paraMap);
		int n2 = r3DAO.addAllRentalById(paraMap);
		r3DAO.findAllRentalByMemberid(paraMap);
		
		if(n1*n2 == 1) {
			n = 1;
		}
		
		return n;
	}// end of addAllRentalById()----------------------------------

	

	
	
	
	
}
