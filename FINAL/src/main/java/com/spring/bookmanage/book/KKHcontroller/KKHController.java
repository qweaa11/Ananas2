package com.spring.bookmanage.book.KKHcontroller;


import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.bookmanage.book.KKHmodel.KKHBookVO;
import com.spring.bookmanage.book.KKHservice.InterKKHBookService;
import com.spring.bookmanage.common.AES256;


@Controller
public class KKHController {
	
	@Autowired
	private InterKKHBookService service;
	
	@Autowired
	private AES256 aes;
	
	@RequestMapping(value="/bookList.ana",method= {RequestMethod.GET})
	/** bookList.jsp 페이지로 이동하는 메소드
	 * 
	 * @return
	 */
	public String bookList(HttpServletRequest request, HttpServletResponse response) {

		List<HashMap<String,String>> libraryList = service.findAllLibrary();
		List<HashMap<String,String>> languageList = service.findAllLanguage();
		List<HashMap<String,String>> categoryList = service.findAllCategory();
		List<HashMap<String,String>> fieldList = service.findAllField();
		
		request.setAttribute("languageList", languageList);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("fieldList", fieldList);
		request.setAttribute("libraryList", libraryList);
		return "book/bookList.tiles1";
	}// end of bookList
	
	@RequestMapping(value="/KKHfindBookBySidebar.ana", method= {RequestMethod.GET})
	@ResponseBody
	/**
	 * ajax로 library,field,category,language, sort 값을 받아와서 조건을 검색해 책 목록을 가져오는 메소드
	 * @param request
	 * @param response
	 * @return List<HashMap<String,Object>>
	 */
	public List<HashMap<String,Object>> findBookBySidebar(HttpServletRequest request, HttpServletResponse response){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
		
		String library = request.getParameter("library");
		String language = request.getParameter("language");
		String category = request.getParameter("category");
		String field = request.getParameter("field");
		String sort = request.getParameter("sort");
		ArrayList<String> libArr = null;
		ArrayList<String> lanArr = null;
		ArrayList<String> cateArr = null;
		ArrayList<String> fieldArr = null;
		
		if(library != "") library = "'"+library+"'";
		if(language != "") language = "'"+language+"'";
		if(category != "") category = "'"+category+"'";
		if(field != "") field = "'"+field+"'";
		
		/*System.out.println("//////////////");
		System.out.println(language);
		System.out.println("/////////////");
		if(library.indexOf(",") != -1) libArr = new ArrayList<String>(Arrays.asList(library.split(",")));
		else if(library.indexOf(",") == -1 && library != "" ) {
			libArr = new ArrayList<String>();
			libArr.add(library);
		}
		if(language.indexOf(",") != -1) lanArr = new ArrayList<String>(Arrays.asList(language.split(",")));
		else if(language.indexOf(",") == -1 && language != "") {
			lanArr = new ArrayList<String>();
			lanArr.add(language);
		}
		if(category.indexOf(",") != -1) cateArr = new ArrayList<String>(Arrays.asList(category.split(",")));
		else if(category.indexOf(",") == -1 && category != "") {
			cateArr = new ArrayList<String>();
			cateArr.add(category);
		}
		if(field.indexOf(",") != -1) fieldArr = new ArrayList<String>(Arrays.asList(field.split(",")));
		else if(field.indexOf(",") == -1 && field != "" ) {
			fieldArr = new ArrayList<String>();
			fieldArr.add(field);
		}*/
		
	
		//System.out.println("library=>"+library+",  language=>"+language+",  category=>"+category+",  field=>"+field);
		HashMap<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("LIBRARY", library);
		parameterMap.put("LANGUAGE", language);
		parameterMap.put("CATEGORY", category);
		parameterMap.put("FIELD", field);
		parameterMap.put("SORT", sort);
		//System.out.println(parameterMap.get("LIBRARY"));
		List<KKHBookVO> bookList = null;
		bookList = service.findBookBysidebar(parameterMap);
		System.out.println("implements 왔다감 by 구현");
		
		for(KKHBookVO bookvo : bookList) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("BOOKID", bookvo.getBookid());
			map.put("IDX", bookvo.getIdx());
			map.put("TITLE", bookvo.getTitle());
			map.put("AUTHOR", bookvo.getAuthor());
			map.put("STATUS", bookvo.getStatus());
			map.put("AGECODE", bookvo.getTranseAgecode());
			map.put("GCODE", bookvo.getGcode_fk());
			map.put("GNAME", bookvo.getGname());
			map.put("NCODE", bookvo.getNcode_fk());
			map.put("NNAME", bookvo.getNname());
			map.put("LCODE", bookvo.getLcode_fk());
			map.put("LNAME", bookvo.getLname());
			map.put("FCODE", bookvo.getFcode_fk());
			map.put("FNAME", bookvo.getFname());
			map.put("CCODE", bookvo.getCcode_fk());
			map.put("CNAME", bookvo.getCname());
			map.put("LIBCODE", bookvo.getLibcode_fk());
			map.put("LIBNAME", bookvo.getLibname());
			map.put("PUBCODE", bookvo.getPubcode_fk());
			map.put("PUBNAME", bookvo.getPubname());
			map.put("COUNT",  bookvo.getCount());
			
			resultList.add(map);
		}
		
