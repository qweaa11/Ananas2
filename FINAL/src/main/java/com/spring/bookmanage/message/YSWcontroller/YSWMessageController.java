package com.spring.bookmanage.message.YSWcontroller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.bookmanage.message.YSWservice.InterYSWMessageService;

@Component
@Controller
public class YSWMessageController {
	
	@Autowired
	private InterYSWMessageService service;
	
	
	@RequestMapping(value="messageForm.ana", method= {RequestMethod.GET})
	public String sendMessageForm(HttpServletRequest req, HttpServletResponse res) {
		
		List<HashMap<String, String>> basicInfoList = service.findBasicInfo();
		
		req.setAttribute("basicInfoList", basicInfoList);
		
		return "message/messageForm.tiles1";
	}

}
