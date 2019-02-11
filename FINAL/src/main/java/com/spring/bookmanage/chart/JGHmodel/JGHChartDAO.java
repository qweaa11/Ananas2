package com.spring.bookmanage.chart.JGHmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bookmanage.library.Yjkmodel.LibraryVO;

@Repository
public class JGHChartDAO implements ChartMapper {

	@Autowired private SqlSessionTemplate sqlsession;

	@Override
	public int findAllTerm() {
		int yearval = -1;// 연도의 value
		int loop = 0;// 반복횟수
		int row = 0;// 쿼리문의 결과

		while(true) {
			row = sqlsession.selectOne("jghChart.findAllTerm", loop);

			if(row == 0) break;

			loop++;
			yearval++;
		}// end of while

		return yearval;
	}// end of findAllTerm

	@Override
	public List<HashMap<String, Object>> findAllBestFieldRankByYearForChart(HashMap<String, String> dateMap, List<LibraryVO> libraryList) {
		
		List<HashMap<String, Object>> bestFieldChart = new ArrayList<>();
		for(LibraryVO libraryVO : libraryList) {
			List<Integer> countArray = new ArrayList<Integer>();
			dateMap.put("libcode", libraryVO.getLibcode());
			HashMap<String, Object> chartMap = new HashMap<>();

			String fname = "";

			HashMap<String, Object> bestFieldRank = sqlsession.selectOne("jghChart.findAllBestFieldRank", dateMap);
			if(bestFieldRank != null) {
				fname = (String)bestFieldRank.get("fname");
			}// end of if

			if(bestFieldRank != null) {
				countArray.add((int)bestFieldRank.get("count"));
			} else
				countArray.add(0);

			chartMap.put("name", libraryVO.getLibname() + "("+(fname.equals("")?"데이터 없음":fname)+")");
			chartMap.put("data", countArray);

			bestFieldChart.add(chartMap);
		}// end of outer for

		return bestFieldChart;
	}// end of findAllBestFieldRankByYearForChart

	@Override
	public List<LibraryVO> findAllLibrary() {
		List<LibraryVO> libraryList = sqlsession.selectList("jghChart.findAllLibrary");

		return libraryList;
	}// end of findAllLibrary
}