package com.spring.bookmanage.chart.YJKservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bookmanage.chart.YJKmodel.InterYJKChartDAO;

@Repository
public class YJKChartService implements InterYJKChartService {
	
	@Autowired
	InterYJKChartDAO dao;

	@Override
	public List<HashMap<String, String>> languageBookChart() {
		
		List<HashMap<String, String>> chartInfo = dao.languageBookChart();
		
		return chartInfo;
	}

	@Override
	public List<HashMap<String, String>> libBookChart() {
		
		List<HashMap<String, String>> libchartInfo = dao.libBookChart();
		
		return libchartInfo;
	}

	@Override
	public List<HashMap<String, String>> liblanguageBookChart(String libname) {
		
		List<HashMap<String, String>> detail = dao.liblanguageBookChart(libname);
		
		return detail;
	}

}
