package com.spring.bookmanage.chart.JGHmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JGHChartDAO implements ChartMapper {

	@Autowired private SqlSessionTemplate sqlsession;

	@Override
	public List<HashMap<String, Object>> findAllBestBookByLibrary(String currentYear) {
		List<HashMap<String, Object>> bestBookChartByLibrary = null;

		HashMap<String, String> paraMap = new HashMap<>();
		paraMap.put("currentYear", currentYear);

		bestBookChartByLibrary = new ArrayList<>();
		for (int i=0;i<12;i++) {
			paraMap.put("month", String.valueOf(i + 1));

			// List<HashMap<String, String>> libraryList = sqlsession.selectList("jghBook.findAllLibrary", paraMap);

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("chart", libraryList);

			bestBookChartByLibrary.add(map);
		} // end of for

		return bestBookChartByLibrary;
	}// end of findAllBestBookByLibrary
}