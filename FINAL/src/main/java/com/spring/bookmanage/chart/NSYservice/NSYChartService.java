package com.spring.bookmanage.chart.NSYservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.chart.NSYmodel.NSYInterChartDAO;

@Service
public class NSYChartService implements NSYInterChartService{
	
	//DI 객체주입
	@Autowired
	private NSYInterChartDAO dao;
	
	// 도서관별 보유도서 비율(통계) 파이차트
	@Override
	public List<HashMap<String, String>> libraryBookPieChart() {
		
		List<HashMap<String, String>> libraryBookPieChart = dao.libraryBookPieChart();
		
		return libraryBookPieChart;
	}
	
	// 도서관별 보유도서 비율(통계) 바차트(분류별 도서비율 추가)
	@Override
	public List<HashMap<String, String>> libraryBookBarChart(String libname) {
		
		List<HashMap<String, String>> libraryBookBarChart = dao.libraryBookBarChart(libname);
		
		return libraryBookBarChart;
	}
}
