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
	
	
	public List<HashMap<String, String>> libraryOverdueRankList() {
		List<HashMap<String, String>> list = dao.libraryOverdueRankList();
		return list;
	}


	public List<HashMap<String, String>> getoverdueByGenreList(String libname) {
		List<HashMap<String, String>> list = dao.getoverdueByGenreList(libname);
		return list;
	}
	
	
	
	
	
}
