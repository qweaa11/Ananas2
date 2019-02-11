package com.spring.bookmanage.chart.KKHservice;

import java.util.HashMap;
import java.util.List;

public interface InterKKHChartService {

	List<HashMap<String, String>> getAllLibrary();

	List<HashMap<String, String>> getAllCategory();

	List<HashMap<String, String>> chartOfcategoryInLibrary(String ccode);

}
