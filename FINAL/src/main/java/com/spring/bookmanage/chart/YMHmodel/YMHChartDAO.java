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

	
	/**
	 * 도서관별로 연체율을 얻어오는 메소드
	 * 
	 *
	 * @author 유민후
	 * @return List<HashMap<String, String>>
	 */
	@Override
	public List<HashMap<String, String>> libraryOverdueRankList() {
		List<HashMap<String, String>> list = sqlsession.selectList("YMH.libraryOverdueRankList");
		return list;
	}

	/**
	 * 도서관별, 장르별로 연체율을 얻어오는 메소드
	 * 
	 *
	 * @author 유민후
	 * @return List<HashMap<String, String>>
	 */
	@Override
	public List<HashMap<String, String>> getoverdueByGenreList(String libname) {
		List<HashMap<String, String>> list = sqlsession.selectList("YMH.getoverdueByGenreList", libname);
		return list;
	}
	
	
	
	
	
	
	
	
}
