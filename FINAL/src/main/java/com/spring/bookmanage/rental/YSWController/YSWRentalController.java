package com.spring.bookmanage.rental.YSWController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.bookmanage.common.MyUtil;
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
		
		return "rental/rentalList.tiles1";
	}// End of public String rentalList(HttpServletRequest req, HttpServletResponse res) 
	
	
	// 대여 책자를 반환 시키고 대출 목록에서 삭제 시키기
	@RequestMapping(value="/returned.ana", method= {RequestMethod.POST})
	public String infoForReturned(HttpServletRequest req, HttpServletResponse res) {
		
		String idx = req.getParameter("idx");
		String bookid = req.getParameter("bookid");
		String memberid = req.getParameter("memberid");
		String rentaldate = req.getParameter("rentaldate");
		String deadline = req.getParameter("deadline");
		String delaydate = req.getParameter("delaydate");
		
		System.out.println("delaydate : " + delaydate);
				
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
		paraMap.put("deadline", deadline);
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
	
	
	// 대여 목록 검색
	@RequestMapping(value="/findRentalList.ana", method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> findRentalList(HttpServletRequest req, HttpServletResponse res) {
		
		List<HashMap<String, Object>> rentalList  = new ArrayList<HashMap<String, Object>>();
		String str_currentPage = req.getParameter("currentPage");
		String searchWord = req.getParameter("searchWord");
		String sort = req.getParameter("sort");
		int itemPerPage = 10;
		int currentPage = 0;
		int startNo = 0;
		int endNo = 0;
		
	
		if(str_currentPage == null) {
			currentPage = 1;
		}
		else {
			
			try {
				currentPage = Integer.parseInt(str_currentPage);
				
			} catch (NumberFormatException  e) {
				currentPage = 1;
			}
			
		}// end of if(str_currentPage == null)


		/*
		 	페이지 번호 	rno1	rno2
		 	==============================
		 	1 페이지		 1		 5	
		 	2 페이지		 6		 10
		 	3 페이지		 11		 15
		 */
		
		// 페이징 공식
		startNo = (currentPage*itemPerPage) - (itemPerPage - 1);
		endNo = (currentPage*itemPerPage);
		
		HashMap<String, Object> optionMap = new HashMap<String, Object>();
		List<YSWRentalVO> rentalVOList = null;
		
		if(searchWord != null && !searchWord.trim().equals("")) {
			optionMap.put("SEARCHWORD", searchWord);
			optionMap.put("SORT", sort);
			optionMap.put("STARTNO", startNo);
			optionMap.put("ENDNO", endNo);
			
			rentalVOList = service.findRentalListWith(optionMap);
		}
		else {
			
			optionMap.put("STARTNO", startNo);
			optionMap.put("ENDNO", endNo);
			
			rentalVOList = service.findRentalList(optionMap);
		}
		
		for(YSWRentalVO rntvo : rentalVOList) {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("IDX", rntvo.getIdx());
			map.put("BOOKID", rntvo.getBookId());
			map.put("MEMBERID", rntvo.getMemberId());
			map.put("RENTALDATE", rntvo.getRentalDate());
			map.put("DEADLINE", rntvo.getDeadline());
			
			if( 0 > Integer.parseInt(rntvo.getDelayDate()) ) {
				map.put("DELAYDATE", -Integer.parseInt((rntvo.getDelayDate()))+"일 남음" );
			}
			else {
				map.put("DELAYDATE", Integer.parseInt((rntvo.getDelayDate()))+"일 지남" );
			}
			map.put("RENEW", rntvo.getRenew());
			map.put("BOOKTITLE", rntvo.getBookTitle());
			map.put("PUBNAME", rntvo.getPubName());
			map.put("BOOKAUTHOR", rntvo.getBookAuthor());
			map.put("MEMBERNAME", rntvo.getMemberName());
			
			rentalList.add(map);
		}
		
		req.setAttribute("sort", sort);
		req.setAttribute("searchWord", searchWord);
		
		return rentalList;
	}
	
	
	// 대여 목록 페이지바
	@RequestMapping(value="/rentalPagebar.ana", method= {RequestMethod.GET})
	@ResponseBody
	public HashMap<String, Integer> rentalPagebar(HttpServletRequest req, HttpServletResponse res) {
		
		HashMap<String, Integer> returnMap = new HashMap<String, Integer>();
		
		String searchWord = req.getParameter("searchWord");
		String sort = req.getParameter("sort");
		int itemPerPage = 10;
		int totalCount = 0;
		int totalPage = 0;
		
		
		
		if(searchWord != null && !searchWord.trim().equalsIgnoreCase("")) {
			
			HashMap<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("SEARCHWORD", searchWord);
			paraMap.put("SORT", sort);
			
			totalCount = service.findTotalPageWith(paraMap);
		}
		else {
			totalCount = service.findTotalPage();
		}
		
		totalPage = (int)Math.ceil((double)totalCount/itemPerPage);
		
		returnMap.put("TOTALPAGE", totalPage);
		
		return returnMap;
	}
	

}
