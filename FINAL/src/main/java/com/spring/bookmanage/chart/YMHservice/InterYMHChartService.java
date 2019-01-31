package com.spring.bookmanage.chart.YMHservice;

import java.util.HashMap;
import java.util.List;

public interface InterYMHChartService 
{
	/**
	 * 도서관별로 연체율을 얻어오는 메소드
	 * 
	 *
	 * @author 유민후
	 * @return List<HashMap<String, String>>
	 */
	List<HashMap<String, String>> libraryOverdueRankList();
	
	
	/**
	 * 도서관별, 장르별로 연체율을 얻어오는 메소드
	 * 
	 *
	 * @author 유민후
	 * @return List<HashMap<String, String>>
	 */
	List<HashMap<String, String>> getoverdueByGenreList(String libname);
}
