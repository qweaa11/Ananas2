package com.spring.bookmanage.library.KGBcontroller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.bookmanage.JDSmodel.LibrarianVO;
import com.spring.bookmanage.library.JGHservice.JGHLibraryService;
import com.spring.bookmanage.library.PMGservice.PMGLibraryService;

@Controller
public class KGBLibraryController {

	@Autowired JGHLibraryService service;
	
	@Autowired
	PMGLibraryService service2;
	
	@RequestMapping(value="/index.ana", method= {RequestMethod.GET})
	public String index(HttpServletRequest request,HttpServletResponse response) {

		return "library/index.tiles1";
	}// end of index

	
	@RequestMapping(value="/alarm.ana", method= {RequestMethod.GET})
	@ResponseBody
	public HashMap<String, String> alarm(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		LibrarianVO libVO = ((LibrarianVO)session.getAttribute("loginLibrarian"));
		
	//	System.out.println(libId); // 확인용
		
		int result = 0;
		
		if(libVO != null) {
			String libId = libVO.getLibid();
			
			result = service2.alarmCount(libId);
		}
		
	//	System.out.println(libCount); // 확인용
		
		HashMap<String, String> json = new HashMap<String, String>();
		
		json.put("COUNT", String.valueOf(result));
		
		return json;
	}// end of alarm()-------------------------
	
	
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