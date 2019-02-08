package com.spring.bookmanage.message.YSWcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.bookmanage.JDSmodel.LibrarianVO;
import com.spring.bookmanage.message.YSWservice.InterYSWMessageService;

@Component
@Controller
public class YSWMessageController {
	
	@Autowired
	private InterYSWMessageService service;
	
	
	// 관리자, 사서 메세지 보내기 페이지 보여주기
	@RequestMapping(value="messageForm.ana", method= {RequestMethod.GET})
	public String sendMessageForm(HttpServletRequest req, HttpServletResponse res) {
		
		// 메세지 보내기 페이지를 보여줄때 도서관 이름 가져오기
		List<HashMap<String, String>> libraryName = service.findLibrary();
		
		req.setAttribute("libraryName", libraryName);
		
		return "message/messageForm.tiles1";
	}
	
	
	// 관리자, 사서 메세지 보내기 페이지에 도서관에 따른 관리자, 사서 보여주기
	@RequestMapping(value="findRecipient.ana", method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> findRecipient(HttpServletRequest req, HttpServletResponse res) {
		
		List<HashMap<String, Object>> recipientList = new ArrayList<HashMap<String, Object>>();
		
		// 도서관 이름 가져오기
		String libname = req.getParameter("libname");
		//System.out.println(libname);
		
		// 해당 도서관에 소속 된 관리자, 사서 보여주기 
		recipientList = service.findRecipientList(libname);
		
		return recipientList;
	}
	
	
	// 선택한 관리자, 사서에게 메세지 전송하기
	@RequestMapping(value="sendMessage.ana", method= {RequestMethod.POST})
	public String sendMessage(HttpServletRequest req, HttpServletResponse res) {
		
		String loginLibrarian = req.getParameter("loginLibrarian");
		String library = req.getParameter("library");
		String recipient = req.getParameter("recipient");
		String messageTitle = req.getParameter("messageTitle");
		String message = req.getParameter("message");
		
		/*
		System.out.println("loginLibrarian : " + loginLibrarian);
		System.out.println("library : " + library);
		System.out.println("recipient : " + recipient);
		System.out.println("messageTitle : " + messageTitle);
		System.out.println("message : " + message);
		*/
		
		int idLength = recipient.length();  
		
		String recipientID = recipient.substring(recipient.indexOf("[")+1, idLength-1);
		
		//System.out.println("recipientID : " + recipientID);
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("loginLibrarian", loginLibrarian);
		paraMap.put("messageTitle", messageTitle);
		paraMap.put("message", message);
		paraMap.put("recipientID", recipientID);
		
		int result = service.sendMessage(paraMap);
		
		if(result != 1) {
			
			String msg = "메세지 전송에 실패했습니다.";
			String loc = "javascript:history.back()";
			
			req.setAttribute("msg", msg);
			req.setAttribute("loc", loc);
			
		}
		else {
			
			String msg = "메세지를 전송 했습니다.";
			String loc = "javascript:history.back()";
			
			req.setAttribute("msg", msg);
			req.setAttribute("loc", loc);
			
		}
		return "msg";
	}

}
