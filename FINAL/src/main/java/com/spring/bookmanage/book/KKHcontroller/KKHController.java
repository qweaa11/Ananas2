
package com.spring.bookmanage.book.KKHcontroller;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.PatternSyntaxException;

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

import com.spring.bookmanage.JDSmodel.AdminVO;
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
	 * 	library, language,category, field의 값을 DB에서 가져와서 사이드바에 출력해준다.
	 * @param request
	 * @param response
	 * @return
	 */
	public String bookList(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		LibrarianVO librarian = (LibrarianVO)session.getAttribute("loginLibrarian");
		HashMap<String,String> libcode = new HashMap<String,String>();
		if(librarian != null) { 
			//사서로 접속한 경우, 각각의 language, category, field,library 값에 접속한 사서의 도서관코드(libcode) 를 넣어서 해당 도서관의 장서수만 표시하도록 한다.
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
	 * 조건 검색 사이드바에서 ajax로 library,field,category,language, sort 값을 받아와서 조건을 검색해 책 목록을 가져오는 메소드
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
			// 사서로 접속한 경우 사서의 도서관코드(libcode)를 검색조건에 넣어주도록 한다.
			libcode = librarian.getLibcode_fk();
		
		
		ArrayList<String> libArr = null;
		ArrayList<String> lanArr = null;
		ArrayList<String> cateArr = null;
		ArrayList<String> fieldArr = null;
		
		if(librarian != null) { 
			//사서로 접속했을경우 도서검색 조건중 도서관을 사서의 도서관코드(libcode) 로 고정시킨다.
			library = "'"+libcode+"'";
			
		}else if(library != "" && librarian == null){
			//총관리자로 접속했을때 사이드바를 이용해 도서관들을 조건에 넣어 검색할 경우,조건에 넣은 도서관코드들을 library 에 넣어준다.
			library = "'"+library+"'";
			
		}// 총관리자로 접속하고 도서관 조건을 입력하지 않은 경우 그냥 아무 값도 넣지 않는다.
		
		if(language != "") language = "'"+language+"'";
		if(category != "") category = "'"+category+"'";
		if(field != "") field = "'"+field+"'";
		
		//System.out.println("library=>"+library+",  language=>"+language+",  category=>"+category+",  field=>"+field);
		
		HashMap<String,Object> parameterMap = new HashMap<String,Object>();
		//사이드바의 조건으로 받은 값들을 해쉬맵에 넣어준다.
		parameterMap.put("LIBRARY", library);
		parameterMap.put("LANGUAGE", language);
		parameterMap.put("CATEGORY", category);
		parameterMap.put("FIELD", field);
		parameterMap.put("SORT", sort);
		parameterMap.put("LIBCODE", libcode);
		
		//System.out.println(parameterMap.get("LIBRARY"));
		List<KKHBookVO> bookList = null;
		//해당 조건을 이용해 도서리스트를 가져오는 service 메소드
		bookList = service.findBookBysidebar(parameterMap);
		
		for(KKHBookVO bookvo : bookList) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			//받은 리스트들을 해쉬맵에 담아서 넘겨준다.
			//왜냐면 이거 @ResponseBody 선언 해줫는데 리스트를 VO로 받아버려서 그럼;;;;;
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
		
		//@ResponseBody 선언된 ajax이므로 그냥 해쉬맵을 리턴해준다.
		return resultList;
	}
	
	@RequestMapping(value="/KKHfindBookBySearchbar.ana", method= {RequestMethod.GET})
	@ResponseBody
	/**
	 * 검색창을 이용해 도서리스트를 가져오는 ajax 
	 * @param request
	 * @param response
	 * @return List<HashMap<String,Object>>
	 */
	public List<HashMap<String,Object>> findBookBySearchbar(HttpServletRequest request, HttpServletResponse response){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
		String searchType = request.getParameter("searchType");	//검색타입
		String searchWord = request.getParameter("searchWord"); //검색어
		String sort = request.getParameter("sort");			    //정렬조건
		String library = "";
		HttpSession session = request.getSession();
		LibrarianVO librarian = (LibrarianVO)session.getAttribute("loginLibrarian");
		if(librarian != null) {
			//사서가 접속한 경우 도서관 코드를 검색조건에 추가해준다.
			library = librarian.getLibcode_fk(); 
		}
		
		HashMap<String,String> parameterMap = new HashMap<String,String>();
		parameterMap.put("SEARCHTYPE", searchType);
		parameterMap.put("SEARCHWORD", searchWord);
		parameterMap.put("SORT", sort);
		parameterMap.put("LIBRARY", library);
		List<KKHBookVO> bookList = service.findBookBySearchbar(parameterMap);
		
		for(KKHBookVO bookvo : bookList) {
			// HashMap에 다시담는 이유는 KKHBookVO 로 받았는데 @ResponseBody 로 보내줘야 하기때문....
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
	/**
	 * 도서 상세페이지로 이동하는 컨트롤러, 수정을 위해 종류,분야,도서관,장르 등등을 가져온다.
	 * 이때 받는 bookid 는 두번째 하이푼(-) 이전 까지의 코드이다.
	 * ex)L1000KRE02530UN-1 요까지만 있는 것임.
	 * @param request
	 * @param response
	 * @return List<KKHBookVO>
	 */
	public String bookDetail(HttpServletRequest request, HttpServletResponse response) {
		String bookid = request.getParameter("bookid");
		System.out.println("bookid:"+bookid); 
		HashMap<String,String> libcode = new HashMap<String,String>();
		

		List<HashMap<String,String>> bookReservateList = new ArrayList<HashMap<String,String>>();
		
		//해당 bookid 에 해당하는 도서리스트를 가져오는 메소드
		//L1000KRE02530UN-1
		List<KKHBookVO> bookDetailList = service.findBookDetail(bookid);
		
		int length = bookDetailList.size();
		
		//도서 수정을 위한 각 항목별 리스트 가져오기
		List<HashMap<String,String>> categoryList = service.findAllCategory(libcode);
		List<HashMap<String,String>> languageList = service.findAllLanguage(libcode);
		List<HashMap<String,String>> genreList = service.findgenre();
		List<HashMap<String,String>> fieldList = service.findfield(); // 이때 가져오는 field 정보는 000,100,200 등 큰 field 값이다.
		List<HashMap<String,String>> libraryList = service.findAllLibrary(libcode);
		// 항목 리스트 끝
		
		//도서상세 페이지에서 보여줄 해당도서들 중 예약된 책목록을 가져오는 메소드
		List<HashMap<String,String>> bookbridgeList =  service.findBookReservateList(bookid);
		try {
			for(HashMap<String,String> map : bookbridgeList) {
				HashMap<String,String> resultMap = new HashMap<String,String>();
				resultMap.put("BOOKID",map.get("BOOKID"));
				resultMap.put("TITLE",map.get("TITLE"));
				resultMap.put("ISBN", map.get("ISBN"));
				resultMap.put("MSTATUS", map.get("MSTATUS"));
				resultMap.put("BSTATUS", map.get("BSTATUS"));
				System.out.println("BSTATUS:"+map.get("BSTATUS"));
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
	/**
	 * 도서 공용정보 수정시 세부field 값을 가져오기 위한 ajax 
	 * 큰 field 값(000,100,200 ... 900) 등의 값을 받아오고난 뒤
	 * 그에 해당하는 하위 field값( 100 일경우 >> 110,120,130... 180,190) 을 List로 가져온다.
	 * @param request
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>> findDetailField(HttpServletRequest request){
		
		String bigfcode = request.getParameter("bigfcode");//큰 field 값을 받아오고
		
		//그걸 이용해 하위 field 값을 가져온다.
		List<HashMap<String,String>> detailFieldList = service.findDetailField(bigfcode);
		
		
		return detailFieldList;
	}
	
	
	/**
	 * 도서 공용 정보 수정을 위한 매핑
	 * Agecode,title,author,image
	 * nation,library,language,field,genre 등을 수정한다.
	 * 기존의 것과 동일할 경우엔 수정 안함.
	 * @param bookvo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editPublicBookInfo.ana",method= {RequestMethod.POST})
	public String editPublicBookInfo(KKHBookVO bookvo,MultipartHttpServletRequest request,HttpServletResponse response) {
		
		HashMap<String,String> parameterMap = new HashMap<String,String>();
		String editAgecode = request.getParameter("editAgecode"); 	// 연령코드
		String editTitle = request.getParameter("editTitle");		// 도서명
		String editAuthor = request.getParameter("editAuthor");		// 저자/역자
		String editNation = request.getParameter("editNation");		// 국가코드
		String editLibrary = request.getParameter("editLibrary");	// 도서관코드
		String editLanguage = request.getParameter("editLanguage");	// 언어코드
		String editCategory = request.getParameter("editCategory");	// 종류코드
		String editField = request.getParameter("editField");		// 분야코드
		String editGenre = request.getParameter("editGenre");		// 장르코드
		String editImage = request.getParameter("editImage");		// 도서 이미지
		String length = request.getParameter("bookListLength");		// 수정해야할 도서 갯수
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
		
		//변경할 도서와 기존 도서의 정보를 비교하기 위해 해당 도서번호(두번째하이푼[-] 이전까지의 코드) 를 가진 도서 1개의 정보를 가져온다.
		KKHBookVO book = service.findOneBook(bookid);
		
		parameterMap.put("BOOKID", bookid);
		parameterMap.put("EDITAGECODE", editAgecode);
		parameterMap.put("LENGTH", length);
		parameterMap.put("EDITTITLE", editTitle);
		parameterMap.put("EDITAUTHOR", editAuthor);
		
		if(!(editImage =="" || editImage == null)) {
			//변경할 이미지가 없으면 HashMap에 넣어주지 않는다.
			parameterMap.put("EDITIMAGE", editImage);
		}
		if(!editLibrary.equals(book.getLibcode_fk()) || !editNation.equals(book.getNcode_fk()) || !editLanguage.equals(book.getLcode_fk()) || !editCategory.equals(book.getCcode_fk()) || !editField.equals(book.getFcode_fk())|| !editGenre.equals(book.getGcode_fk())) {
			//도서관코드, 국가코드, 언어코드, 종류코드, 분야코드, 장르코드 중 하나라도 다를경우 도서코드를 변경해주어야한다.
			//그때문에 위의 조건을 만족할 경우에만 HashMap에 담아서 service로 보낸다.
			parameterMap.put("EDITLIBRARY",editLibrary);
			parameterMap.put("EDITNATION",editNation);
			parameterMap.put("EDITLANGUAGE", editLanguage);
			parameterMap.put("EDITCATEGORY", editCategory);
			parameterMap.put("EDITFIELD", editField);
			parameterMap.put("EDITGENRE", editGenre);
			//새롭게 도서일련번호를 부여하고자 하는 메소드 실행.
			n = service.editBookPlzChangeBookid(parameterMap,book);
			
		}else {
			//도서코드를 변경할 필요가 없는경우 아래의 service 메소드를 실행한다.
			//이경우 HashMap엔 bookid(두번째 하이푼[-]이전까지의 값), agecode,length, title, author 값만 들어있음
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
	
	/**
	 * 개별 도서정보를 수정하는 매핑
	 * ISBN,Price,Weight,TotalPage, Pdate(등록일자) 를 수정한다.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editIndivBookInfo.ana", method= {RequestMethod.POST})
	public String editIndivBookInfo(HttpServletRequest request,HttpServletResponse response) {
		String editISBN = request.getParameter("editISBN");			  	//ISBN
		String editPrice = request.getParameter("editPrice");			//Price
		String editWeight = request.getParameter("editWeight");			//Weight
		String editTotalPage = request.getParameter("editTotalPage");	//TotalPage
		String editPdate = request.getParameter("editPdate");			//Pdate
		String bookid = request.getParameter("bookid");					//bookid
		
		HashMap<String,String> parameterMap = new HashMap<String,String>();
		parameterMap.put("EDITISBN", editISBN);
		parameterMap.put("EDITPRICE", editPrice);
		parameterMap.put("EDITWEIGHT", editWeight);
		parameterMap.put("EDITTOTALPAGE", editTotalPage);
		parameterMap.put("EDITPDATE", editPdate);
		parameterMap.put("BOOKID", bookid);
		
		//개별 도서 정보수정  메소드
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
	
	/**
	 * 개별 도서를 삭제(테이블에서 delete 시켜버림)하는 매핑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/deleteIndivBook.ana",method= {RequestMethod.POST})
	public String deleteIndivBook(HttpServletRequest request,HttpServletResponse response) {
		String bookid = request.getParameter("bookid");
		//그냥 delete 함
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
	
	/**
	 * 해당 도서를 몇권 추가해주는 매핑
	 * 추가되는 도서의 정보는 기존 도서들중 첫번째 도서의 정보들로 추가된다.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/AddBook.ana",method= {RequestMethod.POST})
	public String AddBook(HttpServletRequest request,HttpServletResponse response) {
		String bookid = request.getParameter("bookid");
		String count_str = request.getParameter("count");
		
		//추가될 도서에 부여될 끝부분 이일련번호 시작숫자를 가져오는 메소드
		//ex) L1000KRE02530UN-1-15가 기존에 존재하는 도서번호중 마지막 번호라면 16을 가져온다.
		int startBookNum = service.findStartBookNum(bookid); 
		HashMap<String,String> parameterMap = new HashMap<String,String>();
		parameterMap.put("BOOKID", bookid);								//추가될 도서의 큰 일련번호 ex)L1000KRE02530UN-1
		parameterMap.put("COUNT", count_str);							//추가할 도서수
		parameterMap.put("STARTBOOKNUM", String.valueOf(startBookNum));	//끝자리 일련번호 시작숫자
		
		//추가될 도서에 입력될 도서정보를 가져오는 메소드
		KKHBookVO bookInfoSample = service.findBookInfoSample(bookid);
		
		//도서를 해당 일련버호로 추가하는 메소드
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
	
	/**
	 * 해당 일련번호를 가진 모든 책을 book,book_detail 테이블에서 삭제하고 delete_book 테이블로 옮기는 매핑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/deleteAllBook.ana",method= {RequestMethod.POST})
	public String deleteAllBook(HttpServletRequest request, HttpServletResponse response) {
		String bookid = request.getParameter("bookid");	//삭제할 도서일련번호(큰번호까지) ex)L1000KRE02530UN-1
		String cleanerid = "";
		HttpSession session = request.getSession();
		if(session.getAttribute("loginLibrarian")!= null) {
			LibrarianVO loginLibrarian = (LibrarianVO)session.getAttribute("loginLibrarian");
			cleanerid = loginLibrarian.getLibid();
		}else if(session.getAttribute("loginAdmin")!= null) {
			AdminVO loginAdmin = (AdminVO)session.getAttribute("loginAdmin");
			cleanerid = loginAdmin.getAdminid();
		}
		//삭제할 도서의 정보를 가져오는 메소드
		List<KKHBookVO> deleteBookList = service.findDeleteBook(bookid);
		
		//삭제한 도서들의 정보를 delete_book 테이블에 insert 해주는 메소드
		int n = service.insertAndDeleteBookList(deleteBookList,bookid,cleanerid);
		
		if(n != 1) {
			String msg = "삭제 실패";
			String loc = "javascript:history.back()";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
		}else {
			String msg = "삭제 성공";
			String loc = "/bookmanage/bookList.ana";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
		}
		return "msg";
	}
	
	
	/**
	 * 도서 반납기한을 연장(+7일) 하는 매핑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/extendBookList.ana",method = {RequestMethod.POST})
	public String extendBookList(HttpServletRequest request, HttpServletResponse response) {
		String bookid = request.getParameter("bookid");
		String extendBookid = request.getParameter("extendBookid");
		
		
			String[] extendBookArr = extendBookid.split(",");
			System.out.println(extendBookArr[0]);
			//반납예정일을 +7일 해주는 메소드
			String[] extendSuccessBook = service.updateDeadline(extendBookArr);
			
			
			if(extendSuccessBook.length < 1) { 
				String msg = "연장 실패";
				String loc = "javascript:history.back()";
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
				
			}else {
				String msg = "";
				for(int i=0; i<extendSuccessBook.length;i++) {
					System.out.println(extendSuccessBook[i]);
					if(!extendSuccessBook[i].isEmpty())	msg += extendSuccessBook[i]+",";
				}
				msg = msg.substring(0, msg.length()-1)+" 도서 연장 성공!!";
				String loc = "/bookmanage/bookDetail.ana?bookid="+bookid;
				System.out.println("extendBookListAsBookid:"+bookid);
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
			}
		
		return "msg";
	}
	
	/**
	 * 대여된 도서를 반납하는 매핑
	 * 반납시 도서status를 에약이 있을땐 2 없을땐 0 으로 바꾸고
	 * returned 테이블에 정보를 insert 해준뒤,
	 * rental 테이블의 해당 도서정보를 delete 해준다.
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value="/returnBookList.ana", method = {RequestMethod.POST})
	public String returnBookList(HttpServletRequest request, HttpServletResponse response) {
		String returnBookid = request.getParameter("returnBookid");
		String bookid = request.getParameter("bookid");
		int n=0;
		//try {
			String[] returnBookidArr = returnBookid.split(",");
			//도서번호에 해당하는 책을 반납처리하는 메소드
			String[] returnSuccessBook = service.returnBook(returnBookidArr);
			
			if(returnSuccessBook.length < 1) { 
				String msg = "반납 실패";
				String loc = "javascript:history.back()";
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
			}else {
				String msg = "";
				for(int i=0; i<returnSuccessBook.length;i++) {
					System.out.println(returnSuccessBook[i]);
					if(!returnSuccessBook[i].isEmpty())	msg += returnSuccessBook[i]+",";
				}
				msg = msg.substring(0, msg.length()-1)+" 도서 반납 되었습니다.";
				String loc = "/bookmanage/bookDetail.ana?bookid="+bookid;
				System.out.println("returnSuccessBook:"+bookid);
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
			}
			
		return "msg";
	}
	
	/**
	 * 예약된 책을 예약취소 처리하는 매핑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/reserveCancel.ana",method= {RequestMethod.GET})
	public String reserveCancel(HttpServletRequest request, HttpServletResponse response) {
		String bookid = request.getParameter("bookid");
		String cancelBookid = request.getParameter("cancelBookid");
		
		//예약취소시키는 메소드
		int n = service.reserveCancel(cancelBookid);
		if(n != 1) {
			String msg = "예약 취소실패";
			String loc = "javascript:history.back()";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			
		}else {
			String msg = "예약이 취소되었습니다.";
			String loc = "/bookmanage/bookDetail.ana?bookid="+bookid;
			System.out.println("reserveCancel:"+bookid);
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
		}
		
		return "msg";
	}

}