package com.spring.bookmanage.library.KGBcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.bookmanage.library.JGHservice.JGHLibraryService;

@Controller
public class KGBLibraryController {

	@Autowired JGHLibraryService service;

	@RequestMapping(value="/index.ana", method= {RequestMethod.GET})
	public String index(HttpServletRequest request,HttpServletResponse response) {

		return "library/index.tiles1";
	}// end of index

	@RequestMapping(value = "schedulerRun.ana", method = {RequestMethod.GET})
	public String schedulerRun(HttpServletRequest request, HttpServletResponse response) {
		int row = service.schedulerRunService();

		String msg = "";
		String loc = "";
		if(row == 1) {
			msg = "스케줄러 실행 성공!";
			loc = "index.ana";
		} else {
			msg = "스케줄러 실행 실패!";
			loc = "javascript:history.back();";
		}// end of if~else

		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);

		return "msg";
	}// end of schedulerRun
}