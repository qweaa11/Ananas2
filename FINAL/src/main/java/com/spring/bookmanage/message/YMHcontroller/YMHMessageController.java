package com.spring.bookmanage.message.YMHcontroller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.bookmanage.message.YMHmodel.YMHMessageVO;
import com.spring.bookmanage.message.YMHservice.InterYMHMessageService;

@Component
@Controller
public class YMHMessageController 
{
	@Autowired
	private InterYMHMessageService service;
	
	@RequestMapping(value="/showSendMessage.ana",method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> showSendMessage(HttpServletRequest request, HttpServletResponse response) 
	{	// 보낸 메세지를 보여주는 메소드
		
		String userid = request.getParameter("userid");
		System.out.println("userid: "+userid);
		
		List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
		
		String idx = request.getParameter("idx");
		
		String currentShowPageNo = request.getParameter("currentShowPageNo");
		
		if(currentShowPageNo == null || "".equals(currentShowPageNo))
		{
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 5;	// 한페이지당 5개의 댓글을 보여준다.
		
		/*
			페이지 번호  	 rno1	 rno2
			=========================
			1페이지     		 1       5
			2페이지       	 6		 10
			3페이지		 11		 15
		*/
		int rno1 = Integer.parseInt(currentShowPageNo) * sizePerPage - (sizePerPage-1);		// 공식
		int rno2 = Integer.parseInt(currentShowPageNo) * sizePerPage; 	// 공식
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("IDX", idx);
		paraMap.put("USERID", userid);
		paraMap.put("RNO1", String.valueOf(rno1));
		paraMap.put("RNO2", String.valueOf(rno2));
		
		List<YMHMessageVO> MessageList = service.listMessage(paraMap);
		// 원본 글의 글번호에 대한 댓글중 페이지 번호에 해당하는 댓글만 조회해 온다.
		
		for(YMHMessageVO msgvo :MessageList)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("IDX", msgvo.getIdx());
			map.put("TARTGETID", msgvo.getTartgetid());
			map.put("TARTGETNAME", msgvo.getTartgetname());
			map.put("TITLE", msgvo.getTitle());
			map.put("SENDDATE", msgvo.getSenddate());
			map.put("OPENDATE", msgvo.getOpendate());
			
			mapList.add(map);
		}
		
		return mapList;



		
	}// end of showFieldDetail------------------------------------------------


	@RequestMapping(value="/showReceiveMessage.ana",method= {RequestMethod.GET})
	@ResponseBody
	
	public List<HashMap<String, Object>> showReceiveMessage(HttpServletRequest request, HttpServletResponse response) 
	{	// 보낸 메세지를 보여주는 메소드
		
		String userid = request.getParameter("userid");
		System.out.println("userid: "+userid);
		
		List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
		
		String idx = request.getParameter("idx");
		
		String currentShowPageNo = request.getParameter("currentShowPageNo");
		
		if(currentShowPageNo == null || "".equals(currentShowPageNo))
		{
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 5;	// 한페이지당 5개의 댓글을 보여준다.
		
		/*
			페이지 번호  	 rno1	 rno2
			=========================
			1페이지     		 1       5
			2페이지       	 6		 10
			3페이지		 11		 15
		*/
		int rno1 = Integer.parseInt(currentShowPageNo) * sizePerPage - (sizePerPage-1);		// 공식
		int rno2 = Integer.parseInt(currentShowPageNo) * sizePerPage; 	// 공식
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("IDX", idx);
		paraMap.put("USERID", userid);
		paraMap.put("RNO1", String.valueOf(rno1));
		paraMap.put("RNO2", String.valueOf(rno2));
		
		List<YMHMessageVO> MessageList = service.listReceiveMessage(paraMap);
		// 원본 글의 글번호에 대한 댓글중 페이지 번호에 해당하는 댓글만 조회해 온다.
		
		System.out.println("asd: "+MessageList);
		
		for(YMHMessageVO msgvo :MessageList)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("IDX", msgvo.getIdx());
			map.put("TARTGETID", msgvo.getTartgetid());
			map.put("SENDER", msgvo.getSender());
			map.put("SENDERNAME", msgvo.getSendername());
			map.put("TITLE", msgvo.getTitle());
			map.put("SENDDATE", msgvo.getSenddate());
			map.put("OPENDATE", msgvo.getOpendate());
			
			mapList.add(map);
		}
		
		return mapList;



		
	}// end of showFieldDetail------------------------------------------------


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
