package com.spring.bookmanage.chart.KGBmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChartKGBDAO implements ChartKGBInterDAO {

	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public List<HashMap<String, Object>> findAllReturnGenreChart(String currentyear) {
		
			HashMap<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("CURRENTYEAR", currentyear);
			
			List<HashMap<String, Object>> genreChart = new ArrayList<HashMap<String, Object>>();
			
		for(int i=0; i<12; i++) {
			
			paraMap.put("RENTALMONTH", String.valueOf(i+1));
			
			List<HashMap<String, String>> genre = sqlsession.selectList("kgb.findAllReturnGenreChart", paraMap);
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("CHART", genre);
			
			genreChart.add(map);
			
		}// end of for---------------------------------------
		
		return genreChart;
		
	}// end of findAllReturnGenreChart()----------------------------------
	
}
