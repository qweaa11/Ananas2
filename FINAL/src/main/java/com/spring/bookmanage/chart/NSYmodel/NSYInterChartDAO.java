package com.spring.bookmanage.chart.NSYmodel;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface NSYInterChartDAO {
	
	// 도서관별 보유도서 비율(통계) 파이차트
	List<HashMap<String, String>> libraryBookPieChart();
	
	// 도서관별 보유도서 비율(통계) 바차트(분류별 도서비율 추가)
	List<HashMap<String, String>> libraryBookBarChart(String libname);

}// end of public interface NSYInterChartDAO
