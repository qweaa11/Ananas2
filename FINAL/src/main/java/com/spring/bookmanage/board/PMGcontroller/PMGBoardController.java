package com.spring.bookmanage.board.PMGcontroller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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

import com.spring.bookmanage.JDSmodel.AdminVO;
import com.spring.bookmanage.JDSmodel.LibrarianVO;
import com.spring.bookmanage.board.PMGmodel.CommentVO;
import com.spring.bookmanage.board.PMGmodel.PMGNoticeVO;
import com.spring.bookmanage.board.PMGmodel.PhotoVO;
import com.spring.bookmanage.board.PMGservice.PMGBoardService;
import com.spring.bookmanage.common.AES256;
import com.spring.bookmanage.common.FileManager;
import com.spring.bookmanage.common.LargeThumbnailManager;
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
	
	//===== 스마트에디터3 ===== 스마트에디터 사용시 사진첨부를 할 경우 원본 사진의 크기가 아주 클 경우 이미지 width 의 크기를 적절하게 줄여주는 의존객체 주입하기
	@Autowired
	private LargeThumbnailManager largeThumbnailManager;
	
	// *** 공지사항 목록 ***
	@RequestMapping(value="/noticeList.ana", method= {RequestMethod.GET})
	public String noticeList(HttpServletRequest request, HttpServletResponse response) {
		
		request.getSession().getAttribute("loginLibrarian"); // status => 등급(일반사서 0,도서관장 1)
		request.getSession().getAttribute("loginAdmin");	// 총괄관리자
		String pageNo = request.getParameter("pageNo");
		String colname = request.getParameter("colname");
		String search = request.getParameter("search");
		
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("colname"); // 공지사항 목록 리스트로 오면 세션 삭제
		session.removeAttribute("search"); // 공지사항 목록 리스트로 오면 세션 삭제
		
		session.setAttribute("readCountPermission", "yes");
		
		String gobackURL = MyUtil.getCurrentURL(request);
		gobackURL = gobackURL.substring(0, gobackURL.indexOf("?"));
	//	System.out.println(gobackURL);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("colname", colname);
		request.setAttribute("search", search);
		request.setAttribute("gobackURL", gobackURL);
	
		return "notice/noticeList.tiles1";
	}
	
	// 총괄관리자 => 공지사항 글 목록(검색 없는 것 or 검색 있는 것)
	@RequestMapping(value="/adminNoticeList.ana", method= {RequestMethod.GET})
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
				map.put("commentCount", nvo.getCommentCount());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			mapList.add(map);
		}
		
		return mapList;
	}
	// notice테이블 총 페이지수 알아오기(검색 없는 것 or 검색 있는 것)
	@RequestMapping(value="/getNotiecTotalPage.ana", method= {RequestMethod.GET})
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
	// 도서관장,사서 => 공지사항 글 목록(검색 없는 것 or 검색 있는 것)
	@RequestMapping(value="/lib1NoticeList.ana", method= {RequestMethod.GET})
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
				map.put("commentCount", nvo.getCommentCount());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			mapList.add(map);
		}
		
		return mapList;
	}
	// 도서관장,사서 notice테이블 총 페이지수 알아오기(검색 없는 것 or 검색 있는 것)
	@RequestMapping(value="/lib1getNotiecTotalPage.ana", method= {RequestMethod.GET})
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
	
	
	// 공지사항 글쓰기
	@RequestMapping(value="/noticeWrite.ana", method= {RequestMethod.GET})
	public String noticeWrite(HttpServletRequest request, HttpServletResponse response) {
		
		return "notice/noticeWrite.tiles1";
	}
	
	// 공지사항 글쓰기 완료
	@RequestMapping(value="/noticeWriteEnd.ana", method= {RequestMethod.POST})
	public String noticeWriteEnd(MultipartHttpServletRequest request, HttpServletResponse response, PMGNoticeVO noticevo) {
		
		// 파일첨부가 된 공지사항 글쓰기
		MultipartFile attach = noticevo.getAttach();
		
		HttpSession session = request.getSession();
		// 첨부파일이 있는지 없는지 알아오기 시작
		if(!attach.isEmpty()) {
			// attach 가 비어있지 않다면(즉, 첨부파일이 있는 경우라면)
			
			// 사용자가 보낸 파일을 WAS(톰캣)의 특정 폴더에 저장 => WAS의 webapp/resources/files 라는 폴더로 지정
			// WAS의 webapp 의 절대경로를 알아와야 한다.
			
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
		
		try {
			if(attach.isEmpty()) {
				// 파일첨부가 없다라면
				n = service.noticeWriteadd(noticevo);
			}
			else {
				// 파일첨부가 있다라면
				n = service.noticeWriteadd_withFile(noticevo);
			}	
		} catch (Throwable e) {
			n = 0;
		}
			
		
		String loc = "";
		
		if(n > 0) {
			loc = request.getContextPath()+"/noticeList.ana";
			
			LibrarianVO librarian = (LibrarianVO)session.getAttribute("loginLibrarian");
			
			HashMap<String, String> paraMap = new HashMap<>();
			paraMap.put("PIDX", String.valueOf(n)); // notice테이블의 제일 최근 IDX 글번호
			
			
			try {
				if(librarian != null) {	// 도서관장이 등록한 공지사항 일때
					paraMap.put("LIBCODE", librarian.getLibcode_fk());
					paraMap.put("GRADE", String.valueOf(librarian.getStatus()));
				}
				service.insertAlarm(paraMap);
				n=1;
				
			} catch (Throwable e) {
				loc = request.getContextPath()+"/index.ana";
				n = 0;
				e.printStackTrace();
			}
			
		}
		else {
			loc = request.getContextPath()+"/index.ana";
		}
		
		request.setAttribute("n", n);
		request.setAttribute("loc", loc);
		
		return "notice/noticeWriteEnd.tiles1";
	}
	
	// 공지사항 1개글 상세보기
	@RequestMapping(value="/noticeView.ana", method= {RequestMethod.GET})
	public String noticeView(HttpServletRequest request, HttpServletResponse response) {
		
		String idx = request.getParameter("idx");
		
		String gobackURL = request.getParameter("gobackURL");
		
		String colname = request.getParameter("colname");
		String search = request.getParameter("search");
		
		PMGNoticeVO noticevo = null;	// 글1개를 저장할 객체변수
		
		HttpSession session = request.getSession();
		if(gobackURL != null) {	// 목록보기 => 처음 리스트 목록 볼때 세션에 저장
			session.setAttribute("gobackNoticeURL", gobackURL);
		}else { // 공지사항 수정, 삭제 후 목록보기 세션에 저장
			gobackURL = (String)session.getAttribute("gobackNoticeURL");
			
		}
		request.setAttribute("gobackURL", gobackURL);
		
		if(session.getAttribute("colname") == null && session.getAttribute("search") == null) { // 검색 후 컬럼네임과 검색어를 세션에 저장
			
			session.setAttribute("colname", colname);
			session.setAttribute("search", search);
			
		}
		
		// 로그인 되어진 사용자 정보를 가져옴(총관리자, 도서관장, 사서)
		AdminVO adminvo = (AdminVO)request.getSession().getAttribute("loginAdmin");
		LibrarianVO libvo = (LibrarianVO)request.getSession().getAttribute("loginLibrarian");
		
		// 글조회수(readCount) 증가, 반드시 해당 글제목을 클릭했을 경우에만 글조회수 증가
		// 이전글, 다음글보기를 했을 경우나 새로고침은 증가 X
		String readCountPermission = (String)session.getAttribute("readCountPermission");
		
		String userid = null;
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		
		if(adminvo != null) {
			userid = adminvo.getAdminid();
			paraMap.put("ADMINID", adminvo.getAdminid());
		}
		
		if(libvo != null) {
			userid = libvo.getLibid(); // libvo.getLibid();
			paraMap.put("LIBCODE", libvo.getLibcode_fk());
			paraMap.put("ADMINID", null);
		//	paraMap.put("LIBID", libvo.getLibid());
		}
		
		paraMap.put("COLNAME", (String)session.getAttribute("colname"));
		paraMap.put("SEARCH", (String)session.getAttribute("search"));
		paraMap.put("IDX", idx);
		paraMap.put("USERID", userid);
		
	//	System.out.println("컬럼네임 : "+paraMap.get("COLNAME"));
	//	System.out.println("검색어 : "+paraMap.get("SEARCH"));
		
		if(readCountPermission != null &&
		   "yes".equals(readCountPermission)) { 
			// 글1개를 보기위해 공지사항 목록 noticeList.ana를 거친 경우
			
			noticevo = service.getView(paraMap);
			
			// 글1개를 보기위해 목록을 거쳐온 경우이라면 확인후 세션에서 제거
			session.removeAttribute("readCountPermission");
		}
		else {
			// 특정글 1개를 본 이후 새로고침이나 이전글보기 또는 다음글 보기를 한 경우 // 글 목록을 거쳐야 함
			// 이런 경우 글조회수 증가는 없다.
			noticevo = service.getViewWithNoAddCount(paraMap);
		}
		
		request.setAttribute("noticevo", noticevo);
		
		return "notice/noticeView.tiles1";
	}
	
	// 공지사항 목록 setTimeout(새로고침)
	@RequestMapping(value="/readCountPermission.ana", method= {RequestMethod.GET})
	@ResponseBody
	public HashMap<String, String> readCountPermission(HttpServletRequest request, HttpServletResponse response) {
		
		String bool = (String)request.getSession().getAttribute("readCountPermission");
		
		HashMap<String, String> json = new HashMap<String, String>();
		
		json.put("BOOL", bool);
		
		return json;
		
	}// end of readCountPermission()
	
	
	// 공지사항 수정 페이지
	@RequestMapping(value="/noticeEdit.ana", method= {RequestMethod.GET})
	public String noticeEdit(HttpServletRequest request, HttpServletResponse response) {
		
		String idx = request.getParameter("idx");
		
		AdminVO adminvo = (AdminVO)request.getSession().getAttribute("loginAdmin");
		LibrarianVO libvo = (LibrarianVO)request.getSession().getAttribute("loginLibrarian");
		
		String userid = null;
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		
		if(adminvo != null) {
			userid = adminvo.getAdminid();
			paraMap.put("ADMINID", adminvo.getAdminid());
		}
		if(libvo != null) {
			userid = libvo.getLibid(); // libvo.getLibid();
			paraMap.put("LIBCODE", libvo.getLibcode_fk());
			paraMap.put("ADMINID", null);	// 강제로 null을 넣었다.
		//	paraMap.put("LIBID", libvo.getLibid());
		}
		
		paraMap.put("IDX", idx);
		paraMap.put("USERID", userid);
	//	System.out.println(paraMap.get("LIBID"));
		// 공지사항 수정해야 할 글전체 내용 가져오기
		PMGNoticeVO noticevo = service.getViewWithNoAddCount(paraMap);
		
	//	System.out.println(libvo.getLibid());
	//	System.out.println(noticevo.getLibid_fk());
		
		if(adminvo != null) {
			if(!adminvo.getAdminid().equals(noticevo.getAdminid_fk())) {
				String msg = "다른 사용자의 공지사항 수정이 불가합니다.";
				String loc = "javascript:history.back();";
				
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
				return "msg";
			}
		}
		if(libvo != null) {
			if(!libvo.getLibid().equals(noticevo.getLibid_fk())) {
				String msg = "다른 사용자의 공지사항 수정이 불가합니다.";
				String loc = "javascript:history.back();";
				
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
				return "msg";
			}
		}
		
		request.setAttribute("noticevo", noticevo);
		
		return "notice/noticeEdit.tiles1";
	}
	// 공지사항 수정 페이지 완료
	@RequestMapping(value="/noticeEditEnd.ana", method= {RequestMethod.POST})
	public String noticeEditEnd(HttpServletRequest request, HttpServletResponse response, PMGNoticeVO noticevo) {
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("IDX", noticevo.getIdx());
		paraMap.put("PW", noticevo.getPw());
		paraMap.put("SUBJECT", noticevo.getSubject());
		paraMap.put("CONTENT", noticevo.getContent());
		
		int result = service.noticeEdit(paraMap);
		// 글 암호가 일치하면 1(update 성공), 틀리면 0(update 실패)
		
		String msg = "";
		String loc = "";
		
		if(result == 0) {
			msg = "공지사항 수정 실패";
			loc = "javascript:history.back();";
		}
		else {
			msg = "공지사항 수정 성공";
			loc = "noticeView.ana?idx="+noticevo.getIdx();
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		return "msg";
	}
	
	
	// 공지사항 삭제 페이지 요청
	@RequestMapping(value="/noticeDelete.ana", method= {RequestMethod.GET})
	public String noticeDelete(HttpServletRequest request, HttpServletResponse response) {
		
		// 삭제해야할 글번호
		String idx = request.getParameter("idx");
		
		// 세션에 저장된 총관리자, 도서관장이 쓴 공지사항 글전체 내용 가져오기
		AdminVO adminvo = (AdminVO)request.getSession().getAttribute("loginAdmin");
		LibrarianVO libvo = (LibrarianVO)request.getSession().getAttribute("loginLibrarian");
		
		String userid = null;
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		
		if(adminvo != null) {
			userid = adminvo.getAdminid();
			paraMap.put("ADMINID", adminvo.getAdminid());
		}
		if(libvo != null) {
			userid = libvo.getLibid(); // libvo.getLibid();
			paraMap.put("LIBCODE", libvo.getLibcode_fk());
			paraMap.put("ADMINID", null);	// 강제로 null을 넣었다.
		//	paraMap.put("LIBID", libvo.getLibid());
		}
		
		paraMap.put("IDX", idx);
		paraMap.put("USERID", userid);
	//	System.out.println(paraMap.get("LIBID"));
		// 공지사항 수정해야 할 글전체 내용 가져오기
		PMGNoticeVO noticevo = service.getViewWithNoAddCount(paraMap);
		
	//	System.out.println(libvo.getLibid());
	//	System.out.println(noticevo.getLibid_fk());
		
		if(adminvo != null) {
			if(!adminvo.getAdminid().equals(noticevo.getAdminid_fk())) {
				String msg = "다른 사용자의 공지사항 삭제가 불가합니다.";
				String loc = "javascript:history.back();";
				
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
				return "msg";
			}
		}
		if(libvo != null) {
			if(!libvo.getLibid().equals(noticevo.getLibid_fk())) {
				String msg = "다른 사용자의 공지사항 삭제가 불가합니다.";
				String loc = "javascript:history.back();";
				
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
				return "msg";
			}
		}
		
		request.setAttribute("noticevo", noticevo);
		
		return "notice/noticeDelete.tiles1";
	}
	// 공지사항 삭제 완료
	@RequestMapping(value="/noticeDeleteEnd.ana", method= {RequestMethod.POST})
	public String noticeDeleteEnd(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		// 공지사항 삭제를 하려면 글번호와 사용자가 입력한 글암호를 알아와서 삭제할 글의 암호와 사용자가 입력한 글암호가 일치할 경우에만 삭제
		String idx = request.getParameter("idx");
		String pw = request.getParameter("pw");
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("IDX", idx);
		paraMap.put("PW", pw);
		
		int result = service.noticeDelete(paraMap);
		// 1이면 글삭제 성공, 0이면 글삭제 실패(암호가 틀림)
		
		String msg = "";
		String loc = "";
		
		if(result == 0) {
			msg = "공지사항 삭제 실패!!(암호가 일치하지 않습니다.)";
			loc = "javascript:history.back();";
		}
		else {
			msg = "공지사항 삭제 성공!!";
			loc = "noticeList.ana";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		return "msg";
		
	}
	
	// 댓글쓰기
	@RequestMapping(value="/commentWrite.ana", method= {RequestMethod.POST})
	@ResponseBody
	public HashMap<String, String> commentWrite(HttpServletRequest request, HttpServletResponse response, CommentVO commentvo) throws Throwable {
		
		HashMap<String, String> returnMap = new HashMap<String, String>();
		
		LibrarianVO libvo = (LibrarianVO)request.getSession().getAttribute("loginLibrarian");
		
		String libName = libvo.getLibcode_fk();
		String libClass = "";
		int status = libvo.getStatus();
		if(status == 0) {
			libClass = "사서";
		}
		else if(status == 1) {
			libClass = "도서관장";
		}
		String getLibName = service.findLibName(libName);
		
	//	int commentIdx = service.findCommentIdx();
		
		// 댓글쓰기
		int n = service.commentWrite(commentvo); // 트랜잭션 처리
	//	System.out.println(MyUtil.getNowTime());
	//	System.out.println(commentvo.getIdx());
		if(n==1) {
		//	returnMap.put("IDX", commentvo.getIdx());
			returnMap.put("LIBID_FK", commentvo.getLibid_fk());
			returnMap.put("NAME", commentvo.getName());
			returnMap.put("CONTENT", commentvo.getContent());
			returnMap.put("REGDATE", MyUtil.getNowTime());
			returnMap.put("LIBCLASS", libClass);
			returnMap.put("LIBNAME", getLibName);
		}
		
		return returnMap;
	}
	// 댓글 내용 가져오기
	@RequestMapping(value="/commentListlib.ana", method= {RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> commentListlib(HttpServletRequest request, HttpServletResponse response) {
		
		List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
		
		String idx = request.getParameter("idx");
		// 원글 공지사항 글번호를 받아와서 원글에 딸린 댓글을 보여준다.
		
		String currentShowPageNo = request.getParameter("currentShowPageNo");
		
		if(currentShowPageNo == null || "".equals(currentShowPageNo)) {
			  currentShowPageNo = "1";
		}
		
		int sizePerPage = 5;
		
		int rno1 = Integer.parseInt(currentShowPageNo)*sizePerPage - (sizePerPage-1);
		int rno2 = Integer.parseInt(currentShowPageNo)*sizePerPage;
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
	    paraMap.put("IDX", idx);
	    paraMap.put("RNO1", String.valueOf(rno1));
	    paraMap.put("RNO2", String.valueOf(rno2));
		
	    List<CommentVO> libCommentList = service.commentListLib(paraMap);
	    
	    for(CommentVO cmtvo : libCommentList) {
	    	HashMap<String, Object> map = new HashMap<String, Object>();
	    	map.put("IDX", cmtvo.getIdx());
	    	map.put("LIBID_FK", cmtvo.getLibid_fk());
	    	map.put("NAME", cmtvo.getName());
	    	map.put("CONTENT", cmtvo.getContent());
	    	map.put("REGDATE", cmtvo.getRegDate());
	    	map.put("LIBCLASS", cmtvo.getLibClass());
	    	map.put("LIBNAME", cmtvo.getLibName());
	    	map.put("STATUS", cmtvo.getStatus());
			  
			mapList.add(map);
	    }
	    
		return mapList;
	}
	
	// 댓글 TOTALPAGE
	@RequestMapping(value="/getLibCommentTotalPage.ana", method= {RequestMethod.GET})
	@ResponseBody
	public HashMap<String, Integer> getLibCommentTotalPage(HttpServletRequest request, HttpServletResponse response) {
		  
		  HashMap<String, Integer> returnMap = new HashMap<String, Integer>(); 
		  
		  String idx = request.getParameter("idx");
		  // 원글의 글번호를 받아와서 원글에 딸린 댓글의 갯수를 알아오려고 한다.
		  
		  String sizePerPage = request.getParameter("sizePerPage");
		  // 한 페이지당 보여줄 댓글의 갯수 
		  
		  HashMap<String, String> paraMap = new HashMap<String, String>();
		  paraMap.put("IDX", idx);
		  paraMap.put("SIZEPERPAGE", sizePerPage);
		  
		  // -- 원글 글번호에 해당하는 댓글의 총갯수를 알아오기
		  int totalCount = service.getCommentLibTotalCount(paraMap); 
		   
		  // -- 총 페이지수(totalPage) 구하기 
		  int totalPage = (int)Math.ceil((double)totalCount/Integer.parseInt(sizePerPage)); 
		  
		  returnMap.put("TOTALPAGE", totalPage);
		  
		  return returnMap;
	}
	
	// 댓글 삭제
	@RequestMapping(value="/commentDelete.ana", method= {RequestMethod.POST})
	public String commentDelete(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String idx = request.getParameter("idx");
		String libid_fk = request.getParameter("libid_fk");
		String viewIdx = request.getParameter("viewIdx");
	//	System.out.println(viewIdx);
	//	System.out.println(idx);
	//	System.out.println(libid_fk);
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("IDX", idx);
		paraMap.put("LIBID_FK", libid_fk);
		paraMap.put("VIEWIDX", viewIdx);
		
		int n = service.commentDelete(paraMap);
		
		String msg = "";
		String loc = "";
		
		if(n == 1) {
			msg = "댓글 삭제 성공~!!";
			loc = "noticeView.ana?idx="+viewIdx;
		}
		else {
			msg = "댓글 삭제 실패~!!";
			loc = "javascript:history.back();";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		return "msg";
	}
	
	
	// 첨부파일 다운로드
	@RequestMapping(value="/fileDownload.ana", method={RequestMethod.GET})
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) {
		
		String idx = request.getParameter("idx");
		// 첨부파일이 있는 글번호
		
		HttpSession session = request.getSession();
		// 로그인 되어진 사용자 정보를 가져옴(총관리자, 도서관장, 사서)
		AdminVO adminvo = (AdminVO)request.getSession().getAttribute("loginAdmin");
		LibrarianVO libvo = (LibrarianVO)request.getSession().getAttribute("loginLibrarian");
		
		// 글조회수(readCount) 증가, 반드시 해당 글제목을 클릭했을 경우에만 글조회수 증가
		// 이전글, 다음글보기를 했을 경우나 새로고침은 증가 X
		String userid = null;
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		
		if(adminvo != null) {
			userid = adminvo.getAdminid();
			paraMap.put("ADMINID", adminvo.getAdminid());
		}
		
		if(libvo != null) {
			userid = libvo.getLibid(); // libvo.getLibid();
			paraMap.put("LIBCODE", libvo.getLibcode_fk());
			paraMap.put("ADMINID", null);
		//	paraMap.put("LIBID", libvo.getLibid());
		}
		paraMap.put("IDX", idx);
		paraMap.put("USERID", userid);
		
		PMGNoticeVO noticevo = service.getViewWithNoAddCount(paraMap);
		// 조회수 증가 없이 1개글 가져오기
		
		String fileName = noticevo.getFileName();
		// 201901071211332669823687982667.jpg 와 같은 것을 가져온다.
		// 이것이 바로 WAS(톰캣) 디스크에 저장된 파일명이다.
		
		String orgFileName = noticevo.getOrgFileName();
		// 니트터치장갑-메인.jpg 와 같은것을 가져온다.
		// 사용자가 파일을 다운받을시 니트터치장갑-메인.jpg 처럼 다운받도록 하는 것이다. 
		
		// 첨부파일이 저장되어 있는 WAS의 절대경로를 알아와야 한다.
		// 그래야만 첨부파일이 저장되어있는 곳에서 파일을 다운받을 수 있다.
		// 이 경로는 파일을 첨부했을때와 동일한 경로이다.
		String root = session.getServletContext().getRealPath("/"); // Context가 프로그램 전체를 말한다. // "/" => webapp
		String path = root + "resources" + File.separator + "files"; // File.separator 운영체제에 따라서 windows는 \ , 나머지(리눅스, 유닉스) /
		// path 가 첨부파일들이 저장된 WAS(톰캣)의 폴더가 된다.
		
		// *** 다운로드 하기 *** //
		// 다운로드가 실패할 경우 메시지를 띄워주기 위해서
		// boolean 타입 변수 flag 를 선언한다.
		boolean flag = false;
		
		flag = fileManager.doFileDownload(fileName, orgFileName, path, response);
		// 다운로드가 성공이면 true를 반환해주고,
		// 다운로드가 실패이면 false 를 반환해준다.
		
		if(!flag) {
			// 다운로드가 실패할 경우 메시지를 띄워준다.
			
			response.setContentType("text/html; charset=UTF-8");
			
			try {
				PrintWriter out = response.getWriter(); // 웹브라우저에 뭔가 써주는 필기도구 PrintWriter객체
				// PrintWriter out 이 웹브라우저 상에 내용물을 기재(써주는)해주는 객체이다.
				
				out.println("<script type='text/javascript'>alert('파일 다운로드가 실패했습니다.');</script>");
				out.println("<script type='text/javascript'>location.href='javascript:history.back();'</script>");
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
		}
		
	}
	
	
	// ********************************************** 스마트에디터 ************************************************************** //
	// =====#스마트에디터1. 단일사진 파일업로드=====(없어도 상관없는것 강사님이 그렇게 말씀하심)
	@RequestMapping(value="/bookmanage/image/photoUpload.ana", method={RequestMethod.POST})
	public String noticePhotoUpload(HttpServletRequest request, HttpServletResponse response, PhotoVO photovo) {
		
		String callback = photovo.getCallback();
	    String callback_func = photovo.getCallback_func();
	    String file_result = "";
	    
		if(!photovo.getFiledata().isEmpty()) {
			// 파일이 존재한다라면
			
			/*
			   1. 사용자가 보낸 파일을 WAS(톰캣)의 특정 폴더에 저장해주어야 한다.
			   >>>> 파일이 업로드 되어질 특정 경로(폴더)지정해주기
			        우리는 WAS 의 webapp/resources/photo_upload 라는 폴더로 지정해준다.
			 */
			
			// WAS 의 webapp 의 절대경로를 알아와야 한다. 
			HttpSession session = request.getSession();
			String root = session.getServletContext().getRealPath("/"); 
			String path = root + "resources"+File.separator+"photo_upload";
			// path 가 첨부파일들을 저장할 WAS(톰캣)의 폴더가 된다. 
			
		//	System.out.println(">>>> 확인용 path ==> " + path); 
			// >>>> 확인용 path ==> C:\springworkspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Board\resources\photo_upload
			
			// 2. 파일첨부를 위한 변수의 설정 및 값을 초기화한 후 파일올리기
			String newFilename = "";
			// WAS(톰캣) 디스크에 저장할 파일명 
			
			byte[] bytes = null;
			// 첨부파일을 WAS(톰캣) 디스크에 저장할때 사용되는 용도 
						
			try {
				bytes = photovo.getFiledata().getBytes(); 
				// getBytes()는 첨부된 파일을 바이트단위로 파일을 다 읽어오는 것이다. 
				/* 2-1. 첨부된 파일을 읽어오는 것
					    첨부한 파일이 강아지.png 이라면
					    이파일을 WAS(톰캣) 디스크에 저장시키기 위해
					    byte[] 타입으로 변경해서 받아들인다.
				*/
				// 2-2. 이제 파일올리기를 한다.
				String original_name = photovo.getFiledata().getOriginalFilename();
				//  photovo.getFiledata().getOriginalFilename() 은 첨부된 파일의 실제 파일명(문자열)을 얻어오는 것이다. 
				newFilename = fileManager.doFileUpload(bytes, original_name, path);
				
		//      System.out.println(">>>> 확인용 newFileName ==> " + newFileName); 
				
				int width = fileManager.getImageWidth(path+File.separator+newFilename);
		//		System.out.println("확인용 >>>>>>>> width : " + width);
				
				if(width > 600) {
					width = 600;
					newFilename = largeThumbnailManager.doCreateThumbnail(newFilename, path);
				}
		//		System.out.println("확인용 >>>>>>>> width : " + width);
				
				String CP = request.getContextPath();  // board
				file_result += "&bNewLine=true&sFileName="+newFilename+"&sWidth="+width+"&sFileURL="+CP+"/resources/photo_upload/"+newFilename; 
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			// 파일이 존재하지 않는다라면
			file_result += "&errstr=error";
		}
	    
		return "redirect:" + callback + "?callback_func="+callback_func+file_result;
	}
	
	// ==== #스마트에디터4. 드래그앤드롭을 사용한 다중사진 파일업로드 ====
	@RequestMapping(value="/bookmanage/image/multiplePhotoUpload.ana", method={RequestMethod.POST})
	public void multipleNoticePhotoUpload(HttpServletRequest request, HttpServletResponse response) {
	    
		/*
		   1. 사용자가 보낸 파일을 WAS(톰캣)의 특정 폴더에 저장해주어야 한다.
		   >>>> 파일이 업로드 되어질 특정 경로(폴더)지정해주기
		        우리는 WAS 의 webapp/resources/photo_upload 라는 폴더로 지정해준다.
		 */
		
		// WAS 의 webapp 의 절대경로를 알아와야 한다. 
		HttpSession session = request.getSession();
		String root = session.getServletContext().getRealPath("/"); 
		String path = root + "resources"+File.separator+"photo_upload";
		// path 가 첨부파일들을 저장할 WAS(톰캣)의 폴더가 된다. 
		
	//	System.out.println(">>>> 확인용 path ==> " + path); 
		// >>>> 확인용 path ==> C:\springworkspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Board\resources\photo_upload   
		
		File dir = new File(path);
		if(!dir.exists())
			dir.mkdirs();
		
		String strURL = "";
		
		try {
			if(!"OPTIONS".equals(request.getMethod().toUpperCase())) {
	    		String filename = request.getHeader("file-name"); //파일명을 받는다 - 일반 원본파일명
	    		
	    //		System.out.println(">>>> 확인용 filename ==> " + filename); 
	    		// >>>> 확인용 filename ==> berkelekle%ED%8A%B8%EB%9E%9C%EB%94%9405.jpg
	    		
	    		InputStream is = request.getInputStream();
	    	/*
	          	요청 헤더의 content-type이 application/json 이거나 multipart/form-data 형식일 때,
	          	혹은 이름 없이 값만 전달될 때 이 값은 요청 헤더가 아닌 바디를 통해 전달된다. 
	          	이러한 형태의 값을 'payload body'라고 하는데 요청 바디에 직접 쓰여진다 하여 'request body post data'라고도 한다.

               	서블릿에서 payload body는 Request.getParameter()가 아니라 
            	Request.getInputStream() 혹은 Request.getReader()를 통해 body를 직접 읽는 방식으로 가져온다. 	
	    	 */
	    		String newFilename = fileManager.doFileUpload(is, filename, path);
	    	
				int width = fileManager.getImageWidth(path+File.separator+newFilename);
       //		System.out.println(">>>> 확인용 width ==> " + width);
				
				if(width > 600) {
					width = 600;
					newFilename = largeThumbnailManager.doCreateThumbnail(newFilename, path);
				}
		//		System.out.println(">>>> 확인용 width ==> " + width);
				// >>>> 확인용 width ==> 600
				// >>>> 확인용 width ==> 121
	    	
				String CP = request.getContextPath(); // board
			
				strURL += "&bNewLine=true&sFileName="; 
            	strURL += newFilename;
            	strURL += "&sWidth="+width;
            	strURL += "&sFileURL="+CP+"/resources/photo_upload/"+newFilename;
	    	}
		
	    	/// 웹브라우저상에 사진 이미지를 쓰기 ///
			PrintWriter out = response.getWriter();
			out.print(strURL);
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}// end of void multiplePhotoUpload(HttpServletRequest req, HttpServletResponse res)----------------
	// ********************************************** 스마트에디터 ************************************************************** //
	
	
	
	
}
