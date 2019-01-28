
package com.spring.bookmanage.book.KKHcontroller;


import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.bookmanage.JDSmodel.LibrarianVO;
import com.spring.bookmanage.book.KKHmodel.KKHBookVO;
import com.spring.bookmanage.book.KKHservice.InterKKHBookService;
import com.spring.bookmanage.common.AES256;
import com.spring.bookmanage.common.FileManager;


@Controller
public class KKHController {
	
	@Autowired
	private InterKKHBookService service;
	
	@Autowired
	private AES256 aes;
	
	@Autowired
	private FileManager  fileManager;
	
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
		
		if(librarian != null) {
			library = "'"+libcode+"'";
			System.out.println("1111");
		}else if(library != "" && librarian == null){
			library = "'"+library+"'";
			System.out.println("2222");
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
			library = librarian.getLibcode_fk(); 
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
		int length = bookDetailList.size();
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
		request.setAttribute("length", length);    
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
	public String editPublicBookInfo(KKHBookVO bookvo,MultipartHttpServletRequest request,HttpServletResponse response) {
		
		HashMap<String,String> parameterMap = new HashMap<String,String>();
		String editAgecode = request.getParameter("editAgecode");
		String editTitle = request.getParameter("editTitle");
		String editAuthor = request.getParameter("editAuthor");
		String editNation = request.getParameter("editNation");
		String editLibrary = request.getParameter("editLibrary");
		String editLanguage = request.getParameter("editLanguage");
		String editCategory = request.getParameter("editCategory");
		String editField = request.getParameter("editField");
		String editGenre = request.getParameter("editGenre");
		String editImage = request.getParameter("editImage");
		String length = request.getParameter("bookListLength");
		int n=0;
		MultipartFile attach = bookvo.getEditImage();
		
		if(!attach.isEmpty()) {// 파일이 있으면 참(true), 없으면 거짓(false)
			
			/* 1 사용자가 보낸 파일을 WAS(톰캣)의 특정 폴더에 저장해주어야 한다.
			 >>>> 파일이 업로드 되어질 특정 경로(폴더)지정해주기
			 	우리는 WAS의 webapp/resources/files 폴더에 저장하겠다.*/
			
			//WAS의 webapp의 절대 경로를 알아와야 한다.
			HttpSession session = request.getSession();
			String root = "C:\\Users\\user1\\git\\Ananas2\\FINAL\\src\\main\\webapp\\";
					
			String path = root +"resources"+File.separator+"files";
			//path 가 첨부파일\들을 저장할 WAS(톰캣)의 폴더가 된다.
			
			System.out.println("path:"+path);
			//확인용 C:\springworkspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Board\resources\files
			
			
			//2. 파일 첨부를 위한 변수의 설정 및 값을 초기화한 후 파일 올리기
			String newFileName = "";
			//WAS(톰캣) 디스크에 저장할 파일명 
			
			byte[]bytes = null;
			//첨부파일을 WAS 디스크에 저장할때 사용되는 용도
			long fileSize = 0;
			//파일 크기를 알아오는 용도
			
			try {
				bytes = attach.getBytes();
				// getBytes() 는 첨부된 파일을 바이트 단위로 다 읽어오는 것이다.
				newFileName = fileManager.doFileUpload(bytes,attach.getOriginalFilename(), path);
				//첨부된 파일을 WAS의 디스크로 파일업로드를 한다.
				//파일을 업로드한후, 20190107091234536463453.png 이런식으로 파일이름을 받아온다.
				System.out.println("newFileName:"+newFileName);
				
				//3.BoardVO boardvo 에 fileName, orgFileName, fileSize 값을 넣어줘야 한다.
				// (왜냐면 add.jsp에서 넘긴값이 attach밖에 없기때문)
				bookvo.setFileName(newFileName);
				fileSize = attach.getSize();
				bookvo.setFileSize(String.valueOf(fileSize));
						
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.out.println("eidtIma:"+editImage);
		String bookid = request.getParameter("bookid");
		KKHBookVO book = service.findOneBook(bookid);
		parameterMap.put("BOOKID", bookid);
		parameterMap.put("EDITAGECODE", editAgecode);
		parameterMap.put("LENGTH", length);
		parameterMap.put("EDITTITLE", editTitle);
		parameterMap.put("EDITAUTHOR", editAuthor);
		if(!(editImage =="" || editImage == null)) {
			parameterMap.put("EDITIMAGE", editImage);
		}
		if(!editLibrary.equals(book.getLibcode_fk()) || !editNation.equals(book.getNcode_fk()) || !editLanguage.equals(book.getLcode_fk()) || !editCategory.equals(book.getCcode_fk()) || !editField.equals(book.getFcode_fk())|| !editGenre.equals(book.getGcode_fk())) {
			
			parameterMap.put("EDITLIBRARY",editLibrary);
			parameterMap.put("EDITNATION",editNation);
			parameterMap.put("EDITLANGUAGE", editLanguage);
			parameterMap.put("EDITCATEGORY", editCategory);
			parameterMap.put("EDITFIELD", editField);
			parameterMap.put("EDITGENRE", editGenre);
			n = service.editBookPlzChangeBookid(parameterMap,book);
			
		}else {
			n = service.eidtBookNoChangeBookid(parameterMap,book);
		}
		
		if(n != 1) {
			String msg = "수정 실패";
			String loc = "javascript:history.back()";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			
			
		}else {
			String msg = "수정 성공";
			String loc = "/bookmanage/bookList.ana";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
		}
		return "msg";
	}
	
	@RequestMapping(value="editIndivBookInfo.ana", method= {RequestMethod.POST})
	public String editIndivBookInfo(HttpServletRequest request,HttpServletResponse response) {
		String editISBN = request.getParameter("editISBN");
		String editPrice = request.getParameter("editPrice");
		String editWeight = request.getParameter("editWeight");
		String editTotalPage = request.getParameter("editTotalPage");
		String editPdate = request.getParameter("editPdate");
		String bookid = request.getParameter("bookid");
		
		HashMap<String,String> parameterMap = new HashMap<String,String>();
		parameterMap.put("EDITISBN", editISBN);
		parameterMap.put("EDITPRICE", editPrice);
		parameterMap.put("EDITWEIGHT", editWeight);
		parameterMap.put("EDITTOTALPAGE", editTotalPage);
		parameterMap.put("EDITPDATE", editPdate);
		parameterMap.put("BOOKID", bookid);
		
		int n = service.editIndivBookInfo(parameterMap);
		
		if(n != 1) {
			String msg = "수정 실패";
			String loc = "javascript:history.back()";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			
			
		}else {
			String msg = "수정 성공";
			String loc = "/bookmanage/bookDetail.ana?bookid="+bookid.substring(0,bookid.indexOf("-",16));
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
		}
		return "msg";
	}
	
	@RequestMapping(value="deleteIndivBook.ana",method= {RequestMethod.POST})
	public String deleteIndivBook(HttpServletRequest request,HttpServletResponse response) {
		String bookid = request.getParameter("bookid");
		int n = service.deleteIndivBook(bookid);
		if(n != 1) {
			String msg = "수정 실패";
			String loc = "javascript:history.back()";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			
			
		}else {
			String msg = "수정 성공";
			String loc = "/bookmanage/bookDetail.ana?bookid="+bookid.substring(0,bookid.indexOf("-",16));
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
		}
		return "msg";
		
	}
	
	@RequestMapping(value="AddBook.ana",method= {RequestMethod.POST})
	public String AddBook(HttpServletRequest request,HttpServletResponse response) {
		String bookid = request.getParameter("bookid");
		String count_str = request.getParameter("count");
		System.out.println("bookid1:"+bookid);
	/*	try {
		count = Integer.parseInt(count_str);
		}catch(NumberFormatException e) {
			String msg = "추가 실패";
			String loc = "javascript:history.back()";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			return "msg";
		}*/
		int startBookNum = service.findStartBookNum(bookid); 
		HashMap<String,String> parameterMap = new HashMap<String,String>();
		parameterMap.put("BOOKID", bookid);
		parameterMap.put("COUNT", count_str);
		parameterMap.put("STARTBOOKNUM", String.valueOf(startBookNum));
		KKHBookVO bookInfoSample = service.findBookInfoSample(bookid);
		
		int n = service.insertAdditionalBook(bookInfoSample,parameterMap);
		
		if(n != 1) {
			String msg = "추가 실패";
			String loc = "javascript:history.back()";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
		}else {
			String msg = "추가 성공";
			String loc = "/bookmanage/bookDetail.ana?bookid="+bookid;
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
		}
		return "msg";
	
	}
}