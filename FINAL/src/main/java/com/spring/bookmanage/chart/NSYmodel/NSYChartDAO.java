package com.spring.bookmanage.chart.NSYmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NSYChartDAO implements NSYInterChartDAO {
	
	// DI 객체주입
	@Autowired
	private SqlSessionTemplate session;
	
	// 도서관별 보유도서 비율(통계) 파이차트 
	@Override
	public List<HashMap<String, String>> libraryBookPieChart() {
		
		List<HashMap<String, String>> libraryBookPieChart = session.selectList("NSYChart.libraryBookPieChart");
		
		return libraryBookPieChart;
	}
	
	// 도서관별 보유도서 비율(통계) 바차트(분류별 도서비율 추가)
	@Override
	public List<HashMap<String, String>> libraryBookBarChart(String libname) {
		
		List<HashMap<String, String>> libraryBookBarChart = session.selectList("NSYChart.libraryBookBarChart", libname);
		
		return libraryBookBarChart;
	}
	
}
