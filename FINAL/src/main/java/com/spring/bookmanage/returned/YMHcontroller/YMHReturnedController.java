package com.spring.bookmanage.returned.YMHcontroller;


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

import com.spring.bookmanage.returned.YMHmodel.ReturnedVO;
import com.spring.bookmanage.returned.YMHservice.InterYMHReturnedService;

@Controller
@Component
public class YMHReturnedController 
{
	@Autowired
	private InterYMHReturnedService service;
	
	// 도서 반납 목록 창 요청
	@RequestMapping(value="/bookReturned.ana",method= {RequestMethod.GET})
	public String bookReturned()
	{
		return "returned/bookReturned.tiles1";
	}
	
	
	
	// 반납된 도서목록 가져오기(페이징처리)
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
		
		int sizePerPage = 20;
		
		int rno1 = Integer.parseInt(currentShowPageNo) * sizePerPage - (sizePerPage-1);
		int rno2 = Integer.parseInt(currentShowPageNo) * sizePerPage;
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("RNO1", String.valueOf(rno1));
		paraMap.put("RNO2", String.valueOf(rno2));
		
		List<ReturnedVO> returnedList = service.listReturned(paraMap);
		
		for(ReturnedVO rvo : returnedList)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("idx", rvo.getIdx());
			map.put("memberID", rvo.getMemberID());
			map.put("name", rvo.getName());
			map.put("phone", rvo.getPhone());
			map.put("title", rvo.getTitle());
			map.put("author", rvo.getAuthor());
			map.put("ageCode", rvo.getAgeCode());
			map.put("returnDate", rvo.getReturnDate());
			map.put("rentalDate", rvo.getRentalDate());
			
			mapList.add(map);
		}
		
		return mapList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
