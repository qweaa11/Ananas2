package com.spring.bookmanage.r3.KGBModel;

import java.util.HashMap;
import java.util.List;

import com.spring.bookmanage.member.JGHmodel.MemberVO;

public interface InterKGBR3DAO {

	List<MemberVO> findAllMemberBySearchWord(HashMap<String, String> paraMap);
	// 컬럼명과 검색어를 받아 회원을 조회해오는 메소드 

	MemberVO findOneMemberBymemberid(String memberid);
	// 아이디를 받아 한명의 회원을 조회해오는 메소드

	List<KGBBookVO> findAllBookBySearchWord(HashMap<String, String> paraMap);
	// 컬럼명과 검색어를 받아 도서를 조회해오는 메소드 

	void findAllRentalByMemberid(HashMap<String, String> paraMap) throws Throwable;
	// 대여 최대갯수를 제한하기위한 메소드
	
	int addAllRentalById(HashMap<String, String> paraMap) throws Throwable;
	// 도서대여를 등록해주는 메소드

	int updateAllBookByBookid(HashMap<String, String> paraMap) throws Throwable;
	// 도서를 대여상태로 업데이트 해주는 메소드

	String findAllReservationByBookid(HashMap<String, String> paraMap) throws Throwable;
	// 예약 테이블에 예약된 책이 있는지 조회하는 메소드

	void deleteReservationByMemberid(String reserveMemberid) throws Throwable;
	// 예약 테이블에 예약된 책이 대출이 되어 지우는 메소드

	


}
