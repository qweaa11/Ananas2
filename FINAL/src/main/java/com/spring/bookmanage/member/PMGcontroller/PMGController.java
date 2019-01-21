package com.spring.bookmanage.member.PMGcontroller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.bookmanage.common.AES256;
import com.spring.bookmanage.member.PMGmodel.PMGMemberVO;
import com.spring.bookmanage.member.PMGservice.PMGService;

@Controller
/**
 * <b>회원상세 controller</b>
 * <pre>회원기본정보(활동,휴면해제,정지), 회원도서정보(대여,예약)</pre>
 * @author 박민규
 */
public class PMGController {

	// 의존객체 주입하기(DI : Dependency Injection)
	@Autowired
	private PMGService service;
	
	// 양방향 암호화 AES256을 사용하여 암호화/복호화 하기 위한 클래스 의존객체 주입하기
	@Autowired
	private AES256 aes;
	
	/**
	 * <b>회원상세 페이지</b>
	 * @param pmgMemberVO
	 * @param request
	 * @param response
	 * @return
	 * idx를 받아 회원기본정보(VO를 이용)를 보여줌, 
	 * memberid를 받아 회원도서정보(대여,예약) 대여,예약을 보여줌
	 */
	@RequestMapping(value="/memberDetail.ana", method= {RequestMethod.GET})
	public String member(PMGMemberVO pmgMemberVO, HttpServletRequest request, HttpServletResponse response) {
		
		String idx = request.getParameter("idx");
		
		pmgMemberVO = service.findOneMemberByIdx(idx);
		try {
			pmgMemberVO.setEmail(aes.decrypt(pmgMemberVO.getEmail())); // 복호화한 email
			
			// 복호화한 연락처 "-" 넣어주기
			String phone = aes.decrypt(pmgMemberVO.getPhone());			
			if(phone.length() == 10 || phone.length() == 11) {
				if(phone.length() == 10) {
					phone = phone.substring(0, 3) +"-"+ phone.substring(3, 6) +"-"+ phone.substring(6, 10);
				}else {
					phone = phone.substring(0, 3) +"-"+ phone.substring(3, 7) +"-"+ phone.substring(7, 11);
				}
			}else {
				System.out.println("핸드폰이 아니라면?");
			}			
			pmgMemberVO.setPhone(phone);
			
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {			
			e.printStackTrace();
		}
		
		request.setAttribute("pmgMemberVO", pmgMemberVO);
		
		// memberid를 받아 한 회원의 대여정보를 보여줌
		String memberid = pmgMemberVO.getMemberid();
		
		List<HashMap<String, String>> rentalList = service.memberBookRentalList(memberid);
		
		request.setAttribute("rentallist", rentalList);
		///////////////////////////////////////////////
		
		// memberid를 받아 한 회원의 예약정보를 보여줌
		List<HashMap<String, String>> reservationList = service.memberBookReservationList(memberid);
		
		request.setAttribute("reservationList", reservationList);		
		///////////////////////////////////////////////
		
		return "member/memberDetail.tiles1";
	}// end of member
	
	/**
	 * <b>회원상세 페이지(버튼)</b>
	 * @param request
	 * @param response
	 * @return
	 * idx를 입력받아 status 활동으로 변경 
	 */
	@RequestMapping(value="/goStatusEdit0.ana", method= {RequestMethod.POST})
	public String goStatusEdit0(HttpServletRequest request, HttpServletResponse response) {
		
		String idx = request.getParameter("idx");
		String msg = "";
		String loc = "";
		
		int n = service.EditActivityOneMemberByIdx(idx);
		
		if(n == 1) {
			msg = "회원번호 " +idx+ "번 활동으로 변경이 성공 되었습니다.";
			loc = "memberDetail.ana?idx="+idx;
		}else {
			msg = "회원번호 " +idx+ "의 활동으로 변경이 실패 되었습니다.";
			loc = "javascript:history.back();";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		return "msg";
	}// end of goStatusEdit0
	
	/**
	 * <b>회원상세 페이지(버튼)</b>
	 * @param request
	 * @param response
	 * @return
	 * idx를 입력받아 휴면해제처리 status 활동으로 변경 
	 */
	@RequestMapping(value="/goStatusEdit1.ana", method= {RequestMethod.POST})
	public String goStatusEdit1(HttpServletRequest request, HttpServletResponse response) {
		
		String idx = request.getParameter("idx");
		String msg = "";
		String loc = "";
		
		int n = service.EditInactivityOneMemberByIdx(idx);
		
		if(n == 1) {
			msg = "회원번호 " +idx+ "번 휴면해제(활동)으로 변경이 성공 되었습니다.";
			loc = "memberDetail.ana?idx="+idx;
		}else {
			msg = "회원번호 " +idx+ "의 휴면해제(활동)으로 변경이 실패 되었습니다.";
			loc = "javascript:history.back();";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		return "msg";
	}// end of goStatusEdit1
	
	/**
	 * <b>회원상세 페이지(버튼)</b>
	 * @param request
	 * @param response
	 * @return
	 * idx를 입력받아 회원을 status 정지로 변경 
	 */
	@RequestMapping(value="/goStatusEdit2.ana", method= {RequestMethod.POST})
	public String goStatusEdit2(HttpServletRequest request, HttpServletResponse response) {
		
		String idx = request.getParameter("idx");
		String msg = "";
		String loc = "";
		
		int n = service.EditStopOneMemberByIdx(idx);
		
		if(n == 1) {
			msg = "회원번호 " +idx+ "번 정지로 변경이 성공 되었습니다.";
			loc = "memberDetail.ana?idx="+idx;
		}else {
			msg = "회원번호 " +idx+ "의 정지로 변경이 실패 되었습니다.";
			loc = "javascript:history.back();";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		return "msg";
	}// end of goStatusEdit2

	
}
