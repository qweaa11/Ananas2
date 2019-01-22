package com.spring.bookmanage.member.PMGservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.common.AES256;
import com.spring.bookmanage.member.PMGmodel.PMGMemberDAO;
import com.spring.bookmanage.member.PMGmodel.PMGMemberVO;

@Service
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
	public int EditWithdrawalOneMemberByIdx(String idx) {
		int n = dao.EditWithdrawalOneMemberByIdx(idx);
		return n;
	}
	@Override
	public int EditShutdownOneMemberByIdx(String idx) {
		int n = dao.EditShutdownOneMemberByIdx(idx);
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
