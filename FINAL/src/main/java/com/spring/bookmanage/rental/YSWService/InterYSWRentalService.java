package com.spring.bookmanage.rental.YSWService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.bookmanage.rental.YSWmodel.YSWRentalVO;

@Service
public interface InterYSWRentalService {
	
	// 대출 목록 페이지 보여주기
	List<YSWRentalVO> findRentalList();

	// 대여 책자를 반환 시키고 대여 목록에서 삭제 시키기
	int returnAndDelete(HashMap<String, Object> paraMap);

	// 반납 기간을 연장 시키기
	int extendBook(HashMap<String, Object> paraMap);

}
