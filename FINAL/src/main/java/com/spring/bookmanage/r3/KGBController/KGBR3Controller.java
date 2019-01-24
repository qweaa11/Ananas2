package com.spring.bookmanage.r3.KGBController;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.bookmanage.JDSmodel.LibrarianVO;
import com.spring.bookmanage.member.JGHmodel.MemberVO;
import com.spring.bookmanage.r3.KGBModel.KGBBookVO;
import com.spring.bookmanage.r3.KGBService.InterKGBR3Service;

@Controller
public class KGBR3Controller {
	
	@Autowired
	InterKGBR3Service r3service;

	@RequestMapping(value="/r3.ana", method= {RequestMethod.GET})
	public String r3Main(HttpServletRequest request, HttpServletResponse response) {
		
		String rentalBookid = request.getParameter("rentalBookid");
		String returnBookid = request.getParameter("returnBookid");
		
		request.setAttribute("rentalBookid", rentalBookid);
		request.setAttribute("returnBookid", returnBookid);
		
		return "r3/r3Main.tiles1";
		
	}// end of r3()--------------------------------
	
	
	@RequestMapping(value="/r3searchMember.ana", method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String,String>> r3searchMember(HttpServletRequest request, HttpServletResponse response) {
		
		String searchWord = request.getParameter("searchWord");
		String category = request.getParameter("cateogry");
		
		if(category == null) {
			category = "memberid";
		}
		
		if(searchWord == null) {
			searchWord = "";
		}
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("SEARCHWORD", searchWord);
		paraMap.put("CATEGORY", category);
		
		List<MemberVO> memberList = r3service.findAllMemberBySearchWord(paraMap);
		
		List<HashMap<String, String>> jsonList = new ArrayList<HashMap<String, String>>();
		
		for(MemberVO memberVO : memberList) {
			
			HashMap<String, String> json = new HashMap<String, String>();
			
			json.put("MEMBERID", memberVO.getMemberid());
			json.put("NAME", memberVO.getName());
			
			jsonList.add(json);
			
		}
		
		return jsonList;
		
	}// end of r3searchMember()------------------------------------
	
	@RequestMapping(value="/r3findOneMember.ana", method= {RequestMethod.GET})
	@ResponseBody 
	public HashMap<String, String> r3findOneMember(HttpServletRequest request, HttpServletResponse response) {
		
		String memberid = request.getParameter("memberid");
		
		MemberVO memberVO = r3service.findOneMemberBymemberid(memberid);
		
		HashMap<String, String> json = new HashMap<String, String>();
		
		json.put("MEMBERID", memberVO.getMemberid());
		json.put("NAME", memberVO.getName());
		json.put("AGES", memberVO.getAges());
		json.put("ADDR1", memberVO.getAddr1());
		json.put("ADDR2", memberVO.getAddr2());
		json.put("PHONE", memberVO.getPhone());
		
		return json;
		
	}// end of r3findOneMember()------------------
	
	@RequestMapping(value="/r3searchBook.ana", method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, String>> r3searchBook(HttpServletRequest request, HttpServletResponse response) {
		
		String searchWord = request.getParameter("searchWord");
		String category = request.getParameter("cateogry");
		
		if(category == null) {
			category = "bookid";
		}
		
		if(searchWord == null) {
			searchWord = "";
		}
		
		HttpSession session = request.getSession();
		LibrarianVO librarian = (LibrarianVO)session.getAttribute("loginLibrarian");
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		
		if(librarian != null)
			paraMap.put("LIBCODE", librarian.getLibcode_fk());
		paraMap.put("SEARCHWORD", searchWord);
		paraMap.put("CATEGORY", category);
		
		List<KGBBookVO> bookList = r3service.findAllBookBySearchWord(paraMap);
		
		List<HashMap<String, String>> jsonList = new ArrayList<HashMap<String, String>>();
		
		for(KGBBookVO bookVO : bookList) {
			
			HashMap<String, String> json = new HashMap<String, String>();
			
			json.put("BOOKID", bookVO.getBookid());
			json.put("TITLE", bookVO.getTitle());
			
			jsonList.add(json);
			
		}// end of for-------------------
		
		return jsonList;
		
	}// end of r3searchBook()-----------------------------
	
	
	@RequestMapping(value="/rentalInsert.ana", method= {RequestMethod.POST})
	@ResponseBody
	public HashMap<String, String> rentalInsert(HttpServletRequest request, HttpServletResponse response) {
		
		String memberids = request.getParameter("memberids");
		String bookids = request.getParameter("bookids");
		String names = request.getParameter("names");
		String deadlines = request.getParameter("deadlines");
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("MEMBERIDS", memberids);
		paraMap.put("BOOKIDS", bookids);
		paraMap.put("NAMES", names);
		paraMap.put("DEADLINES", deadlines);
		
		String msg = "";
		int n = 0;
		
		// 대여 테이블에 insert
		try {
			n = r3service.addAllRentalByIdAfterUpdate(paraMap);
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			msg = "이미 대여된 책입니다.";
			n = 0;
			
		} catch (Throwable e) {
			msg = e.getMessage();
			n = 0;
		} 
		
		HashMap<String, String> json = new HashMap<String, String>();
		
		json.put("MSG", msg);
		json.put("RESULT", String.valueOf(n));
		
		return json;
		
	}// end of rentalInsert()------------------------
	
	
	
	@RequestMapping(value="/r3searchRental.ana", method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, String>> searchRental(HttpServletRequest request, HttpServletResponse response) {
		
		String category = request.getParameter("category");
		String searchWord = request.getParameter("searchWord");
		String sort = request.getParameter("sort");
		
		HttpSession session = request.getSession();
		LibrarianVO librarian = (LibrarianVO)session.getAttribute("loginLibrarian");
			
		HashMap<String, String> paraMap = new HashMap<String, String>(); 
		if(librarian != null)
			paraMap.put("LIBCODE", librarian.getLibcode_fk());
		paraMap.put("CATEGORY", category);
		paraMap.put("SEARCHWORD", searchWord);
		paraMap.put("SORT", sort);
		
		List<HashMap<String, String>> rentalList = r3service.findAllRentalByCategory(paraMap);
		
		List<HashMap<String, String>> jsonList = new ArrayList<HashMap<String, String>>();
		
		for(HashMap<String, String> rental : rentalList) {
			HashMap<String, String> json = new HashMap<String, String>();
			
			json.put("BOOKID", rental.get("BOOKID"));
			json.put("NAME", rental.get("NAME"));
			json.put("TITLE", rental.get("TITLE"));
			json.put("MEMBERID", rental.get("MEMBERID"));
			json.put("RENTALDATE", rental.get("RENTALDATE"));
			json.put("DEADLINE", rental.get("DEADLINE"));
			json.put("RENEW", rental.get("RENEW"));
			json.put("DEADLINECUT", rental.get("DEADLINECUT"));
			
			jsonList.add(json);
		}
		
		return jsonList;
		
	}// end of searchRental()-------------------
	
	
	@RequestMapping(value="/r3bookReturn.ana", method= {RequestMethod.POST})
	@ResponseBody
	public HashMap<String, String> r3bookReturn(HttpServletRequest request, HttpServletResponse response) {
		
		String bookids = request.getParameter("bookids");
		String memberids = request.getParameter("memberids");
		String deadlinecuts = request.getParameter("deadlinecuts");
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("DEADLINECUTS", deadlinecuts);
		paraMap.put("MEMBERIDS", memberids);
		paraMap.put("BOOKIDS", bookids);
		
		int n = 0;
		String msg = "";
		
		try {
			n = r3service.addReturnByBookid(paraMap);
			
		} catch (Throwable e) {
			msg = e.getMessage();
		}
		
		
		HashMap<String, String> json = new HashMap<String, String>();
		
		json.put("RESULT", String.valueOf(n));
		json.put("MSG", msg);
		
		return json;
		
	}// end of bookReturn()---------------
	
	
	@RequestMapping(value="/r3bookRenew.ana", method= {RequestMethod.POST})
	@ResponseBody
	public HashMap<String, String> r3bookRenew(HttpServletRequest request, HttpServletResponse response) {
		
		String bookids = request.getParameter("bookids");
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("BOOKIDS", bookids);
		
		int n = 0;
		String msg = "";
		
		try {
			n = r3service.updateRentalRenewByBookid(paraMap);
		} catch (Throwable e) {
			msg = e.getMessage();
		}
		
		
		HashMap<String, String> json = new HashMap<String, String>();
		
		json.put("RESULT", String.valueOf(n));
		json.put("MSG", msg);
		
		return json;
		
	}// end of r3bookReservation()-------------------------------
	
	
	@RequestMapping(value="/r3searchReserveRental.ana", method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, String>> r3searchReserveRental(HttpServletRequest request, HttpServletResponse response) {
		
		String category = request.getParameter("category");
		String searchWord = request.getParameter("searchWord");
		String sort = request.getParameter("sort");
		
		HttpSession session = request.getSession();
		LibrarianVO librarian = (LibrarianVO)session.getAttribute("loginLibrarian");
			
		HashMap<String, String> paraMap = new HashMap<String, String>(); 
		if(librarian != null)
			paraMap.put("LIBCODE", librarian.getLibcode_fk());
		paraMap.put("CATEGORY", category);
		paraMap.put("SEARCHWORD", searchWord);
		paraMap.put("SORT", sort);
		
		List<HashMap<String, String>> rentalList = r3service.findAllReserveRentalByCategory(paraMap);
		
		List<HashMap<String, String>> jsonList = new ArrayList<HashMap<String, String>>();
		
		for(HashMap<String, String> rental : rentalList) {
			HashMap<String, String> json = new HashMap<String, String>();
			
			json.put("BOOKID", rental.get("BOOKID"));
			json.put("NAME", rental.get("NAME"));
			json.put("TITLE", rental.get("TITLE"));
			json.put("MEMBERID", rental.get("MEMBERID"));
			json.put("RENTALDATE", rental.get("RENTALDATE"));
			json.put("DEADLINE", rental.get("DEADLINE"));
			json.put("RENEW", rental.get("RENEW"));
			json.put("DEADLINECUT", rental.get("DEADLINECUT"));
			
			jsonList.add(json);
		}
		
		return jsonList;
		
	}// end of r3searchReserveRental()-------------------
	
	
	
	@RequestMapping(value="r3bookReservation.ana", method= {RequestMethod.POST})
	@ResponseBody
	public HashMap<String, String> r3bookReservation(HttpServletRequest request, HttpServletResponse response) {
		
		String bookids = request.getParameter("bookids");
		String memberids = request.getParameter("memberids");
		String reservedates = request.getParameter("reservedates");
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("BOOKIDS", bookids);
		paraMap.put("MEMBERIDS", memberids);
		paraMap.put("RESERVEDATES", reservedates);
		
		int n = 0;
		String msg = "";
		
		try {
			n = r3service.insertReserveByRentalInfo(paraMap);
		} catch (Throwable e) {
			msg = e.getMessage();
		}
		
		
		HashMap<String, String> json = new HashMap<String, String>();
		
		json.put("RESULT", String.valueOf(n));
		json.put("MSG", msg);
		
		return json;
		
	}// end of r3bookReservation()------------------------------------------------
	
}