		return resultList;
	}
	
	@RequestMapping(value="/KKHfindBookBySearchbar.ana", method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String,Object>> findBookBySearchbar(HttpServletRequest request, HttpServletResponse response){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
		
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		String sort = request.getParameter("sort");
		
		HashMap<String,String> parameterMap = new HashMap<String,String>();
		parameterMap.put("SEARCHTYPE", searchType);
		parameterMap.put("SEARCHWORD", searchWord);
		parameterMap.put("SORT", sort);
		List<KKHBookVO> bookList = service.findBookBySearchbar(parameterMap);
		
		for(KKHBookVO bookvo : bookList) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("BOOKID", bookvo.getBookid());
			map.put("IDX", bookvo.getIdx());
			map.put("TITLE", bookvo.getTitle());
			map.put("AUTHOR", bookvo.getAuthor());
			map.put("STATUS", bookvo.getStatus());
			map.put("AGECODE", bookvo.getTranseAgecode());
			map.put("GCODE", bookvo.getGcode_fk());
			map.put("GNAME", bookvo.getGname());
			map.put("NCODE", bookvo.getNcode_fk());
			map.put("NNAME", bookvo.getNname());
			map.put("LCODE", bookvo.getLcode_fk());
			map.put("LNAME", bookvo.getLname());
			map.put("FCODE", bookvo.getFcode_fk());
			map.put("FNAME", bookvo.getFname());
			map.put("CCODE", bookvo.getCcode_fk());
			map.put("CNAME", bookvo.getCname());
			map.put("LIBCODE", bookvo.getLibcode_fk());
			map.put("LIBNAME", bookvo.getLibname());
			map.put("PUBCODE", bookvo.getPubcode_fk());
			map.put("PUBNAME", bookvo.getPubname());
			map.put("COUNT",bookvo.getCount());
			
			resultList.add(map);
		}
		return resultList;
	}
	
	
	@RequestMapping(value="/bookDetail.ana",method= {RequestMethod.GET})
	public String bookDetail(HttpServletRequest request, HttpServletResponse response) {
		String bookid = request.getParameter("bookid");
		System.out.println("bookid:"+bookid);
		List<HashMap<String,String>> bookReservateList = new ArrayList<HashMap<String,String>>();
		List<KKHBookVO> bookDetailList = service.findBookDetail(bookid);
		
		List<HashMap<String,String>> bookbridgeList =  service.findBookReservateList(bookid);
		try {
			for(HashMap<String,String> map : bookbridgeList) {
				HashMap<String,String> resultMap = new HashMap<String,String>();
				resultMap.put("BOOKID",map.get("BOOKID"));
				resultMap.put("TITLE",map.get("TITLE"));
				resultMap.put("ISBN", map.get("ISBN"));
				resultMap.put("STATUS", map.get("STATUS"));
				resultMap.put("MEMBERREGDATE", map.get("MEMBERREGDATE"));
				resultMap.put("MEMBERID", map.get("MEMBERID"));
				resultMap.put("RESERVEDATE", map.get("RESERVEDATE"));
				resultMap.put("NAME", map.get("NAME"));
				resultMap.put("BOOKREGDATE", map.get("BOOKREGDATE"));
				resultMap.put("PHONE",aes.decrypt(map.get("PHONE")) );
				bookReservateList.add(resultMap);
			}
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			e.printStackTrace();
		}
		request.setAttribute("bookDetailList", bookDetailList);
		request.setAttribute("bookReservateList", bookReservateList);
		return "book/bookDetail.tiles1";
	}
}