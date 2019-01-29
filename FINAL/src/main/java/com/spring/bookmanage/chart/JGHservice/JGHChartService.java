package com.spring.bookmanage.chart.JGHservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.chart.JGHmodel.ChartMapper;

@Service
public class JGHChartService {

	@Autowired private ChartMapper mapper;

	/**
	 * 도서관별 최다대여 도서차트 불러오기
	 * @param currentYear
	 * @return
	 */
	public List<HashMap<String, Object>> bestBookChartByLibrary(String currentYear) {
		List<HashMap<String, Object>> bestBookListByLibrary = mapper.findAllBestBookByLibrary(currentYear);

		return bestBookListByLibrary;
	}// end of 
}