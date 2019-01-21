package com.spring.bookmanage.book.YMHcontroller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.bookmanage.book.YMHmodel.YMHBookVO;


@Controller
@Component
public class YMHController 
{
	// 도서 등록 창 요청
	@RequestMapping(value="/bookRegister.ana",method= {RequestMethod.GET})
	public String bookRegister() {
		
		return "book/bookRegister.tiles1";
	}// end of bookRegister------------------------------------------------
	
	// 출판사 조회 페이지 요청
	@RequestMapping(value="/findPublisher.ana",method= {RequestMethod.GET})
	public String findPublisher() {
		
		return "findPublisher.notiles";
	}// end of findPublisher()----------------------------------------------
	
	
	
	// 도서등록 완효 요청
	@RequestMapping(value="/bookRegisterEnd.ana",method= {RequestMethod.POST})
	public String bookRegisterEnd(YMHBookVO bookvo, MultipartHttpServletRequest req) 
	{
	//	MultipartFile attach = bookvo.getAttach();
		
		
		
		
		
		
		
		return "findPublisher.notiles";
	}// end of findPublisher()----------------------------------------------
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}





















