package com.spring.bookmanage.chart.JGHcontroller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.bookmanage.chart.JGHservice.JGHChartService;

@Controller
public class JGHChartController {
	
	@Autowired private JGHChartService service;

	@RequestMapping(value = "", method = {RequestMethod.GET})
	public List<HashMap<String, Object>> chart(HttpServletRequest request, HttpServletResponse response) {

		String currentYear = request.getParameter("currentYear");

		List<HashMap<String, Object>> bestBookChartByLibrary = service.bestBookChartByLibrary(currentYear);

		HashMap<String, String> paraMap = new HashMap<>();

		return bestBookChartByLibrary;
	}// end of chart
}