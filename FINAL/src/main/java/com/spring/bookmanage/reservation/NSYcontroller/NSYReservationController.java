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
	
	@RequestMapping(value="rental.ana", method= {RequestMethod.POST})
	public String rental(HttpServletRequest req, HttpServletResponse res) {
		
		String[] rentalValue = req.getParameterValues("rentalValue");
		//System.out.println("체크박스값 확인하기 : "+ rentalValue[0]);
		
		for(int i=0; i<rentalValue.length; i++) {
			//System.out.println("체크박스값 확인하기 : "+ rentalValue[i]);
			String[] value = rentalValue[i].split(",");
			String bookid = value[0];
			String memberid = value[1];
			
			//System.out.println("value값:"+value);
			System.out.println("value1값:"+bookid);
			System.out.println("value2값:"+memberid);
			
			HashMap<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("bookid", bookid);
			paraMap.put("memberid", memberid);
			
			System.out.println("value1값 2번째:"+paraMap.get("bookid"));
			System.out.println("value2값 2번쨰:"+paraMap.get("memberid"));
			
			int result = service.reservation_rental(paraMap);
			
			if(result ==1) {
				service.changBookStatus(paraMap);
				
				service.deleteReservation(paraMap);
				
				String msg ="대여가 완료되었습니다.";
				String loc ="";
			}
			else {
				String msg ="대여 실패. 관리자에게 문의하세요";
				String loc ="";
			}//end of if_else
			
		}// end of for문
		
		return "reservation/reservationList.tiles1";
	}// end of String rental()
	
}// end of class NSYReservationController
