package com.spring.bookmanage.chart.KKHcontroller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.bookmanage.chart.KKHservice.InterKKHChartService;

@Controller
public class ChartKKHController {

	@Autowired
	private InterKKHChartService service;
	
	@RequestMapping(value="/getAllLibrary.ana",method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String,String>> getAllLibrary(HttpServletRequest request, HttpServletResponse response){
		
		List<HashMap<String,String>> libraryList = service.getAllLibrary();
		
		return libraryList;
	}
	
	@RequestMapping(value="/getAllCategory.ana",method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String,String>> getAllCategory(HttpServletRequest request, HttpServletResponse response){
		
		List<HashMap<String,String>> categoryList = service.getAllCategory();
		
		return categoryList;
	}
	
	@RequestMapping(value="/KKHChartLibrary.ana",method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String,String>> chartOfcategoryInLibrary(HttpServletRequest request, HttpServletResponse response){
		String ccode = request.getParameter("ccode");
		System.out.println("ccode:"+ccode);
		List<HashMap<String,String>> chartOfcategoryInLibraryList = service.chartOfcategoryInLibrary(ccode);
		
		return chartOfcategoryInLibraryList;
	}
}
