package com.spring.bookmanage.rental.YSWController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.bookmanage.rental.YSWService.InterYSWRentalService;
import com.spring.bookmanage.rental.YSWmodel.YSWRentalVO;

@Controller
@Component
public class YSWRentalController {
	
	@Autowired
	private InterYSWRentalService service;
	
	
	// 대여 목록 페이지 보여주기
	@RequestMapping(value="/rentalList.ana", method= {RequestMethod.GET})
	public String rentalList(HttpServletRequest req, HttpServletResponse res) {
		
		List<HashMap<String, String>> List = new ArrayList<HashMap<String, String>>();
		List<YSWRentalVO> rentalList = service.findRentalList();
				
		for(YSWRentalVO rentalvo : rentalList) {
			
			HashMap<String, String> rentalMap = new HashMap<String, String>();
			
			rentalMap.put("IDX", rentalvo.getIdx());
			rentalMap.put("BOOKID", rentalvo.getBookid());
			rentalMap.put("MEMBERID", rentalvo.getMemberid());
			rentalMap.put("RENTALDATE", rentalvo.getRentalDate());
			rentalMap.put("DEADLINE", rentalvo.getDeadline());
			rentalMap.put("DELAYDATE", rentalvo.getDelayDate());
			rentalMap.put("RENEW", rentalvo.getRenew());
			rentalMap.put("BOOKTITLE", rentalvo.getBookTitle());
			rentalMap.put("PUBNAME", rentalvo.getPubname());
			rentalMap.put("BOOKAUTHOR", rentalvo.getBookAuthor());
			rentalMap.put("MEMBERNAME", rentalvo.getMemberName());
			
			List.add(rentalMap);
		}
		
		req.setAttribute("LIST", List);
		
		return "rental/rentalList.tiles1";
	}// End of public String rentalList(HttpServletRequest req, HttpServletResponse res) 
	
	
	// 대여 책자를 반환 시키고 대출 목록에서 삭제 시키기
	@RequestMapping(value="/returned.ana", method= {RequestMethod.POST})
	public String infoForReturned(HttpServletRequest req, HttpServletResponse res) {
		
		String idx = req.getParameter("idx");
		String bookid = req.getParameter("bookid");
		String memberid = req.getParameter("memberid");
		String rentaldate = req.getParameter("rentaldate");
		String delaydate = req.getParameter("delaydate");
				
/*		
  		System.out.println("idx : " + idx);
		System.out.println("bookid : " + bookid);
		System.out.println("memberid : " + memberid);
*/
		
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("idx", idx);
		paraMap.put("bookid", bookid);
		paraMap.put("memberid", memberid);
		paraMap.put("rentaldate", rentaldate);
		paraMap.put("delaydate", delaydate);
		
		int result = service.returnAndDelete(paraMap);
		
		if(result == 2) {
			
			String msg = "책을 반납하셨습니다."; 
			String loc = "bookReturned.ana";
			
			req.setAttribute("msg", msg);
			req.setAttribute("loc", loc);
		}
		else {
			
			String msg = "책 반납을 실패했습니다."; 
			String loc = "javascript:history.back();";
			
			req.setAttribute("msg", msg);
			req.setAttribute("loc", loc);
		}

		return "msg";
	}// end of public String infoForReturned(HttpServletRequest req, HttpServletResponse res)
	
	
	// 반납 기간을 연장 시키기
	@RequestMapping(value="/extend.ana", method= {RequestMethod.POST})
	public String extendBook(HttpServletRequest req, HttpServletResponse res) {
		
		String idx = req.getParameter("idx");
		
		
		System.out.println("idx : " + idx);
		
		
		String[] idxList = idx.split(",");
		
		for(int i=0; i<idxList.length; i++) {
			
			System.out.println(idxList[i]);
		}
		
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("IDXLIST", idxList);
		
		int result = service.extendBook(paraMap);
		
		if(result == 1) {
			
			String msg = "책 대여기간을 연장하셨습니다."; 
			String loc = "rentalList.ana";
			
			req.setAttribute("msg", msg);
			req.setAttribute("loc", loc);
		}
		else {
			
			String msg = "대여기간 연장에 실패하셨습니다."; 
			String loc = "javascript:history.back();";
			
			req.setAttribute("msg", msg);
			req.setAttribute("loc", loc);
		}
		
		return "msg";
	}// end of public String extendBook(HttpServletRequest req, HttpServletResponse res)
	

}
