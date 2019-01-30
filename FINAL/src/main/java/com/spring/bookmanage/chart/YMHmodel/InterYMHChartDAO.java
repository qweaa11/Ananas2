package com.spring.bookmanage.chart.YMHmodel;

import java.util.HashMap;
import java.util.List;

public interface InterYMHChartDAO 
{
	List<HashMap<String, String>> libraryOverdueRankList();

	List<HashMap<String, String>> getoverdueByGenreList(String libname);
}
