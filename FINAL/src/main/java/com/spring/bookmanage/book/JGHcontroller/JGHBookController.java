package com.spring.bookmanage.book.JGHcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.bookmanage.book.JGHservice.JGHBookService;

@Controller
public class JGHBookController {

	@Autowired private JGHBookService service;

	@RequestMapping(value = "deleteBookLog.ana", method = {RequestMethod.GET})
	public String deleteLog(HttpServletRequest request, HttpServletResponse response) {

		return "";
	}// end of deleteBookLog

	@RequestMapping(value = "deleteLog.ana", method = {RequestMethod.POST})
	public String getBack(HttpServletRequest request, HttpServletResponse response) {
		String[] delidSet = request.getParameterValues("");

		return "";
	}// end of getBack
}