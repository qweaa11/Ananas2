package com.spring.bookmanage.chart.YSWcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.bookmanage.chart.YSWservice.InterYSWChartService;

@Controller
@Component
public class YSWChartController {
	
	@Autowired
	private InterYSWChartService service;
	
	
	// 도서 카테고리를 가져오기
	@RequestMapping(value="/bookChart.ana", method= {RequestMethod.GET})
	public String bookChart(HttpServletRequest req, HttpServletResponse res) {
		
		return "chart/YSWBookChart.tiles1";
	}
	
	
	// 책 카테고리 선택시 차트 불러오기
	@RequestMapping(value="/findBookChart.ana", method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> findBookChart(HttpServletRequest req, HttpServletResponse res) {
		
		List<HashMap<String, Object>> bookChart = new ArrayList<HashMap<String, Object>>();
		
		
		List<HashMap<String, String>> chartInfo = service.findBookChart();
		
		for(HashMap<String, String> chartMap : chartInfo) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("PERCENTAGE", chartMap.get("PERCENTAGE"));
			map.put("CNT", chartMap.get("CNT"));
			map.put("CNAME", chartMap.get("CNAME"));
			
			bookChart.add(map);
		}
		
		return bookChart;
	}
	
	
	// 책 장르별 차트 불러오기
	@RequestMapping(value="findDetailBookChart.ana", method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> findDetailBookChart(HttpServletRequest req, HttpServletResponse res) {
		
		List<HashMap<String, Object>> detailInfo = new ArrayList<HashMap<String, Object>>();
		
		String CCODE = req.getParameter("CNAME");
		//System.out.println("CCODE : " + CCODE);
		
		List<HashMap<String, String>> detail = service.findDetailBookChart(CCODE);
		
		for(HashMap<String, String> chartMap : detail) {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("PERCENTAGE", chartMap.get("PERCENTAGE"));
			map.put("CNT", chartMap.get("CNT"));
			map.put("GNAME", chartMap.get("GNAME"));
			
			System.out.println("PERCENTAGE : " + chartMap.get("PERCENTAGE"));
			System.out.println("CNT : " + chartMap.get("CNT"));
			System.out.println("GNAME : " + chartMap.get("GNAME"));
			
			detailInfo.add(map);
			
		}
		return detailInfo;
	}

}
