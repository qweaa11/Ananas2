package com.spring.bookmanage.chart.KKHservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.chart.KKHmodel.InterKKHChartDAO;


@Service
public class KKHChartService implements InterKKHChartService {
	
	@Autowired
	private InterKKHChartDAO chartdao;

	@Override
	public List<HashMap<String, String>> getAllLibrary() {
		List<HashMap<String,String>> libraryList = chartdao.getAllLibrary();
		return libraryList;
	}

	@Override
	public List<HashMap<String, String>> getAllCategory() {
		List<HashMap<String,String>> categoryList = chartdao.getAllCategory();
		return categoryList;
	}

	@Override
	public List<HashMap<String, String>> chartOfcategoryInLibrary(String ccode) {
		List<HashMap<String,String>> chartOfcategoryInLibraryList = chartdao.chartOfcategoryInLibrary(ccode);
		return chartOfcategoryInLibraryList;
	}

}
