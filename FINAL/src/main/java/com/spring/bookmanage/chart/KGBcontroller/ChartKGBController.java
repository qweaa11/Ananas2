package com.spring.bookmanage.chart.KGBcontroller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.bookmanage.chart.KGBservice.ChartKGBInterService;

@Controller
public class ChartKGBController {

	@Autowired
	private ChartKGBInterService service;
	
	
	
	/**
	 * 검색 가능한 년도를 가져오는 메소드
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/kgbChartYear.ana", method= {RequestMethod.GET})
	@ResponseBody
	public HashMap<String, Integer> kgbChartYear(HttpServletRequest request, HttpServletResponse response) {
		
		int cnt = service.findAllreturnTerm();
		
		HashMap<String, Integer> json = new HashMap<String, Integer>();
		
		json.put("CNT", cnt);
		
		return json;
		
	}// end of kgbChartYear()--------------------------------------
	
	
	/**
	 * 년도의 월별 장르 차트 정보를 가져오는 메소드
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/genre.ana", method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> genre(HttpServletRequest request, HttpServletResponse response) {
		
		String currentyear = request.getParameter("currentyear");
		
		List<HashMap<String, Object>> genreChart = service.findAllReturnGenreChart(currentyear);
		
		return genreChart;
		
	}// end of genre()----------------------------------------------------
	
}
