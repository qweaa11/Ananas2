package com.spring.bookmanage.chart.JGHservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.chart.JGHmodel.ChartMapper;
import com.spring.bookmanage.library.Yjkmodel.LibraryVO;

@Service
public class JGHChartService {

	@Autowired private ChartMapper mapper;

	/**
	 * 검색가능한 연도 불러오기
	 * @return
	 */
	public int loadAllTermService() {
		int count = mapper.findAllTerm();

		return count;
	}// end of loadAllTermService

	/**
	 * 연간 도서관별 최다대여된 도서의 분야정보 알아오기 
	 * @param dateMap
	 * @param libraryList
	 * @return
	 */
	public List<HashMap<String, Object>> bestFieldRank(HashMap<String, String> dateMap, List<LibraryVO> libraryList) {
		List<HashMap<String, Object>> bestBookChart = mapper.findAllBestFieldRankByYearForChart(dateMap, libraryList);

		return bestBookChart;
	}// end of FieldRank

	/**
	 * 모든 도서관 목록 불러오기
	 * @return
	 */
	public List<LibraryVO> loadAllLibrary() {
		List<LibraryVO> libraryList = mapper.findAllLibrary();

		return libraryList;
	}// end of loadAllLibrary

	
}