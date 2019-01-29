package com.spring.bookmanage.chart.YSWservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.chart.YSWmodel.InterYSWChartDAO;

@Service
public class YSWChartService implements InterYSWChartService {
	
	@Autowired
	private InterYSWChartDAO dao;

	
	/*// 도서 카테고리를 가져오기
	@Override
	public List<HashMap<String, String>> findBookCategory() {

		List<HashMap<String, String>> categoryList = dao.findBookCategory();
		return categoryList;
	}*/


	// 책 카테고리 선택시 차트 불러오기
	@Override
	public List<HashMap<String, String>> findBookChart() {

		List<HashMap<String, String>> chartInfo = dao.findBookChart();
		return chartInfo;
	}


	// 책 장르별 차트 불러오기
	@Override
	public List<HashMap<String, String>> findDetailBookChart(String CCODE) {

		List<HashMap<String, String>> detail = dao.findDetailBookChart(CCODE);
		return detail;
	}

}
