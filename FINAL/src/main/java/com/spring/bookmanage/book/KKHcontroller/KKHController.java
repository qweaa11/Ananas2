package com.spring.bookmanage.book.KKHcontroller;


import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.bookmanage.JDSmodel.LibrarianVO;
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
		
		HttpSession session = request.getSession();
		LibrarianVO librarian = (LibrarianVO)session.getAttribute("loginLibrarian");
		HashMap<String,String> libcode = new HashMap<String,String>();
		if(librarian != null) {
			libcode.put("LIBCODE", librarian.getLibcode_fk());
		}
		
		System.out.println(libcode);
		List<HashMap<String,String>> libraryList = service.findAllLibrary(libcode);
		List<HashMap<String,String>> languageList = service.findAllLanguage(libcode);
		List<HashMap<String,String>> categoryList = service.findAllCategory(libcode);
		List<HashMap<String,String>> fieldList = service.findAllField(libcode);
		
		
		
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
		HttpSession session =request.getSession();
		LibrarianVO librarian = (LibrarianVO)session.getAttribute("loginLibrarian");
		String libcode =""; 
		if(librarian != null)
			libcode = librarian.getLibcode_fk();
		
		
		ArrayList<String> libArr = null;
		ArrayList<String> lanArr = null;
		ArrayList<String> cateArr = null;
		ArrayList<String> fieldArr = null;
		
		if(library != "" && librarian != null) {
			library = "'"+library+"'";
		}else {
			library = "'"+libcode+"'";
		}
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
		parameterMap.put("LIBCODE", libcode);
		//System.out.println(parameterMap.get("LIBRARY"));
		List<KKHBookVO> bookList = null;
		bookList = service.findBookBysidebar(parameterMap);
		
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
		String library = "";
		HttpSession session = request.getSession();
		LibrarianVO librarian = (LibrarianVO)session.getAttribute("loginLibrarian");
		if(librarian != null) {
			library = "'"+librarian.getLibcode_fk()+"'"; 
		}
		
		HashMap<String,String> parameterMap = new HashMap<String,String>();
		parameterMap.put("SEARCHTYPE", searchType);
		parameterMap.put("SEARCHWORD", searchWord);
		parameterMap.put("SORT", sort);
		parameterMap.put("LIBRARY", library);
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
		HashMap<String,String> libcode = new HashMap<String,String>();
		
		List<HashMap<String,String>> bookReservateList = new ArrayList<HashMap<String,String>>();
		List<KKHBookVO> bookDetailList = service.findBookDetail(bookid);
		List<HashMap<String,String>> categoryList = service.findAllCategory(libcode);
		List<HashMap<String,String>> languageList = service.findAllLanguage(libcode);
		List<HashMap<String,String>> genreList = service.findgenre();
		List<HashMap<String,String>> fieldList = service.findfield();
		List<HashMap<String,String>> libraryList = service.findAllLibrary(libcode);
		
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
		request.setAttribute("libraryList", libraryList);
		request.setAttribute("bookid", bookid);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("languageList", languageList);
		request.setAttribute("genreList", genreList);
		request.setAttribute("fieldList", fieldList);
		request.setAttribute("bookDetailList", bookDetailList);
		request.setAttribute("bookReservateList", bookReservateList);
		return "book/bookDetail.tiles1";
	}
	
	@RequestMapping(value="/findDetailField.ana", method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String,String>> findDetailField(HttpServletRequest request){
		
		String bigfcode = request.getParameter("bigfcode");
		
		List<HashMap<String,String>> detailFieldList = service.findDetailField(bigfcode);
		
		
		return detailFieldList;
	}
	
	
	@RequestMapping(value="editPublicBookInfo.ana",method= {RequestMethod.POST})
	public String editPublicBookInfo(HttpServletRequest request,HttpServletResponse response) {
		
		HashMap<String,String> parameterMap = new HashMap<String,String>();
		String editTitle = request.getParameter("editTitle");
		String editAuthor = request.getParameter("editAuthor");
		String editLanguage = request.getParameter("editLanguage");
		String editCategory = request.getParameter("editCategory");
		String editField = request.getParameter("editField");
		String editGenre = request.getParameter("editGenre");
		String bookid = request.getParameter("bookid");
		KKHBookVO book = service.findOneBook(bookid);
		if(!editLanguage.equals(book.getLcode_fk()) || !editCategory.equals(book.getCcode_fk()) || !editField.equals(book.getFcode_fk())|| !editGenre.equals(book.getGcode_fk())) {
			//코드가 변경된 것이 있을경우
			
			
		}
		
		return "book/bookDetail.tiles1";
	}
}