package com.spring.bookmanage.chart.NSYcontroller;
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

import com.spring.bookmanage.chart.NSYservice.NSYInterChartService;
import com.spring.bookmanage.common.AES256;


@Controller
public class NSYChartController {
	
	@Autowired
	private NSYInterChartService service;
	
	@Autowired
	private AES256 aes;
	
	// 도서관별 보유도서 비율(통계) 페이지 요청
	@RequestMapping(value="/libraryBookChart.ana",method= {RequestMethod.GET})
	public String libraryBookChart(HttpServletRequest req, HttpServletResponse res){
		
		return "chart/chartDetail/NSYChart.tiles1";
	}// end of String libraryBookChart()
	
	// 도서관별 보유도서 비율(통계) 파이차트 
	@RequestMapping(value="/libraryBookPieChart.ana",method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String,String>> libraryBookPieChart(HttpServletRequest req, HttpServletResponse res){
		
		List<HashMap<String,String>> libraryBookPieChart = new ArrayList<HashMap<String, String>>();
		
		List<HashMap<String, String>> chartData = service.libraryBookPieChart();
		
		for(HashMap<String, String> mainData : chartData) {
			HashMap<String, String> subData = new HashMap<String, String>();
			subData.put("percent", mainData.get("percent"));
			subData.put("libname", mainData.get("libname"));

			libraryBookPieChart.add(subData);
		}
		return libraryBookPieChart; 
	}// end of List<HashMap<String,String>> libraryBookPieChart()
	
	// 도서관별 보유도서 비율(통계) 바차트(분류별 도서비율 추가)
	@RequestMapping(value="libraryBookBarChart.ana", method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String,String>> libraryBookBarChart(HttpServletRequest req, HttpServletResponse res){
		
		String libname = req.getParameter("libname");
		
		List<HashMap<String,String>> libraryBookBarChart = new ArrayList<HashMap<String, String>>();
		
		List<HashMap<String, String>> chartData = service.libraryBookBarChart(libname);
		
		for(HashMap<String, String> mainData : chartData) {
			HashMap<String, String> subData = new HashMap<String, String>();
			subData.put("percent", mainData.get("percent"));
			subData.put("cname", mainData.get("cname"));
			
			libraryBookBarChart.add(subData);
		}// end of for문
		
		return libraryBookBarChart; 
	}// end of List<HashMap<String,String>> libraryBookBarChart()

}// end of public class NSYChartController()
