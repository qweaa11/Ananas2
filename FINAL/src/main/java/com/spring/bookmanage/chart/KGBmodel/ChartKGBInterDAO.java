package com.spring.bookmanage.chart.KGBmodel;

import java.util.HashMap;
import java.util.List;

public interface ChartKGBInterDAO {

	int findAllreturnTerm();
	// 검색 가능한 년도를 가져오는 메소드
	
	List<HashMap<String, Object>> findAllReturnGenreChart(String currentyear);
	// 년도의 월별 장르 차트 정보를 가져오는 메소드

	

}
