package com.spring.bookmanage.chart.KKHmodel;

import java.util.HashMap;
import java.util.List;

public interface InterKKHChartDAO {

	List<HashMap<String, String>> getAllLibrary();

	List<HashMap<String, String>> getAllCategory();

	List<HashMap<String, String>> chartOfcategoryInLibrary(String ccode);

}
