package com.spring.bookmanage.chart.YMHservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.chart.YMHmodel.YMHChartDAO;

@Service
public class YMHChartService implements InterYMHChartService 
{
	@Autowired
	private YMHChartDAO dao;
	
	
	/**
	 * 도서관별로 연체율을 얻어오는 메소드
	 * 
	 *
	 * @author 유민후
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> libraryOverdueRankList() {
		List<HashMap<String, String>> list = dao.libraryOverdueRankList();
		return list;
	}


	/**
	 * 도서관별, 장르별로 연체율을 얻어오는 메소드
	 * 
	 *
	 * @author 유민후
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getoverdueByGenreList(String libname) {
		List<HashMap<String, String>> list = dao.getoverdueByGenreList(libname);
		return list;
	}
	
	
	
	
	
}
