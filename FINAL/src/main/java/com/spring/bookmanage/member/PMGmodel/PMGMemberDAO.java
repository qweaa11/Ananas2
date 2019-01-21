package com.spring.bookmanage.member.PMGmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
/**
 * <b>회원상세 DAO</b>
 * <pre>회원기본정보(활동,휴면해제,정지), 회원도서정보(대여,예약)</pre>
 * @author 박민규
 */
public class PMGMemberDAO implements InterPMGMemberDAO {

	// 의존객체 주입하기(DI : Dependency Injection)
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	@Override
	public PMGMemberVO findOneMemberByIdx(String idx) {
		PMGMemberVO pmgMemberVO = sqlsession.selectOne("PMG.findOneMemberByIdx", idx);
		return pmgMemberVO;
	}

	@Override
	public int EditActivityOneMemberByIdx(String idx) {
		int n = sqlsession.update("PMG.EditActivityOneMemberByIdx", idx);
		return n;
	}
	@Override
	public int EditInactivityOneMemberByIdx(String idx) {
		int n = sqlsession.update("PMG.EditInactivityOneMemberByIdx", idx);
		return n;
	}
	@Override
	public int EditStopOneMemberByIdx(String idx) {
		int n = sqlsession.update("PMG.EditStopOneMemberByIdx", idx);
		return n;
	}

	@Override
	public List<HashMap<String, String>> memberBookRentalList(String memberid) {
		List<HashMap<String, String>> rentalList = sqlsession.selectList("PMG.memberBookRentalList", memberid);
		return rentalList;
	}

	@Override
	public List<HashMap<String, String>> memberBookReservationList(String memberid) {
		List<HashMap<String, String>> reservationList = sqlsession.selectList("PMG.memberBookReservationList", memberid);
		return reservationList;
	}

	
	
	
	
	
}
