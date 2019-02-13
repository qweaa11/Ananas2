package com.spring.bookmanage.chatting.NSYcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NSYChattingController {

	@RequestMapping(value="multichat.ana", method={RequestMethod.GET})
	public String multichat(HttpServletRequest req, HttpServletResponse res){
		
		return "chatting/multichat.tiles1";
	}
	
}
