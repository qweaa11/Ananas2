package com.sping.bookmanage.chart.JGHcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JGHChartController {

	@RequestMapping(value = "", method = {RequestMethod.GET})
	public String chart(HttpServletRequest request, HttpServletResponse response) {

		return "";
	}// end of chart
}