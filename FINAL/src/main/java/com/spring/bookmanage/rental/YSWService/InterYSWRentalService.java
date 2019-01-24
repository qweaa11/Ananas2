package com.spring.bookmanage.rental.YSWService;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.bookmanage.rental.YSWmodel.YSWRentalVO;

@Service
public interface InterYSWRentalService {
	
	// 대여 목록 페이지 보여주기
	//List<YSWRentalVO> findRentalList();

	// 대여 책자를 반환 시키고 대여 목록에서 삭제 시키기
	int returnAndDelete(HashMap<String, Object> paraMap);

	// 반납 기간을 연장 시키기
	int extendBook(HashMap<String, Object> paraMap);

	// 검색어에 따른 대여 목록 결과의 총 페이지 구하기
	int findTotalPageWith(HashMap<String, String> paraMap);

	// 검색어가 없을 때 나오는 대여 목록의 총 페이지
	int findTotalPage();

	// 검색어에 따른 대여 목록 검색 
	List<YSWRentalVO> findRentalListWith(HashMap<String, Object> optionMap);
	
	// 검색어 없이 대여 목록 검색
	List<YSWRentalVO> findRentalList(HashMap<String, Object> optionMap);

}
