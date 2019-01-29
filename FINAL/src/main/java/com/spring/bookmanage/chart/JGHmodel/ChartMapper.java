package com.spring.bookmanage.chart.JGHmodel;

import java.util.HashMap;
import java.util.List;

public interface ChartMapper {

	/**
	 * 도서관별 최다대여 도서목록 조회
	 * @param currentYear
	 * @return
	 */
	List<HashMap<String, Object>> findAllBestBookByLibrary(String currentYear);

}