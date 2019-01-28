package com.spring.bookmanage.board.PMGcontroller;

import java.io.File;
import java.util.ArrayList;
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
import com.spring.bookmanage.board.PMGmodel.PMGNoticeVO;
import com.spring.bookmanage.board.PMGservice.PMGBoardService;
import com.spring.bookmanage.common.AES256;
import com.spring.bookmanage.common.FileManager;
import com.spring.bookmanage.common.MyUtil;

@Controller
public class PMGBoardController {

	//===== 의존객체 주입하기(DI : Dependency Injection) =====
	@Autowired
	private PMGBoardService service;
	
	//===== 양방향 암호화 알고리즘인 AES256를 사용하여 암호화/복호화 하기 위한 클래스 의존객체 주입하기 (DI : Dependency Injection) =====
	@Autowired
	private AES256 aes;
	
	//===== 파일업로드 및 파일다운로드를 해주는 FileManager 클래스 의존객체 주입하기 (DI : Dependency Injection) =====
	@Autowired
	private FileManager fileManager;
	
	
	// *** 공지사항 목록 ***
	@RequestMapping(value="/noticeList.ana", method= {RequestMethod.GET})
	public String noticeList(HttpServletRequest request, HttpServletResponse response) {
		
		request.getSession().getAttribute("loginLibrarian"); // status => 등급(일반사서 0,도서관장 1)
		request.getSession().getAttribute("loginAdmin");	// 총괄관리자
		
	/*	List<PMGNoticeVO> noticeList = null;
		
		// 검색어가 포함
		String colname = request.getParameter("colname");
		String search = request.getParameter("search");
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("COLNAME", colname);
		paraMap.put("SEARCH", search);
		
		// 페이징 처리
		String str_currentShowPageNo = request.getParameter("currentShowPageNo");
		
		int totalCount = 0; 		// 총게시물 건수
		int sizePerPage = 10; 		// 한 페이지당 보여줄 게시물 건수
		int currentShowPageNo = 0;	// 현재 보여주는 페이지번호로서, 초기치로는 1페이지로 설정함.
		int totalPage = 0; 			// 총 페이지수(웹브라우저상에 보여줄 총 페이지 갯수)
		
		int startRno = 0;			// 시작 행 번호
		int endRno = 0;				// 끝 행 번호
		
		int blockSize = 10;			// "페이지바" 에 보여줄 페이지의 갯수
		
		if(search != null &&
			!search.trim().equals("") &&
			!search.trim().equals("null")) {
			// 검색이 있는 경우(페이징 처리 한 것임)
			totalCount = service.getTotalCountWithSearch(paraMap);
		}
		
		else {
			// 검색이 없는 경우(페이징 처리 한 것임)
			totalCount = service.getTotalCountNoSerach();
		}
		totalPage = (int)Math.ceil((double)totalCount/sizePerPage);
		
		if(str_currentShowPageNo == null) {
			// 게시판 초기화면일 경우			
			currentShowPageNo = 1;
		}
		else {
			// 특정페이지를 보고자 조회한 경우
			try {
				currentShowPageNo = Integer.parseInt(str_currentShowPageNo); 
				
				if(currentShowPageNo < 1 || currentShowPageNo > totalPage) { // 스크립트에선 ajax로 되어 있기 때문에 url에서 장난을 칠수 없다. 지금 현재 자바에선 장난 칠수 있기 때문에 방지해야 한다.
					currentShowPageNo = 1;
				}
			}catch(NumberFormatException e) { // url에 문자로 장난 칠수 있기 때문에 방지한다!
				currentShowPageNo = 1;
			}
		}
		// 가져올 게시글의 범위를 구한다.
		startRno = ((currentShowPageNo-1)*sizePerPage) + 1;
		endRno = startRno + sizePerPage - 1;
		
		paraMap.put("STARTRNO", String.valueOf(startRno));
		paraMap.put("ENDRNO", String.valueOf(endRno));
		
		noticeList = service.noticeListPaging(paraMap);
		
		// 페이지바 만들기
		String pagebar = "<ul>";
		pagebar += MyUtil.getPageBarWithSearch(sizePerPage, blockSize, totalPage, currentShowPageNo, colname, search, null, "noticeList.ana");
		pagebar += "<ul>";
		
		// 글조회수
		HttpSession session = request.getSession();
		session.setAttribute("readCountPermission", "yes");
		
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("colname", colname);
		request.setAttribute("search", search);
		request.setAttribute("pagebar", pagebar);
		
		String gobackURL = MyUtil.getCurrentURL(request);
		request.setAttribute("gobackURL", gobackURL);
	*/
		
		return "notice/noticeList.tiles1";
	}
	
