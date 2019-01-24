package com.spring.bookmanage.rental.YSWmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface InterYSWRentalDAO {
	
	// 대출 목록 페이지 보여주기
	List<YSWRentalVO> findRentalList();

	// 책 반납
	int returnBook(HashMap<String, Object> paraMap);
	
	// 책 반납 연체시 회원상태 변경
	int changeMemberStat(HashMap<String, Object> paraMap);
	
	// 도서 상태 변경
	int changeBookStat(HashMap<String, Object> paraMap);
	
	// 대여 리스트에서 삭제
	int deleteBook(HashMap<String, Object> paraMap);

	// 책 대여기간 연장
	int extendBook(HashMap<String, Object> paraMap);

	


	

}
