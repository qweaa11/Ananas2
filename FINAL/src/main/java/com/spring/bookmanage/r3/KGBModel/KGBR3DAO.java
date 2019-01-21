package com.spring.bookmanage.r3.KGBModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bookmanage.member.JGHmodel.MemberVO;

@Repository
public class KGBR3DAO implements InterKGBR3DAO {
	
	@Autowired
	SqlSessionTemplate sqlsession;

	@Override
	public List<MemberVO> findAllMemberBySearchWord(HashMap<String, String> paraMap) {
		
		List<MemberVO> memberList = sqlsession.selectList("kgb.findAllMemberBySearchWord", paraMap);
		
		return memberList;
	}// end of findAllMemberBySearchWord()--------------------------------

	@Override
	public MemberVO findOneMemberBymemberid(String memberid) {
		
		MemberVO memberVO = sqlsession.selectOne("kgb.findOneMemberBymemberid", memberid);
		
		return memberVO;
	}// findOneMemberBymemberid()--------------------

	
	@Override
	public List<KGBBookVO> findAllBookBySearchWord(HashMap<String, String> paraMap) {
		
		List<KGBBookVO> bookList = sqlsession.selectList("kgb.findAllBookBySearchWord", paraMap);
		
		return bookList;
	}// end of findAllBookBySearchWord()-------------------

	
	@Override
	public void findAllRentalByMemberid(HashMap<String, String> paraMap) throws Throwable{
		
		String limitBook = "";
		
		String[] memberidArr = paraMap.get("MEMBERIDS").split(",");
		
		for(String memberid : memberidArr) {
			int rentalCount = sqlsession.selectOne("kgb.findAllRentalByMemberid", memberid);
			
			if(rentalCount > 5) {
				limitBook += memberid + ",";
			}
		}// end of for-------------------------
		
		if(limitBook.length() > 0)
			limitBook = limitBook.substring(0, limitBook.length()-1);
		
		String[] MemberidArr = limitBook.split(",");	// 권수를 초과한 멤버의 아이디
		
		List<String> limitMemberidArr = new ArrayList<String>(); // 중복검사되어 통과해 담기 회원 아이디
		
		if(limitBook.length() > 0)
			for(String memberid : MemberidArr) {
				
				int count = 0;
				
				for(String limitMemberid : limitMemberidArr) {
					if(limitMemberid != null && limitMemberid.equals(memberid))
						count ++;
				}// end of for 내부 ------ 
				
				if(count == 0) {
					limitMemberidArr.add(memberid);
				}
				
			}// end of for 외부 -----------------------
		
		limitBook = "";
		
		for(String limitMemberid : limitMemberidArr) {
			
			limitBook += limitMemberid + ",";
			
		}// end of for 내부 ------
		
		if(limitBook.length() > 0) {
			limitBook = limitBook.substring(0, limitBook.length()-1);
			
			Throwable ex = new Throwable(limitBook + " 회원님의 최대 대여가능 권수를 초과합니다.");
			
			throw ex;
		}
		
	}// end of findAllRentalByMemberid()-------------------------
	
	
	@Override
	public int addAllRentalById(HashMap<String, String> paraMap) throws Throwable{
		
		String[] memberidArr = paraMap.get("MEMBERIDS").split(",");
		String[] bookidArr = paraMap.get("BOOKIDS").split(",");
		String[] nameArr = paraMap.get("NAMES").split(",");
		String[] deadlineArr = paraMap.get("DEADLINES").split(",");
		
		int result = 0;
		
		for(int i=0; i < memberidArr.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("MEMBERID", memberidArr[i]);
			map.put("BOOKID", bookidArr[i]);
			map.put("NAME", nameArr[i]);
			map.put("DEADLINE", deadlineArr[i]);
			
			int n = sqlsession.insert("kgb.addAllRentalById", map);
			
			result += n;
			
			
			
		}// end of for()------------------
		
		if(result != memberidArr.length) {
			Throwable ex = new Throwable("대여중 오류가 발생했습니다."); 
			
			throw ex;
		}
		
		return 1;
	}// end of addAllRentalById()--------------------

	
	@Override
	public int updateAllBookByBookid(HashMap<String, String> paraMap)  throws Throwable{
		
		int result = 0;
		
		String[] bookidArr = paraMap.get("BOOKIDS").split(",");
		
		for(String bookid : bookidArr) {
			int n = sqlsession.update("kgb.updateAllBookByBookid", bookid);
			
			if(n != 0) {
				result += n;
			}
		}
		
		if(result != bookidArr.length) {
			Throwable ex = new Throwable("이미 대여 되어있는 책 입니다."); 
			
			throw ex;
		}
		
		return 1;
	}// end of updateAllBookByBookid()-----------------------------

	
	@Override
	public String findAllReservationByBookid(HashMap<String, String> paraMap) throws Throwable{
		
		String reservebookid = "";
		
		String[] memberidArr = paraMap.get("MEMBERIDS").split(",");
		String[] bookidArr = paraMap.get("BOOKIDS").split(",");
		
		for(int i=0; i < memberidArr.length; i++ ) {
			
			String memberid = sqlsession.selectOne("kgb.findAllReservationByBookid", bookidArr[i]);
			
			if(memberid != null && !memberid.equals(memberidArr[i])) {
				Throwable ex = new Throwable(memberid + "회원에게 예약되어있는 책입니다."); 
				
				throw ex;
			}
			
			if(memberid != null && memberid.equals(memberidArr[i])) {
				reservebookid += bookidArr[i] + ",";
			}
			
		}// end of for-----------------
		
		if(reservebookid.length() > 0)
			reservebookid = reservebookid.substring(0, reservebookid.length()-1);
		
		return reservebookid;
		
	}// end of findAllReservationByBookid()---------------------------

	
	@Override
	public void deleteReservationByMemberid(String reservebookid) throws Throwable {
		
		int n = sqlsession.delete("kgb.deleteReservationByMemberid", reservebookid);
		System.out.println("asd");
		if(n == 0) {
			Throwable ex = new Throwable("대여중 오류가 발생했습니다."); 
			
			throw ex;
		}
		
	}// end of deleteReservationByMemberid()-----------------------

	
}
