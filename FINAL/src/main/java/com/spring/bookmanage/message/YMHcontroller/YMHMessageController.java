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
		
		List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
		
		String idx = request.getParameter("idx");
		
		String currentShowPageNo = request.getParameter("currentShowPageNo");
		
		if(currentShowPageNo == null || "".equals(currentShowPageNo))
		{
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 5;	// 한페이지당 5개의 쪽지를 보여준다.
		
		int rno1 = Integer.parseInt(currentShowPageNo) * sizePerPage - (sizePerPage-1);		// 공식
		int rno2 = Integer.parseInt(currentShowPageNo) * sizePerPage; 	// 공식
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("IDX", idx);
		paraMap.put("USERID", userid);
		paraMap.put("RNO1", String.valueOf(rno1));
		paraMap.put("RNO2", String.valueOf(rno2));
		
		List<YMHMessageVO> MessageList = service.listMessage(paraMap);
		
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
	}// end of showSendMessage------------------------------------------------


	@RequestMapping(value="/showReceiveMessage.ana",method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> showReceiveMessage(HttpServletRequest request, HttpServletResponse response) 
	{	// 보낸 메세지를 보여주는 메소드
		
		String userid = request.getParameter("userid");
		
		List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
		
		String idx = request.getParameter("idx");
		
		String currentShowPageNo = request.getParameter("currentShowPageNo");
		
		if(currentShowPageNo == null || "".equals(currentShowPageNo))
		{
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 5;	// 한페이지당 5개의 댓글을 보여준다.
		
		int rno1 = Integer.parseInt(currentShowPageNo) * sizePerPage - (sizePerPage-1);		// 공식
		int rno2 = Integer.parseInt(currentShowPageNo) * sizePerPage; 	// 공식
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("IDX", idx);
		paraMap.put("USERID", userid);
		paraMap.put("RNO1", String.valueOf(rno1));
		paraMap.put("RNO2", String.valueOf(rno2));
		
		List<YMHMessageVO> MessageList = service.listReceiveMessage(paraMap);
		// 원본 글의 글번호에 대한 댓글중 페이지 번호에 해당하는 댓글만 조회해 온다.
		
		
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
	}// end of showReceiveMessage------------------------------------------------


	
	
	@RequestMapping(value="/getMessageTotalPage.ana", method={RequestMethod.GET})
	@ResponseBody
	public HashMap<String, Integer> getMessageTotalPage(HttpServletRequest request, HttpServletResponse response)
	{
		HashMap<String, Integer> returnMap = new HashMap<String, Integer>();
		
		String userid = request.getParameter("userid");
		// 원본글의 글번호를 받아와서 원본에 딸린 댓글의 갯수를 알아오려고 한다.
		System.out.println("userid: "+userid);
		String sizePerPage = request.getParameter("sizePerPage");
		// 한페이지당 보여줄 댓글의 갯수
		System.out.println("sizePerPage: "+sizePerPage);
		
		
		
		if(sizePerPage == null || "".equals(sizePerPage))
		{
			sizePerPage = "10";
		}
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("USERID", userid);
		paraMap.put("SIZEPERPAGE", sizePerPage);
		
		
		// 원본 글의 글번호에 대한 댓글의 총 갯수를 조회해 온다.
		int totalCount = service.getReceiveMessageTotalCount(paraMap);
		
		// -- 총페이지수(totalPage) 구하기
		int totalPage = (int)Math.ceil((double)totalCount/Integer.parseInt(sizePerPage));
		
		System.out.println("totalCount: "+totalCount);
		System.out.println("totalPage: "+totalPage);
		
		returnMap.put("TOTALPAGE", totalPage);
		
		return returnMap;
	}
	




	@RequestMapping(value="/getSendMessageTotalPage.ana", method={RequestMethod.GET})
	@ResponseBody
	public HashMap<String, Integer> getSendMessageTotalPage(HttpServletRequest request, HttpServletResponse response)
	{
		HashMap<String, Integer> returnMap = new HashMap<String, Integer>();
		
		String userid = request.getParameter("userid");
		// 원본글의 글번호를 받아와서 원본에 딸린 댓글의 갯수를 알아오려고 한다.
		System.out.println("userid: "+userid);
		String sizePerPage = request.getParameter("sizePerPage");
		// 한페이지당 보여줄 댓글의 갯수
		System.out.println("sizePerPage: "+sizePerPage);
		
		
		
		if(sizePerPage == null || "".equals(sizePerPage))
		{
			sizePerPage = "5";
		}
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("USERID", userid);
		paraMap.put("SIZEPERPAGE", sizePerPage);
		
		
		// 원본 글의 글번호에 대한 댓글의 총 갯수를 조회해 온다.
		int totalCount = service.getSendMessageTotalCount(paraMap);
		
		// -- 총페이지수(totalPage) 구하기
		int totalPage = (int)Math.ceil((double)totalCount/Integer.parseInt(sizePerPage));
		
		System.out.println("totalPage: "+totalPage);
		
		returnMap.put("TOTALPAGE", totalPage);
		
		return returnMap;
	}
	



	@RequestMapping(value="/deleteReceiveMessage.ana", method={RequestMethod.GET})
	public String deleteReceiveMessage(HttpServletRequest request, HttpServletResponse response)
	{
		String idx = request.getParameter("idx");
		System.out.println("삭제할 idx: "+idx);
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("IDX", idx);
		
		int result = service.deleteReceiveMessage(paraMap);
		
		String msg = "";
		String loc = "";
		
		if(result == 0)
		{
			msg = "글 삭제 실패!!";
			loc = "javascript:history.back();";
		}
		else
		{
			msg = "글 삭제 성공!!";
			loc = request.getContextPath()+"/messageForm.ana?flag1=1";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		return "msg";
	}


	@RequestMapping(value="/deleteSendMessage.ana", method={RequestMethod.GET})
	public String deleteSendMessage(HttpServletRequest request, HttpServletResponse response)
	{
		String idx = request.getParameter("idx");
		System.out.println("삭제할 idx: "+idx);
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("IDX", idx);
		
		int result = service.deleteSendMessage(paraMap);
		
		String msg = "";
		String loc = "";
		
		if(result == 0)
		{
			msg = "글 삭제 실패!!";
			loc = "javascript:history.back();";
		}
		else
		{
			msg = "글 삭제 성공!!";
			loc = request.getContextPath()+"/messageForm.ana?flag2=1";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		return "msg";
	}



	@RequestMapping(value="/showYMHMessage.ana",method= {RequestMethod.GET})
	public String showMessage(HttpServletRequest request, HttpServletResponse response) {
		
		String idx = request.getParameter("idx");
		
		System.out.println("보여줄 메세지 idx: "+idx);
		
		YMHMessageVO mvo = service.listMessage(idx);
		
		if(mvo.getOpendate() == null)
		{
			int n = service.setOpenDate(idx);
		}
		
		
		request.setAttribute("idx", idx);	// 쪽지 번호
		request.setAttribute("mvo", mvo);	// 쪽지
		
		
		return "showMessage.notiles";
	}// end of findPublisher()----------------------------------------------


	

	
	
	
	
	
	
	
	
	
	
	
	
}
