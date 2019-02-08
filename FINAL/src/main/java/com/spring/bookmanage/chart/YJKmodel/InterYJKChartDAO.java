package com.spring.bookmanage.chart.YJKmodel;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface InterYJKChartDAO {

	List<HashMap<String, String>> languageBookChart();

	List<HashMap<String, String>> libBookChart();

	List<HashMap<String, String>> liblanguageBookChart(String libname);

}
