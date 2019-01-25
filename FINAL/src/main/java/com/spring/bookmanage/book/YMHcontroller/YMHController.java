package com.spring.bookmanage.book.YMHcontroller;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.bookmanage.book.YMHService.InterYMHBookService;
import com.spring.bookmanage.book.YMHmodel.YMHBookVO;
import com.spring.bookmanage.common.FileManager;


@Controller
@Component
public class YMHController 
{
	@Autowired
	private InterYMHBookService service;
	
	@Autowired
	private FileManager fileManager;
	
	
	
	
	// 도서 등록 창 요청
	@RequestMapping(value="/bookRegister.ana",method= {RequestMethod.GET})
	public String bookRegister(HttpServletRequest request, HttpServletResponse response) {
		
		return "book/bookRegister.tiles1";
	}// end of bookRegister------------------------------------------------
	
	
	// 출판사 조회 페이지 요청
	@RequestMapping(value="/findPublisher.ana",method= {RequestMethod.GET})
	public String findPublisher(HttpServletRequest request, HttpServletResponse response) {
		
		return "findPublisher.notiles";
	}// end of findPublisher()----------------------------------------------
	
	
	
	// 도서등록 완효 요청
	@RequestMapping(value="/bookRegisterEnd.ana",method= {RequestMethod.POST})
	public String bookRegisterEnd(MultipartHttpServletRequest request, HttpServletResponse response, YMHBookVO bookvo) 
	{
		System.out.println("도서 등록 시작!");
		// ===  첨부파일이 있는지 확인하기  === //
		MultipartFile attach = bookvo.getAttach();
		
		if(!attach.isEmpty())
		{// 첨부파일이 있다면
			HttpSession session = request.getSession();
			String root = session.getServletContext().getRealPath("/");
			String path = root + "resources" + File.separator + "files";
			// path 가 첨부파일을 저장할 WAS의 폴더가 된다.
			
			System.out.println("확인용 : path => "+path);
			
			String newFileName = "";
			byte[] bytes = null;
			long fileSize = 0;
			
			try 
			{
				bytes = attach.getBytes();
				
				newFileName = fileManager.doFileUpload(bytes, attach.getOriginalFilename(), path);
				
				System.out.println(">>>확인용 : " + newFileName);
				
				bookvo.setFilename(newFileName);
				bookvo.setOrgFilename(attach.getOriginalFilename());
				
				fileSize = attach.getSize();
				
				bookvo.setFilesize(String.valueOf(fileSize));
				
			}catch (Exception e) {
				e.printStackTrace();
			}
						
		}// end of if()-----------------------------------
		// ===  첨부파일이 있는지 확인하기 끝   === //
		
		
		// ===  도서 등록 시작하기!!!   === //
		/*순서: 	1. 출판사 테이블에 출판사가 존재하는지 확인( 2.존재 하지 않으면 출판사 테이블에 insert)
				3. book 테이블에 insert
				4. book_detail 테이블에 insert*/
		
		int n = 0 ; // 출판사가 있는지 확인하는 변수
		int o = 0 ; // 출판사를 만든뒤 확인하는 변수
		int p = 0 ; // book테이블에 등록한 뒤 리턴값용 변수
		int q = 0 ; // book_Detail테이블에 등록한 뒤 리턴값용 변수
		int count = 0;	// 등록하는 책의 수를 알려주는 변수
		
		// === 1. 출판사 테이블에 출판사가 존재하는지 확인 === //
		System.out.println("출판사 조회시작");
		n = service.isExistPublisher(bookvo);
		System.out.println("출판사가 존재하는가? 존재한다면 1: " + n);
		
		
		if(n == 0) 
		{// 출판사가 존재하지 않는다면 출판사를 등록
			System.out.println("출판사 등록 시작");
			System.out.println("pubName:"+bookvo.getPubName());
			o = service.addPublisher(bookvo);
			System.out.println("출판사 등록 완료: " + n);
		}
		
		count = bookvo.getBookCount();
		System.out.println("반복횟수: " + count);
		
		
		String bookId = bookvo.getLibCode()+bookvo.getNcode_fk()+bookvo.getLcode_fk()+bookvo.getCcode_fk()+bookvo.getFcode_fk()+bookvo.getGcode_fk();
		System.out.println("bookId: "+bookId);
		
		// 도서의 시리얼 넘버중 첫번째 - 전까지 의 count 알아오는 메소드
		int bookIdN = service.getBookIdN( bookId );
		
		if(bookIdN >= 1)
		{
			bookId+= "-" + (bookIdN+1) ;
		}
		else
		{
			bookId+= "-" + "1" ;
		}
		
		
		
		bookvo.setBookId(bookId);	// 알아온 값+(-하이픈)을 vo의 bookId 변수에 저장
		
				
		for(int i=1 ; i<= count ; i++)
		{ 
			// 출판사 등록후 book테이블에 등록
			bookvo.setBookCount(i);
			
			System.out.println("도서 등록 시작");
			p = service.addOneBook(bookvo);
			System.out.println("도서 등록 완료: " + p);
			
			//  book테이블에 등록후 book_Detail 테이블에 등록
			System.out.println("도서상세 등록 시작");
			q = service.addOneBook_detail(bookvo);
			System.out.println("도서상세 등록 완료: " + q);
			
		}
		
		
		
		int result=p*q;
		String loc = "";
		if(p*q >= 1) 
		{	// 등록에 성공하면
			loc = request.getContextPath()+"/bookList.ana";
			
		}
		else
		{	// 등록에 실패한다면
			loc = request.getContextPath()+"/bookRegister.ana";
		}
		
		request.setAttribute("loc", loc);
		request.setAttribute("result", result);

		
		return "book/bookRegisterEnd.tiles1";
	}// end of bookRegisterEnd()----------------------------------------------
		
	
	
	
	// field 테이블에서 값아 알아오기
	@RequestMapping(value="/showFieldDetail.ana",method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, String>> showFieldDetail(HttpServletRequest request, HttpServletResponse response) {
		
		List<HashMap<String, String>> mapList = new ArrayList<HashMap<String, String>>();
		
		String fcode = request.getParameter("fieldCode");
		
		String initFcode = fcode.substring(0, 1);
		
		 
		List<YMHBookVO> fieldList = service.fieldCodeList(initFcode);
		
		for(YMHBookVO bvo : fieldList)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			
			map.put("FCODE", bvo.getFcode_fk());
			map.put("FNAME", bvo.getFname());
			
			
			mapList.add(map);
		}
		
		
		return mapList;
	}// end of showFieldDetail------------------------------------------------

	
	
	// Library 테이블에서 값아 알아오기
	@RequestMapping(value="/showLibrary.ana",method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, String>> showLibrary(HttpServletRequest request, HttpServletResponse response) 
	{
		List<HashMap<String, String>> mapList = new ArrayList<HashMap<String, String>>();
		
		
		
		List<YMHBookVO> libraryList = service.showLibrary();
		
		
		for(YMHBookVO bvo : libraryList)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			
			map.put("LIBCODE", bvo.getLibCode());
			map.put("LIBNAME", bvo.getLibName());
			
			mapList.add(map);
		}
			
		return mapList;
	}// end of showLibrary------------------------------------------------














	
}





















