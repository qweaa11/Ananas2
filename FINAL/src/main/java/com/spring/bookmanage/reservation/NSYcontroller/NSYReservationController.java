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
	
	// 예약목록 페이지 요청하기
	@RequestMapping(value="reservationList.ana",method= {RequestMethod.GET})
	public String reservationList(HttpServletRequest req, HttpServletResponse res) {
		
		return "reservation/reservationList.tiles1";
	}//end of String reservationList()
	
	// 예역목록 리스트 가져오기 
	@RequestMapping(value="getReservationList.ana",method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> getReservationList (HttpServletRequest req, HttpServletResponse res) {
		
		List<HashMap<String, Object>> resultMapList = new ArrayList<HashMap<String,Object>>();
		
		String colname = req.getParameter("colname");
		String searchWord = req.getParameter("searchWord");
		String currentShowPageNo = req.getParameter("currentShowPageNo");
		String sizePerPage = req.getParameter("sizePerPage");
		
		// 현재 보여지는 페이지번호 지정하기!
		if(currentShowPageNo==null || "".equals(currentShowPageNo)) {
			currentShowPageNo= "1";
		}//end of if문
		
		// 공식 !!!
		int rno1 = Integer.parseInt(currentShowPageNo) * Integer.parseInt(sizePerPage) - (Integer.parseInt(sizePerPage)-1); // 공식 !!!
		int rno2 = Integer.parseInt(currentShowPageNo) * Integer.parseInt(sizePerPage); 
		
		// 파라미터값 생성하기
		HashMap<String,String> paraMap = new HashMap<String,String>();
		
		paraMap.put("rno1", String.valueOf(rno1));
		paraMap.put("rno2", String.valueOf(rno2));
		paraMap.put("colname", colname);
		paraMap.put("searchWord", searchWord);
		
		
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
			
		}// end of for문
		
		return resultMapList;
		
	}// end of List<HashMap<String, Object>> getReservationList()
	
	// 예약목록 페이지바 만들기 
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
		}// end of if문
		
		HashMap<String,String > paraMap = new HashMap<String,String>();
		paraMap.put("sizePerPage", sizePerPage);
		paraMap.put("colname", colname);
		paraMap.put("searchWord", searchWord);
		
		// 예약된 도서 전체 갯수 구해오기 
		int totalCount = service.getReservationTotalList(paraMap);
		
		// 공식!
		int totalPage = (int)Math.ceil((double)totalCount/Integer.parseInt(sizePerPage));
		
		returnMap.put("totalPage", totalPage);
		
		return returnMap;
	}// end of HashMap<String, Integer> getMakeBarPage_ReservationList
	
	// 예약목록에서 예약되어진 도서를 대여도서로 변경하기
	@RequestMapping(value="rental.ana", method= {RequestMethod.POST})
	public String rental(HttpServletRequest req, HttpServletResponse res) {
		
		// 목록에서 체크되어진 값을 불러와 선언한다.( 체크항목이 여러개일수있기 때문에 배열로 값을 선언한다)
		String[] rentalValue = req.getParameterValues("rentalValue");
		
		// 메세지, 위치 선언해주기
		String msg ="";
		String loc ="";
		
		// 체크된어진 값이 있다면
		if(rentalValue != null) {
			
			for(int i=0; i<rentalValue.length; i++) {
				
				// 배열로 저장된 rentalValue을 ','구분하여  i++ 순서로 선언하기
				String[] value = rentalValue[i].split(",");
				
				// rentalValue구분되어진 value값의 순서를 맞춰 bookid와 memberid로 선언
				String bookid = value[0];
				String memberid = value[1];
				
				// 파라미터값 생성하기!
				HashMap<String, String> paraMap = new HashMap<String, String>();
				paraMap.put("bookid", bookid);
				paraMap.put("memberid", memberid);
				
				// 예약목록의 도서를 대여목록에 추가하기
				int result = service.reservation_rental(paraMap);
				
				// 결과가 성공이라면 
				if(result ==1) {
					// book table의 status값 변경 ( =>대여값으로)
					service.changBookStatus(paraMap);
					
					// 예약모록의 도서가 대여가 되어지면 예약목록에서 삭제
					service.deleteReservation(paraMap);
					
					msg ="대여가 완료되었습니다.";
					loc ="reservationList.ana";
				}
				// 결과가 실패라면
				else {
					msg ="대여가 완료되지 않았습니다. 관리자에게 문의하세요";
					loc ="reservationList.ana";
				}//end of if_else
				
			}// end of for문
			
		}// if
		// 체크되어진 값이 없다면
		else {
			msg="체크된항목이 없습니다.";
			loc="reservationList.ana";
			
		}// end of if_else
		
		req.setAttribute("msg", msg);
		req.setAttribute("loc", loc);
		
		return "msg";
	}// end of String rental()
	
}// end of class NSYReservationController