	// 총괄관리자 => 공지사항 글 목록(검색 없는 것 or 검색 있는 것)
	@RequestMapping(value="/adminNoticeList.ana",method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> noticeAdminList(HttpServletRequest request, HttpServletResponse response) {
		
		List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String,Object>>();
		
		String currentShowPageNo = request.getParameter("currentShowPageNo");
		String colname = request.getParameter("COLNAME");
		String search = request.getParameter("SEARCH");
		
		if(currentShowPageNo == null || "".equals(currentShowPageNo)) {
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 10;
		
		int rno1 = Integer.parseInt(currentShowPageNo) * sizePerPage - (sizePerPage-1);
		int rno2 = Integer.parseInt(currentShowPageNo) * sizePerPage;
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("COLNAME", colname);
		paraMap.put("SEARCH", search);
		paraMap.put("RNO1", String.valueOf(rno1));
		paraMap.put("RNO2", String.valueOf(rno2));
		
		List<PMGNoticeVO> noticeList = service.noticeAdminListWithSearch(paraMap);
		
		for(PMGNoticeVO nvo : noticeList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			try {
				map.put("idx", nvo.getIdx());
				map.put("libid_fk", nvo.getLibid_fk());
				map.put("adminid_fk", nvo.getAdminid_fk());
				map.put("name", nvo.getName());
				map.put("subject", nvo.getSubject());
				map.put("readCount", nvo.getReadCount());
				map.put("regDate", nvo.getRegDate());
				map.put("fileName", nvo.getFileName());
				map.put("fileSize", nvo.getFileSize());
				map.put("libname", nvo.getLibname());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			mapList.add(map);
		}
		
		return mapList;
	}
	// notice테이블 총 페이지수 알아오기(검색 없는 것 or 검색 있는 것)
	@RequestMapping(value="/getNotiecTotalPage.ana",method= {RequestMethod.GET})
	@ResponseBody
	public HashMap<String, Integer> getNoticeTotalPage(HttpServletRequest request, HttpServletResponse response) {
		
		HashMap<String, Integer> noticeMap = new HashMap<String, Integer>();
		
		String sizePerPage = request.getParameter("sizePerPage");
		String colname = request.getParameter("COLNAME");
		String search = request.getParameter("SEARCH");
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("SIZEPERPAGE", sizePerPage);
		paraMap.put("COLNAME", colname);
		paraMap.put("SEARCH", search);
		
		int totalCount = service.getNoticeTotalCount(paraMap);
		
		int totalPage= (int)Math.ceil((double)totalCount/Integer.parseInt(sizePerPage));
		noticeMap.put("TOTALPAGE", totalPage);
		
		return noticeMap;
	}
	
	
	
	
	// ***************************************************** //
	// 도서관장 => 공지사항 글 목록(검색 없는 것 or 검색 있는 것)
	@RequestMapping(value="/lib1NoticeList.ana",method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> noticelib1List(HttpServletRequest request, HttpServletResponse response) {
		
		List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String,Object>>();
		
		String libcode = ((LibrarianVO)request.getSession().getAttribute("loginLibrarian")).getLibcode_fk();
		
		String currentShowPageNo = request.getParameter("currentShowPageNo");
		String colname = request.getParameter("COLNAME");
		String search = request.getParameter("SEARCH");	
		
		if(currentShowPageNo == null || "".equals(currentShowPageNo)) {
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 10;
		
		int rno1 = Integer.parseInt(currentShowPageNo) * sizePerPage - (sizePerPage-1);
		int rno2 = Integer.parseInt(currentShowPageNo) * sizePerPage;
		
		HashMap<String, String> paraMap = new HashMap<String, String>();		
		paraMap.put("COLNAME", colname);
		paraMap.put("SEARCH", search);
		paraMap.put("LIBCODE", libcode);
		paraMap.put("RNO1", String.valueOf(rno1));
		paraMap.put("RNO2", String.valueOf(rno2));		
		
		List<PMGNoticeVO> noticeList = service.noticelib1ListWithSearch(paraMap);
		
		for(PMGNoticeVO nvo : noticeList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			try {
				map.put("idx", nvo.getIdx());
				map.put("libid_fk", nvo.getLibid_fk());
				map.put("adminid_fk", nvo.getAdminid_fk());
				map.put("name", nvo.getName());
				map.put("subject", nvo.getSubject());
				map.put("readCount", nvo.getReadCount());
				map.put("regDate", nvo.getRegDate());
				map.put("fileName", nvo.getFileName());
				map.put("fileSize", nvo.getFileSize());				
				map.put("libname", nvo.getLibname());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			mapList.add(map);
		}
		
		return mapList;
	}
	// 도서관장, notice테이블 총 페이지수 알아오기(검색 없는 것 or 검색 있는 것)
	@RequestMapping(value="/lib1getNotiecTotalPage.ana",method= {RequestMethod.GET})
	@ResponseBody
	public HashMap<String, Integer> lib1getNoticeTotalPage(HttpServletRequest request, HttpServletResponse response) {
		
		String libcode = ((LibrarianVO)request.getSession().getAttribute("loginLibrarian")).getLibcode_fk();
		
		HashMap<String, Integer> noticeMap = new HashMap<String, Integer>();
		
		String sizePerPage = request.getParameter("sizePerPage");
		String colname = request.getParameter("COLNAME");
		String search = request.getParameter("SEARCH");	
		
		HashMap<String, String> paraMap = new HashMap<String, String>();		
		paraMap.put("SIZEPERPAGE", sizePerPage);
		paraMap.put("COLNAME", colname);
		paraMap.put("SEARCH", search);
		paraMap.put("LIBCODE", libcode);
		
		int totalCount = service.lib1getNoticeTotalCount(paraMap);
		
		int totalPage= (int)Math.ceil((double)totalCount/Integer.parseInt(sizePerPage));
		noticeMap.put("TOTALPAGE", totalPage);
		
		return noticeMap;
	}
	
	
	// ***************************************************** //
	// 사서 => 공지사항 글 목록(검색 없는 것 or 검색 있는 것)
	@RequestMapping(value="/lib0NoticeList.ana",method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> noticelib0List(HttpServletRequest request, HttpServletResponse response) {
		
		List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String,Object>>();
		
		String libcode = ((LibrarianVO)request.getSession().getAttribute("loginLibrarian")).getLibcode_fk();
		
		String currentShowPageNo = request.getParameter("currentShowPageNo");
		String colname = request.getParameter("COLNAME");
		String search = request.getParameter("SEARCH");	
		
		if(currentShowPageNo == null || "".equals(currentShowPageNo)) {
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 10;
		
		int rno1 = Integer.parseInt(currentShowPageNo) * sizePerPage - (sizePerPage-1);
		int rno2 = Integer.parseInt(currentShowPageNo) * sizePerPage;
		
		HashMap<String, String> paraMap = new HashMap<String, String>();		
		paraMap.put("COLNAME", colname);
		paraMap.put("SEARCH", search);
		paraMap.put("LIBCODE", libcode);
		paraMap.put("RNO1", String.valueOf(rno1));
		paraMap.put("RNO2", String.valueOf(rno2));		
		
		List<PMGNoticeVO> noticeList = service.noticelib0ListWithSearch(paraMap);
		
		for(PMGNoticeVO nvo : noticeList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			try {
				map.put("idx", nvo.getIdx());
				map.put("libid_fk", nvo.getLibid_fk());
				map.put("adminid_fk", nvo.getAdminid_fk());
				map.put("name", nvo.getName());
				map.put("subject", nvo.getSubject());
				map.put("readCount", nvo.getReadCount());
				map.put("regDate", nvo.getRegDate());
				map.put("fileName", nvo.getFileName());
				map.put("fileSize", nvo.getFileSize());				
				map.put("libname", nvo.getLibname());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			mapList.add(map);
		}
		
		return mapList;
	}
	// 사서, notice테이블 총 페이지수 알아오기(검색 없는 것 or 검색 있는 것)
	@RequestMapping(value="/lib0getNotiecTotalPage.ana",method= {RequestMethod.GET})
	@ResponseBody
	public HashMap<String, Integer> lib0getNoticeTotalPage(HttpServletRequest request, HttpServletResponse response) {
		
		String libcode = ((LibrarianVO)request.getSession().getAttribute("loginLibrarian")).getLibcode_fk();
		
		HashMap<String, Integer> noticeMap = new HashMap<String, Integer>();
		
		String sizePerPage = request.getParameter("sizePerPage");
		String colname = request.getParameter("COLNAME");
		String search = request.getParameter("SEARCH");	
		
		HashMap<String, String> paraMap = new HashMap<String, String>();		
		paraMap.put("SIZEPERPAGE", sizePerPage);
		paraMap.put("COLNAME", colname);
		paraMap.put("SEARCH", search);
		paraMap.put("LIBCODE", libcode);
		
		int totalCount = service.lib0getNoticeTotalCount(paraMap);
		
		int totalPage= (int)Math.ceil((double)totalCount/Integer.parseInt(sizePerPage));
		noticeMap.put("TOTALPAGE", totalPage);
		
		return noticeMap;
	}
	
	
	
	// 공지사항 글쓰기
	@RequestMapping(value="/noticeWrite.ana", method= {RequestMethod.GET})
	public String noticeWrite(HttpServletRequest request, HttpServletResponse response) {
		
		return "notice/noticeWrite.tiles1";
	}
	
	// 공지사항 글쓰기 완료
	@RequestMapping(value="/noticeWriteEnd.ana", method= {RequestMethod.POST})
	public String noticeWriteEnd(PMGNoticeVO noticevo, MultipartHttpServletRequest request, HttpServletResponse response) {
		
		// 파일첨부가 된 공지사항 글쓰기
		MultipartFile attach = noticevo.getAttach();
		
		// 첨부파일이 있는지 없는지 알아오기 시작
		if(!attach.isEmpty()) {
			// attach 가 비어있지 않다면(즉, 첨부파일이 있는 경우라면)
			
			// 사용자가 보낸 파일을 WAS(톰캣)의 특정 폴더에 저장 => WAS의 webapp/resources/files 라는 폴더로 지정
			// WAS의 webapp 의 절대경로를 알아와야 한다.
			HttpSession session = request.getSession();
			String root = session.getServletContext().getRealPath("/"); // Context가 프로그램 전체를 말한다. // "/" => webapp
			String path = root + "resources" + File.separator + "files"; // File.separator 운영체제에 따라서 windows는 \ , 나머지(리눅스, 유닉스) /
			// path 가 첨부파일들을 저장할 WAS(톰캣)의 폴더가 된다.
			
			System.out.println("확인용 path ==> "+path);
			
			String newFileName = "";
			// WAS(톰캣) 디스크에 저장할 파일명
			
			byte[] bytes = null;
			// 첨부파일을 WAS(톰캣) 디스크에 저장할때 사용되는 용도
			
			long fileSize = 0;
			// 파일크기를 읽어오기 위한 용도
			
			try {
				bytes = attach.getBytes();
				// getBytes() 는 첨부된 파일을 바이트 단위로 파일을 다 읽어오는 것이다.
				
				newFileName = fileManager.doFileUpload(bytes, attach.getOriginalFilename(), path);
				// 첨부된 파일을 WAS(톰캣)의 디스크로 파일올기리를 하는 것이다.
				// 파일을 올린후 예를 들어, 20190107091235345464324354353467.png 와 같은 파일명을 얻어온다.
				
				System.out.println(">>>> 확인용 newFileName ==> " + newFileName);
				// >>>> 확인용 newFileName ==> 201901071126192667107421104377.png
				
			//	PMGNoticeVO noticevo 에 fileName 값과 orgFileName 값과 fileSize 값을 넣어주기
				noticevo.setFileName(newFileName);
				noticevo.setOrgFileName(attach.getOriginalFilename());
				
				fileSize = attach.getSize();
				// 첨부한 파일의 크기인데 리턴타입은 long 타입이다.
				
				noticevo.setFileSize(String.valueOf(fileSize));
				
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}// 첨부파일이 있는지 없는 알아오기 끝
		
		int n = 0;
		if(attach.isEmpty()) {
			// 파일첨부가 없다라면
			n = service.noticeWriteadd(noticevo);
		}
		else {
			// 파일첨부가 있다라면
			n = service.noticeWriteadd_withFile(noticevo);
		}		
		
		String loc = "";
		
		if(n==1) {
			loc = request.getContextPath()+"/noticeList.ana";
		}
		else {
			loc = request.getContextPath()+"/index.ana";
		}
		
		request.setAttribute("n", n);
		request.setAttribute("loc", loc);
		
		return "notice/noticeWriteEnd.tiles1";
	}
	
	
	
	
}
