package com.spring.bookmanage.chart.JGHcontroller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.bookmanage.chart.JGHservice.JGHChartService;
import com.spring.bookmanage.library.Yjkmodel.LibraryVO;

@Controller
public class JGHChartController {
	
	@Autowired private JGHChartService service;

	/**
	 * 반납기록정보로부터 모든 도서관에 대여된적이 있는 연도를 불러오기
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "jghChartYear.ana", method = {RequestMethod.GET})
	@ResponseBody
	public HashMap<String, String>  yearForChart(HttpServletRequest request, HttpServletResponse response) {
		int count = service.loadAllTermService();

		HashMap<String, String> json = new HashMap<>();
		json.put("count", String.valueOf(count));

		return json;
	}// end of yearForChart

	/**
	 * 해당년도에 월별 최다대여된 도서의 분야를 조회해주는 차트 불러오기
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "jghChartBestField.ana", method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> bestFieldChart(HttpServletRequest request, HttpServletResponse response) {
		String currentyear = request.getParameter("currentyear");// selected year by View
		String month = request.getParameter("month");// selected month by View

		List<LibraryVO> libraryList = service.loadAllLibrary();
		HashMap<String, String> dateMap = new HashMap<>();
		dateMap.put("currentyear", currentyear);
		dateMap.put("month", month);
		
		List<HashMap<String, Object>> bestFieldRankChart = service.bestFieldRank(dateMap, libraryList);

		return bestFieldRankChart;
	}// end of unknowns
}