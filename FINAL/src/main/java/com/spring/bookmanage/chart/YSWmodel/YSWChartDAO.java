package com.spring.bookmanage.chart.YSWmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class YSWChartDAO implements InterYSWChartDAO {
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
		
	/*//도서 카테고리를 가져오기	
	@Override
	public List<HashMap<String, String>> findBookCategory() {

		List<HashMap<String, String>> categoryList = sqlsession.selectList("YSW.findBookCategory");
		return categoryList;
	}*/


	// 책 카테고리 선택시 차트 불러오기
	@Override
	public List<HashMap<String, String>> findBookChart() {

		List<HashMap<String, String>> chartInfo = sqlsession.selectList("YSW.findBookChart");
		return chartInfo;
	}


	@Override
	public List<HashMap<String, String>> findDetailBookChart(String CCODE) {

		List<HashMap<String, String>> detail = sqlsession.selectList("YSW.findDetailBookChart", CCODE);
		return detail;
	}

}
