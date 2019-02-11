package com.spring.bookmanage.chart.YJKmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class YJKChartDAO implements InterYJKChartDAO {
	
	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public List<HashMap<String, String>> languageBookChart() {
		
		List<HashMap<String, String>> chartInfo = sqlsession.selectList("bookmanage.languageBookChart");
		
		return chartInfo;
	}

	@Override
	public List<HashMap<String, String>> libBookChart() {

		List<HashMap<String, String>> libchartInfo = sqlsession.selectList("bookmanage.libBookChart");
		
		return libchartInfo;
	}

	@Override
	public List<HashMap<String, String>> liblanguageBookChart(String libname) {
		
		List<HashMap<String, String>> detail = sqlsession.selectList("bookmanage.liblanguageBookChart", libname);
		
		return detail;
	}
	

}
