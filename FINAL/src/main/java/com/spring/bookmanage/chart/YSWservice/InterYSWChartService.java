package com.spring.bookmanage.chart.YSWservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface InterYSWChartService {

	// 도서 카테고리를 가져오기
	//List<HashMap<String, String>> findBookCategory();

	// 책 카테고리 선택시 차트 불러오기
	List<HashMap<String, String>> findBookChart();

	List<HashMap<String, String>> findDetailBookChart(String CCODE);

	// 카테고리별 대여 차트
	List<HashMap<String, String>> findRentalInfo(HashMap<String, String> paramap);

	// 장르별 대여 성별의 퍼센티지
	List<HashMap<String, String>> findRentalChartGender(HashMap<String, String> paramap);

}
