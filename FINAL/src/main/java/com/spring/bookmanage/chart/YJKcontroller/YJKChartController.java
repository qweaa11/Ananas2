package com.spring.bookmanage.chart.YJKcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.bookmanage.chart.YJKservice.InterYJKChartService;

@Controller
public class YJKChartController {
	
	//==== 의존객체 주입하기(DI : Dependency Injection)  ====
	@Autowired
	private InterYJKChartService service;
	
	@RequestMapping(value="/languageBookChart.ana", method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> languageBookChart(HttpServletRequest req, HttpServletResponse res) {
		
		List<HashMap<String, Object>> languageChart = new ArrayList<HashMap<String, Object>>();
		
		List<HashMap<String, String>> chartInfo = service.languageBookChart();
		
		for(HashMap<String, String> chartMap : chartInfo) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("PERCENTAGE", chartMap.get("PERCENTAGE"));
			map.put("CNT", chartMap.get("CNT"));
			map.put("LNAME", chartMap.get("LNAME"));
			
			languageChart.add(map);
		}
		
		return languageChart;
		
	}
	
	@RequestMapping(value="/libBookChart.ana", method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> libraryBookChart(HttpServletRequest req, HttpServletResponse res) {
		
		List<HashMap<String, Object>> libraryChart = new ArrayList<HashMap<String, Object>>();
		
		List<HashMap<String, String>> libchartInfo = service.libBookChart();
		
		for(HashMap<String, String> chartMap : libchartInfo) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("PERCENTAGE", chartMap.get("PERCENTAGE"));
			map.put("CNT", chartMap.get("CNT"));
			map.put("LIBNAME", chartMap.get("LIBNAME"));
			
			libraryChart.add(map);
		}
		
		return libraryChart;
		
	}
	
	@RequestMapping(value="liblanguageBookChart.ana", method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> liblanguageBookChart(HttpServletRequest req, HttpServletResponse res) {
		
		List<HashMap<String, Object>> detailInfo = new ArrayList<HashMap<String, Object>>();
		
		String libname = req.getParameter("LIBNAME");
		
		List<HashMap<String, String>> detail = service.liblanguageBookChart(libname);
		
		for(HashMap<String, String> chartMap : detail) {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("PERCENTAGE", chartMap.get("PERCENTAGE"));
			map.put("CNT", chartMap.get("CNT"));
			map.put("LNAME", chartMap.get("LNAME"));
			
			detailInfo.add(map);
		}
		return detailInfo;
	}

}
