package com.spring.bookmanage.returned.YMHcontroller;


import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.spring.bookmanage.common.AES256;
import com.spring.bookmanage.returned.YMHmodel.ReturnedVO;
import com.spring.bookmanage.returned.YMHservice.InterYMHReturnedService;

@Controller
@Component
public class YMHReturnedController 
{
	@Autowired
	private InterYMHReturnedService service;
	
	@Autowired
	private AES256 aes;
	
	
	// 도서 반납 목록 창 요청
	@RequestMapping(value="/bookReturned.ana",method= {RequestMethod.GET})
	public String bookReturned()
	{
		return "returned/bookReturned.tiles1";
	}// end of String bookReturned()------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------
	
	
	
	// 반납된 도서목록 가져오기(페이징처리, without search)
	@RequestMapping(value="/showReturned.ana",method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> showReturned(HttpServletRequest request, HttpServletResponse response)
	{
		List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String,Object>>();
		
		String currentShowPageNo = request.getParameter("currentShowPageNo");
		
		if(currentShowPageNo == null || "".equals(currentShowPageNo))
		{
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 10;
		int rno1 = Integer.parseInt(currentShowPageNo) * sizePerPage - (sizePerPage-1);
		int rno2 = Integer.parseInt(currentShowPageNo) * sizePerPage;
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("RNO1", String.valueOf(rno1));
		paraMap.put("RNO2", String.valueOf(rno2));
		
		List<ReturnedVO> returnedList = service.listReturned(paraMap); 
		
		for(ReturnedVO rvo : returnedList)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			try {
				map.put("idx", rvo.getIdx());
				map.put("memberID", rvo.getMemberID());
				map.put("name", rvo.getName());
				map.put("title", rvo.getTitle());
				map.put("author", rvo.getAuthor());
				map.put("ageCode", rvo.getAgeCode());
				map.put("returnDate", rvo.getReturnDate());
				map.put("rentalDate", rvo.getRentalDate());
				map.put("phone", aes.decrypt(rvo.getPhone()) );
			} catch (Exception e) {
				e.printStackTrace();
			} 
			mapList.add(map);
		}
		return mapList;
	}// end of List<HashMap<String, Object>> showReturned(HttpServletRequest request, HttpServletResponse response)------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------
	
	// returned 테이블 totalpage 가져오기
	@RequestMapping(value="/getReturnedTotalPage.ana",method= {RequestMethod.GET})
	@ResponseBody
	public HashMap<String, Integer> getReturnedTotalPage(HttpServletRequest request, HttpServletResponse response)
	{
		HashMap<String, Integer> returnMap = new HashMap<String, Integer>();
		
		String sizePerPage = request.getParameter("sizePerPage");
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("SIZEPERPAGE", sizePerPage);
		
		int totalCount = service.getReturnedTotalCount(paraMap);
		
		int totalPage= (int)Math.ceil((double)totalCount/Integer.parseInt(sizePerPage));
		returnMap.put("TOTALPAGE", totalPage);
		
		return returnMap;
	}// end of String getReturnedTotalPage()------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------
	


	

	// 반납된 도서목록 가져오기(페이징처리, with search)
	@RequestMapping(value="/showReturnedSearch.ana",method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> showReturnedSearch(HttpServletRequest request, HttpServletResponse response)
	{
		List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String,Object>>();
		
		String currentShowPageNo = request.getParameter("currentShowPageNo");
		String colname = request.getParameter("COLNAME");
		String search = request.getParameter("SEARCH");
		
		if(currentShowPageNo == null || "".equals(currentShowPageNo))
		{
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 10;
		
		int rno1 = Integer.parseInt(currentShowPageNo) * sizePerPage - (sizePerPage-1);
		int rno2 = Integer.parseInt(currentShowPageNo) * sizePerPage;

		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("COLNAME", colname);
		paraMap.put("SEARCH", search);
		paraMap.put("RNO1", String.valueOf(rno1));
		paraMap.put("RNO2", String.valueOf(rno2));
		
		System.out.println("검색 COLNAME: "+colname);
		System.out.println("검색 SEARCH: "+search);
		
		List<ReturnedVO> returnedList = service.listReturnedWithSearch(paraMap);
		
		for(ReturnedVO rvo : returnedList)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			try {
				map.put("idx", rvo.getIdx());
				map.put("memberID", rvo.getMemberID());
				map.put("name", rvo.getName());
				map.put("title", rvo.getTitle());
				map.put("author", rvo.getAuthor());
				map.put("ageCode", rvo.getAgeCode());
				map.put("returnDate", rvo.getReturnDate());
				map.put("rentalDate", rvo.getRentalDate());
				map.put("phone", aes.decrypt(rvo.getPhone()) );
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			
			mapList.add(map);
		}
		
		return mapList;
	}// end of List<HashMap<String, Object>> showReturnedSearch(HttpServletRequest request, HttpServletResponse response)------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------
	
	// returned 테이블 totalpage 가져오기
	@RequestMapping(value="/getReturnedTotalPageWithSearch.ana",method= {RequestMethod.GET})
	@ResponseBody
	public HashMap<String, Integer> getReturnedTotalPageWithSearch(HttpServletRequest request, HttpServletResponse response)
	{
		HashMap<String, Integer> returnMap = new HashMap<String, Integer>();
		
		String sizePerPage = request.getParameter("sizePerPage");
		String colname = request.getParameter("COLNAME");
		String search = request.getParameter("SEARCH");
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("SIZEPERPAGE", sizePerPage);
		paraMap.put("COLNAME", colname);
		paraMap.put("SEARCH", search);
		
		int totalCount = service.getReturnedTotalCountWithSearch(paraMap);
		
		int totalPage= (int)Math.ceil((double)totalCount/Integer.parseInt(sizePerPage));
		returnMap.put("TOTALPAGE", totalPage);
		
		return returnMap;
	}// end of String getReturnedTotalPage()------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------
	








	
	
	
	
	
	
	
	
	
}
