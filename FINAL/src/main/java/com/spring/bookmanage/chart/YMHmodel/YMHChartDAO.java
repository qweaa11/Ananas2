package com.spring.bookmanage.chart.YMHmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class YMHChartDAO implements InterYMHChartDAO
{
	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public List<HashMap<String, String>> libraryOverdueRankList() {
		List<HashMap<String, String>> list = sqlsession.selectList("YMH.libraryOverdueRankList");
		return list;
	}
	
	
	
	
	
	
	
	
}
