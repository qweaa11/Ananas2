package com.spring.bookmanage.rental.YSWmodel;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface InterYSWRentalDAO {
	
	// 대출 목록 페이지 보여주기
	List<YSWRentalVO> findRentalList();

}
