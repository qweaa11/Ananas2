package com.spring.bookmanage.chart.YJKservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface InterYJKChartService {

	List<HashMap<String, String>> languageBookChart();

	List<HashMap<String, String>> libBookChart();

	List<HashMap<String, String>> liblanguageBookChart(String libname);

}
