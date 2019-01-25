package com.spring.bookmanage.chart.mainchart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChartMainController {

	@RequestMapping(value="/chart.ana", method= {RequestMethod.GET})
	public String chartMain(HttpServletRequest request, HttpServletResponse response) {
		
		return "chart/chartMain.tiles1";
		
	}// end of chartMain()-----------------------------------------
	
}
