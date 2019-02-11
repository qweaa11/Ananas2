package com.spring.bookmanage.chart.JGHmodel;

import java.util.HashMap;
import java.util.List;

import com.spring.bookmanage.library.Yjkmodel.LibraryVO;

public interface ChartMapper {

	/**
	 * 검색가능한 연도 전체조회
	 * @return
	 */
	int findAllTerm();

	/**
	 * 연간 도서관별 최다대여된 도서의 분야를 월별로 조회
	 * @param currentyear
	 * @return
	 */
	List<HashMap<String, Object>> findAllBestFieldRankByYearForChart(HashMap<String, String> dateMap, List<LibraryVO> libraryList);

	/**
	 * 모든 도서관 목록 조회
	 * @return
	 */
	List<LibraryVO> findAllLibrary();
}