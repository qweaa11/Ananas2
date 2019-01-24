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
	// 대여/예약 최대갯수를 제한하기위한 메소드
	
	int addAllRentalById(HashMap<String, String> paraMap) throws Throwable;
	// 도서대여를 등록해주는 메소드

	int updateAllBookByBookid(HashMap<String, String> paraMap) throws Throwable;
	// 도서를 대여상태로 업데이트 해주는 메소드

	String findNoteqReservationByBookid(HashMap<String, String> paraMap) throws Throwable;
	// 예약 테이블에 예약된 책이 회원아이디와 일치하는지 조회하는 메소드
	
	String findAllReservationByBookid(HashMap<String, String> paraMap);
	// 예약 테이블에 예약된 책이 있는지 조회하는 메소드

	void deleteReservationByBookid(String reserveMemberid) throws Throwable;
	// 예약 테이블에 예약된 책이 대출이 되어 지우는 메소드

	void findAllOverdateByMeberid(HashMap<String, String> paraMap) throws Throwable;
	// 대여 한 책 중 연체된 책이 있을 경우 대여 불가 해주는 메소드
	
	List<HashMap<String, String>> findAllRentalByCategory(HashMap<String, String> paraMap);
	// 대여된 목록을 가져오는 메소드

	void updateMemberByDeadeline(HashMap<String, String> paraMap) throws Throwable;
	// 연체료와 이용정지기간 등록하기

	void updateBookstatusByBookid(HashMap<String, String> paraMap, String reserveBookid) throws Throwable;
	// 책 상태 일반 상태로 만드는 메소드

	void insertReturnByRentalInfo(HashMap<String, String> paraMap) throws Throwable;
	// 대여에 있던 정보를 반납에 넣어주는 메소드 

	void deleteRentalByBookid(HashMap<String, String> paraMap) throws Throwable;
	// 대여 테이블의 반납이 된 정보를 지워주는 메소드

	void findAllReservationCountByBookid(HashMap<String, String> paraMap) throws Throwable;
	// 책의 예약이 있는지 조회해오는 메소드

	void UpdateAllRenewByBookid(HashMap<String, String> paraMap) throws Throwable;
	// 대여한 도서의 반납기간을 연장하는 메소드

	List<HashMap<String, String>> findAllReserveRentalByCategory(HashMap<String, String> paraMap);
	// 예약에 대한 대여된 도서를 가져오는 메소드
	
	void insertReserveByRentalInfo(HashMap<String, String> paraMap) throws Throwable;
	// 대여한 도서에 예약을 거는 메소드

	

	

	
	


}
