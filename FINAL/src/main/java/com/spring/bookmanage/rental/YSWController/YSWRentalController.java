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
	
	
	// 대출 목록 페이지 보여주기
	@RequestMapping(value="/rentalList.ana", method= {RequestMethod.GET})
	public String rentalList(HttpServletRequest req, HttpServletResponse res) {
		
		List<HashMap<String, String>> List = new ArrayList<HashMap<String, String>>();
		List<YSWRentalVO> rentalList = service.findRentalList();
				
		for(YSWRentalVO rentalvo : rentalList) {
			
			HashMap<String, String> rentalMap = new HashMap<String, String>();
			
			rentalMap.put("IDX", rentalvo.getIdx());
			rentalMap.put("BOOKID", rentalvo.getBookid());
			rentalMap.put("MEMBERID", rentalvo.getMemberid());
			rentalMap.put("RETALDATE", rentalvo.getRetalDate());
			rentalMap.put("DEADLINE", rentalvo.getDeadline());
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

}
