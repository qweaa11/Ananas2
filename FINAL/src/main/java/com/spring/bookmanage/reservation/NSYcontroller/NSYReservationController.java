package com.spring.bookmanage.reservation.NSYcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.bookmanage.common.AES256;
import com.spring.bookmanage.reservation.NSYservice.NSYInterReservationService;

@Controller
public class NSYReservationController {
	
	@Autowired
	private NSYInterReservationService service;
	
	@Autowired
	private AES256 aes;
	
	@RequestMapping(value="reservationList.ana",method= {RequestMethod.GET})
	public String reservationList(HttpServletRequest req, HttpServletResponse res) {
		
		return "reservation/reservationList.tiles1";
	}
	
	@RequestMapping(value="getReservationList.ana",method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> getReservationList (HttpServletRequest req, HttpServletResponse res) {
		
		List<HashMap<String, Object>> resultMapList = new ArrayList<HashMap<String,Object>>();
		
		String colname = req.getParameter("colname");
		String searchWord = req.getParameter("searchWord");
		String currentShowPageNo = req.getParameter("currentShowPageNo");
		String sizePerPage = req.getParameter("sizePerPage");
		String sort = req.getParameter("sort");
		
		if(currentShowPageNo==null || "".equals(currentShowPageNo)) {
			currentShowPageNo= "1";
		}
		
		// 공식 !!!
		int rno1 = Integer.parseInt(currentShowPageNo) * Integer.parseInt(sizePerPage) - (Integer.parseInt(sizePerPage)-1); // 공식 !!!
		int rno2 = Integer.parseInt(currentShowPageNo) * Integer.parseInt(sizePerPage); 
		
		HashMap<String,String> paraMap = new HashMap<String,String>();
		
		paraMap.put("rno1", String.valueOf(rno1));
		paraMap.put("rno2", String.valueOf(rno2));
		paraMap.put("colname", colname);
		paraMap.put("searchWord", searchWord);
		paraMap.put("sort", sort);
		
		List<HashMap<String, Object>> reservationList = service.getReservationList(paraMap);
		
		for(HashMap<String, Object> reservationMap : reservationList) {
			
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			// A
			resultMap.put("idx", reservationMap.get("idx"));
			resultMap.put("reserveDate", reservationMap.get("reserveDate"));
			//resultMap.put("bookid_fk", reservationMap.get("bookid_fk"));
			//resultMap.put("memberid_fk", reservationMap.get("memberid_fk"));
			// B
			resultMap.put("memberid", reservationMap.get("memberid"));
			resultMap.put("name", reservationMap.get("name"));
			//resultMap.put("email", reservationMap.get("email"));
			resultMap.put("phone", reservationMap.get("phone"));
			resultMap.put("addr1", reservationMap.get("addr1"));
			resultMap.put("addr2", reservationMap.get("addr2"));
			resultMap.put("post", reservationMap.get("post"));
			//resultMap.put("birth", reservationMap.get("birth"));
			//resultMap.put("gender", reservationMap.get("gender"));
			// C
			resultMap.put("bookid", reservationMap.get("bookid"));
			resultMap.put("title", reservationMap.get("title"));
			resultMap.put("author", reservationMap.get("author"));
			//resultMap.put("pubcode_fk", reservationMap.get("pubcode_fk"));
			resultMap.put("status", reservationMap.get("status"));
			// D
			resultMap.put("pubcode", reservationMap.get("pubcode"));
			resultMap.put("pubname", reservationMap.get("pubname"));
			resultMap.put("rno", reservationMap.get("rno"));
			
			resultMapList.add(resultMap);
			
		}
		
		return resultMapList;
		
	}
	
	@RequestMapping(value="getMakeBarPage_ReservationList.ana", method= {RequestMethod.GET})
	@ResponseBody
	public HashMap<String, Integer> getMakeBarPage_ReservationList(HttpServletRequest req, HttpServletResponse res){
		
		HashMap<String, Integer> returnMap = new HashMap<String,Integer>();
		
		String colname = req.getParameter("colname");
		String searchWord = req.getParameter("searchWord");
		String sizePerPage = req.getParameter("sizePerPage");
		String currentShowPageNo = req.getParameter("currentShowPageNo");
		
		if(currentShowPageNo==null || "".equals(currentShowPageNo)) {
			currentShowPageNo= "1";
		}
		
		HashMap<String,String > paraMap = new HashMap<String,String>();
		paraMap.put("sizePerPage", sizePerPage);
		paraMap.put("colname", colname);
		paraMap.put("searchWord", searchWord);
		
		// ==== 도서관 전체 갯수를 구해오기 ====
		int totalCount = service.getReservationTotalList(paraMap);
		
		int totalPage = (int)Math.ceil((double)totalCount/Integer.parseInt(sizePerPage));
		
		returnMap.put("totalPage", totalPage);
		
		return returnMap;
	} 
	
}// end of class NSYReservationController
