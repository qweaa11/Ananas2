package com.spring.bookmanage.member.PMGservice;

import java.util.HashMap;
import java.util.List;

import com.spring.bookmanage.member.PMGmodel.PMGMemberVO;

/**
 * <b>회원상세 인터페이스 service</b>
 * <pre>회원기본정보(활동,휴면해제,정지), 회원도서정보(대여,예약)</pre>
 * @author 박민규
 */
public interface InterPMGService {

	PMGMemberVO findOneMemberByIdx(String idx);
	// idx로 회원상세 정보 보여주기
	
	int EditActivityOneMemberByIdx(String idx);
	// idx로 회원상태 활동으로 변경
	int EditInactivityOneMemberByIdx(String idx);
	// idx로 회원상태 휴면해제(활동)으로 변경
	int EditStopOneMemberByIdx(String idx);
	// idx로 회원상태 정지로 변경
	
	
	List<HashMap<String, String>> memberBookRentalList(String memberid);
	// memberid로 회원 대여 리스트를 가져옴.
	
	List<HashMap<String, String>> memberBookReservationList(String memberid);
	// memberid로 회원 예약 리스트를 가져옴.
	
	
}
