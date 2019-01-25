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
			
			Throwable ex = new Throwable(limitBook + " 회원님의 최대 대여/예약 가능 권수를 초과합니다.");
			
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
	public String findNoteqReservationByBookid(HashMap<String, String> paraMap) throws Throwable{
		
		String reservebookid = "";
		
		String[] memberidArr = paraMap.get("MEMBERIDS").split(",");
		String[] bookidArr = paraMap.get("BOOKIDS").split(",");
		
		for(int i=0; i < memberidArr.length; i++ ) {
			
			String memberid = sqlsession.selectOne("kgb.findNoteqReservationByBookid", bookidArr[i]);
			
			if(memberid != null && !memberid.equals(memberidArr[i])) {
				Throwable ex = new Throwable(bookidArr[i] + " 은" + memberid + "회원에게 예약되어있는 책입니다."); 
				
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
	public String findAllReservationByBookid(HashMap<String, String> paraMap){
		
		String reservebookid = "";
		
		String[] memberidArr = paraMap.get("MEMBERIDS").split(",");
		String[] bookidArr = paraMap.get("BOOKIDS").split(",");
		
		for(int i=0; i < memberidArr.length; i++ ) {
			
			int count = sqlsession.selectOne("kgb.findAllReservationByBookid", bookidArr[i]);
			
			if(count > 0) {
				reservebookid += bookidArr[i] + ",";
			}
			
			
		}// end of for-----------------
		
		if(reservebookid.length() > 0)
			reservebookid = reservebookid.substring(0, reservebookid.length()-1);
		
		return reservebookid;
		
	}// end of findAllReservationByBookid()-------------------------------

	
	@Override
	public void deleteReservationByBookid(String reservebookid) throws Throwable {
		
		String[] bookidArr = reservebookid.split(",");
		
		int n = 0;
		
		for(String bookid : bookidArr) {
			n = sqlsession.delete("kgb.deleteReservationByBookid", bookid);
			
			if(n == 0) {
				Throwable ex = new Throwable("대여중 오류가 발생했습니다.");
				
				throw ex;
			}
		}
		
	}// end of deleteReservationByMemberid()-----------------------

	
	@Override
	public void findAllOverdateByMeberid(HashMap<String, String> paraMap) throws Throwable{
		
		String[] memberidArr = paraMap.get("MEMBERIDS").split(",");
		
		for(String memberid : memberidArr) {
			int n = sqlsession.selectOne("kgb.findAllOverdateByMeberid", memberid);
			
			if(n > 0) {
				Throwable ex = new Throwable("연체되어 있는 책이 있어 예약/대여 가 불가능 합니다."); 
				
				throw ex;
			}
		}
		
	}// end of findAllOverdateByMeberid()--------------------------------
	
	
	@Override
	public List<HashMap<String, String>> findAllRentalByCategory(HashMap<String, String> paraMap) {
		
		List<HashMap<String, String>> rentalList = sqlsession.selectList("kgb.findAllRentalByCategory", paraMap);
		
		return rentalList;
		
	}// end of findAllRentalByCategory()-----------------------

	
	@Override
	public void updateMemberByDeadeline(HashMap<String, String> paraMap) throws Throwable {
		
		String[] memberidArr = paraMap.get("MEMBERIDS").split(",");
		String[] deadlinecutArr = paraMap.get("DEADLINECUTS").split(",");
		
		for(int i=0; i<memberidArr.length; i++) {
			if(Integer.parseInt(deadlinecutArr[i]) > 0) {
			
				HashMap<String, String> map = new HashMap<String, String>();
				
				map.put("MEMBERID", memberidArr[i]);
				map.put("DEADLINECUT", deadlinecutArr[i]);
				
				int n = sqlsession.update("kgb.updateMemberByDeadeline", map);
				
				if(n == 0) {
					Throwable ex = new Throwable("반납중 오류가 발생했습니다."); 
					
					throw ex;
				}
			}
		}// end of for----------------------
		
		
		
	}// end of updateMemberByDeadeline()------------------------------

	
	@Override
	public void updateBookstatusByBookid(HashMap<String, String> paraMap, String reserveBookid) throws Throwable{
		
		String[] bookidArr = paraMap.get("BOOKIDS").split(",");
		String[] reserveBookidArr = reserveBookid.split(",");
		
		boolean flag = false;
		
		for(String bookid : bookidArr) {
			
			flag = false;
			
			if(reserveBookidArr != null && reserveBookidArr.length != 0) {
				
				for(String reserve : reserveBookidArr) {
					if(reserve.equals(bookid))
						flag = true;
				}
				
			}// end of if------------------
			int n = 0;
			
			if(flag) {
				n = sqlsession.update("kgb.updateBookstatus2ByBookid", bookid);
			}
			else {
				n = sqlsession.update("kgb.updateBookstatus0ByBookid", bookid);
			}
				
			
			
			if(n == 0) {
				Throwable ex = new Throwable("반납중 오류가 발생했습니다."); 
				
				throw ex;
			}
		}// end of for----------------------
		
		
	}// end of updateBookstatusByBookid()------------------------------

	@Override
	public void insertReturnByRentalInfo(HashMap<String, String> paraMap) throws Throwable{
		
		String[] memberidArr = paraMap.get("MEMBERIDS").split(",");
		String[] bookidArr = paraMap.get("BOOKIDS").split(",");
		
		for(int i=0; i<memberidArr.length; i++) {
			
			HashMap<String, String> map = new HashMap<String, String>();
			
			map.put("MEMBERID", memberidArr[i]);
			map.put("BOOKID", bookidArr[i]);
			
			int n = sqlsession.update("kgb.insertReturnByRentalInfo", map);
			
			if(n == 0) {
				Throwable ex = new Throwable("반납중 오류가 발생했습니다."); 
				
				throw ex;
			}
			
		}// end of for----------------------
		
	}// end of insertReturnByRentalInfo()-----------------------

	@Override
	public void deleteRentalByBookid(HashMap<String, String> paraMap)  throws Throwable{
		
		String[] bookidArr = paraMap.get("BOOKIDS").split(",");
		
		for(String bookid : bookidArr) {
			
			int n = sqlsession.delete("kgb.deleteRentalByBookid", bookid);
			
			if(n == 0) {
				Throwable ex = new Throwable("반납중 오류가 발생했습니다."); 
				
				throw ex;
			}
			
		}// end of for----------------------
		
	}// end of deleteRentalByBookid()-------------------------

	@Override
	public void findAllReservationCountByBookid(HashMap<String, String> paraMap) throws Throwable{
		
		String[] bookidArr = paraMap.get("BOOKIDS").split(",");
		
		String result = "";
		
		for(String bookid : bookidArr) {
			String reserveName = sqlsession.selectOne("kgb.findAllReservationCountByBookid", bookid);
			
			if(reserveName != null && !reserveName.trim().equals("")) {
				result += reserveName + "(" + bookid + "),";
			}
			
		}// end of for--------------------------------
		
		if(result.length() > 0) {
			result = result.substring(0, result.length()-1);
			
			Throwable ex = new Throwable(result + " 책은 이미 예약이 있습니다."); 
			
			throw ex;
		}// end of if----------------------------------
		
	}// end of findAllReservationCountByBookid()-------------------

	
	@Override
	public void UpdateAllRenewByBookid(HashMap<String, String> paraMap) throws Throwable{
		
		String[] bookidArr = paraMap.get("BOOKIDS").split(",");
		
		for(String bookid : bookidArr) {
			
			int n = sqlsession.update("kgb.UpdateAllRenewByBookid", bookid);
			
			if(n == 0) {
				Throwable ex = new Throwable("연장중 오류가 발생했습니다."); 
				
				throw ex;
			}
			
		}// end of for--------------------------------
		
		
		
	}// end of UpdateAllRenewByBookid()--------------------------
	
	
	@Override
	public List<HashMap<String, String>> findAllReserveRentalByCategory(HashMap<String, String> paraMap) {
		
		List<HashMap<String, String>> rentalList = sqlsession.selectList("kgb.findAllReserveRentalByCategory", paraMap);
		
		return rentalList;
		
	}// end of findAllRentalByCategory()-----------------------
	
	
	@Override
	public void insertReserveByRentalInfo(HashMap<String, String> paraMap) throws Throwable{
		
		String[] bookidArr = paraMap.get("BOOKIDS").split(",");
		String[] memberidArr = paraMap.get("MEMBERIDS").split(",");
		String[] reservedateArr = paraMap.get("RESERVEDATES").split(",");
		
		for(int i=0; i < bookidArr.length; i++) {
			
			HashMap<String, String> map = new HashMap<String, String>();
			
			map.put("BOOKID", bookidArr[i]);
			map.put("MEMBERID", memberidArr[i]);
			map.put("RESERVEDATE", reservedateArr[i]);
			
			int n = sqlsession.update("kgb.insertReserveByRentalInfo", map);
			
			if(n == 0) {
				Throwable ex = new Throwable("예약중 오류가 발생했습니다."); 
				
				throw ex;
			}
			
		}// end of for--------------------------------
		
	}// end of insertReserveByRentalInfo()-----------------------------------

	

	

	
}
