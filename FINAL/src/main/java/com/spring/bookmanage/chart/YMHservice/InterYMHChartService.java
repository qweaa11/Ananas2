package com.spring.bookmanage.chart.YMHservice;

import java.util.HashMap;
import java.util.List;

public interface InterYMHChartService 
{
	List<HashMap<String, String>> libraryOverdueRankList();
	
	List<HashMap<String, String>> getoverdueByGenreList(String libname);
}
