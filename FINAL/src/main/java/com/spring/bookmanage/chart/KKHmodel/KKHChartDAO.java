package com.spring.bookmanage.chart.KKHmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class KKHChartDAO implements InterKKHChartDAO {
	
	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public List<HashMap<String, String>> getAllLibrary() {
		List<HashMap<String,String>> libraryList = sqlsession.selectList("KKHChart.getAllLibrary");
		return libraryList;
	}

	@Override
	public List<HashMap<String, String>> getAllCategory() {
		List<HashMap<String,String>> categoryList = sqlsession.selectList("KKHChart.getAllCategory");
		return categoryList;
	}

	@Override
	public List<HashMap<String, String>> chartOfcategoryInLibrary(String ccode) {
		List<HashMap<String,String>> chartOfcategoryInLibraryList = sqlsession.selectList("KKHChart.chartOfcategoryInLibrary", ccode);
		return chartOfcategoryInLibraryList;
	}

}
