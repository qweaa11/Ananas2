package com.spring.bookmanage.chart.KGBservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.chart.KGBmodel.ChartKGBInterDAO;

@Service
public class ChartKGBService implements ChartKGBInterService {

	@Autowired
	private ChartKGBInterDAO dao;

	
	@Override
	public int findAllreturnTerm() {
		
		int cnt = dao.findAllreturnTerm();
		
		return cnt;
		
	}// end of findAllreturnTerm()------------------------------
	
	
	@Override
	public List<HashMap<String, Object>> findAllReturnGenreChart(String currentyear) {
		
		List<HashMap<String, Object>> genreChart = dao.findAllReturnGenreChart(currentyear);
		
		return genreChart;
	}// end of findAllReturnGenreChart()----------------------------------------

	
	
}
