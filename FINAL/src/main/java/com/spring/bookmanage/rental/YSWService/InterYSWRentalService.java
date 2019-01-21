package com.spring.bookmanage.rental.YSWService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.bookmanage.rental.YSWmodel.YSWRentalVO;

@Service
public interface InterYSWRentalService {
	
	// 대출 목록 페이지 보여주기
	List<YSWRentalVO> findRentalList();

}
