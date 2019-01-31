package com.spring.bookmanage.r3.KGBService;

import java.util.HashMap;
import java.util.List;

import com.spring.bookmanage.member.JGHmodel.MemberVO;
import com.spring.bookmanage.r3.KGBModel.KGBBookVO;

public interface InterKGBR3Service {

	/**
	 * 컬럼명과 검색어를 받아 회원을 조회해오는 메소드 
	 * @param paraMap
	 * @return
	 */
	List<MemberVO> findAllMemberBySearchWord(HashMap<String, String> paraMap);

	/**
	 * 아이디를 받아 한명의 회원을 조회해오는 메소드
	 * @param memberid
	 * @return
	 */
	MemberVO findOneMemberBymemberid(String memberid);

	/**
	 * 컬럼명과 검색어를 받아 도서를 조회해오는 메소드 
	 * @param paraMap
	 * @return
	 */
	List<KGBBookVO> findAllBookBySearchWord(HashMap<String, String> paraMap);
	
	/**
	 * 도서대여를 등록해주는 메소드
	 * @param paraMap
	 * @return
	 * @throws Throwable
	 */
	int addAllRentalByIdAfterUpdate(HashMap<String, String> paraMap) throws Throwable;

	/**
	 * 대여된 목록을 가져오는 메소드
	 * @param paraMap
	 * @return
	 */
	List<HashMap<String, String>> findAllRentalByCategory(HashMap<String, String> paraMap);

	/**
	 * 도서 반납하는 메소드
	 * @param paraMap
	 * @return
	 * @throws Throwable
	 */
	int addReturnByBookid(HashMap<String, String> paraMap) throws Throwable;

	/**
	 * 대여 기간을 연장해주는 메소드
	 * @param paraMap
	 * @return int
	 * @throws Throwable
	 */
	int updateRentalRenewByBookid(HashMap<String, String> paraMap) throws Throwable;

	
	/**
	 * 예약에 대한 대여된 도서를 가져오는 메소드
	 * @param paraMap
	 * @return
	 */
	List<HashMap<String, String>> findAllReserveRentalByCategory(HashMap<String, String> paraMap);
	
	
	/**
	 * 대여된 책에 대하여 예약해주는 메소드
	 * @param paraMap
	 * @return int
	 */
	int insertReserveByRentalInfo(HashMap<String, String> paraMap) throws Throwable;

	
	

	

	
	
}
