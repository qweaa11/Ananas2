package com.spring.bookmanage.member.PMGservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.common.AES256;
import com.spring.bookmanage.member.PMGmodel.PMGMemberDAO;
import com.spring.bookmanage.member.PMGmodel.PMGMemberVO;

@Service
/**
 * <b>회원상세 service</b>
 * <pre>회원기본정보(활동,휴면해제,정지), 회원도서정보(대여,예약)</pre>
 * @author 박민규
 */
public class PMGService implements InterPMGService {

	// 의존객체 주입하기(DI : Dependency Injection)
	@Autowired
	private PMGMemberDAO dao;
	
	// 양방향 암호화 AES256을 사용하여 암호화/복호화 하기 위한 클래스 의존객체 주입하기
	@Autowired
	private AES256 aes;
	
	@Override
	public PMGMemberVO findOneMemberByIdx(String idx) {		
		PMGMemberVO pmgMemberVO = dao.findOneMemberByIdx(idx);
		return pmgMemberVO;
	}

	@Override
	public int EditActivityOneMemberByIdx(String idx) {
		int n = dao.EditActivityOneMemberByIdx(idx);
		return n;
	}
	@Override
	public int EditInactivityOneMemberByIdx(String idx) {
		int n = dao.EditInactivityOneMemberByIdx(idx);
		return n;
	}
	@Override
	public int EditStopOneMemberByIdx(String idx) {
		int n = dao.EditStopOneMemberByIdx(idx);
		return n;
	}

	@Override
	public List<HashMap<String, String>> memberBookRentalList(String memberid) {
		List<HashMap<String, String>> rentalList = dao.memberBookRentalList(memberid);
		return rentalList;
	}

	@Override
	public List<HashMap<String, String>> memberBookReservationList(String memberid) {
		List<HashMap<String, String>> reservationList = dao.memberBookReservationList(memberid);
		return reservationList;
	}

	
	
	
	
}
