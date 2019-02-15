package com.spring.bookmanage.board.YJKcontroller;

import java.io.File;
import java.io.IOException;
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

import com.spring.bookmanage.JDSmodel.LibrarianVO;
import com.spring.bookmanage.board.YJKmodel.YJKAttachFileVO;
import com.spring.bookmanage.board.YJKmodel.YJKBoardVO;
import com.spring.bookmanage.board.YJKmodel.YJKReplyVO;
import com.spring.bookmanage.board.YJKservice.InterYJKBoardService;
import com.spring.bookmanage.common.FileManager;
import com.spring.bookmanage.common.MyUtil;
import com.spring.bookmanage.library.Yjkmodel.LibraryVO;
import com.spring.bookmanage.library.Yjkmodel.YjkVO;

@Controller
public class YJKBoardController {
	
	// 페이지바
	public static String getPageBarWithSearch(int sizePerPage, int blockSize, int totalPage, int currentShowPageNo,
			  String colname, String search, String period, String url) {
	String pageBar = "";
	int loop = 1;
	int pageNo = ((currentShowPageNo-1)/blockSize)*blockSize+1;// 공식임!!!
	
	// currentShowPageNo 가 1~10 일때 pageNo 는 1
	// currentShowPageNo 가 11~20 일때 pageNo 는 11
	// currentShowPageNo 가 21~30 일때 pageNo 는 21
	String str_pageNo = "";
	
	if(pageNo == 1) {
		str_pageNo = "";
	} else {
		str_pageNo = "&nbsp;<a href=\""+url+"?currentShowPageNo="+(pageNo - 1)+"&sizePerPage="+sizePerPage
		+"&colname="+colname+"&search="+search+"&period="+period+"\" >"
		+"<span><<</span></a>&nbsp;";
	} // end of if~else
	
	pageBar += str_pageNo;
	
	while (!(pageNo > totalPage || loop > blockSize)) {
		if (pageNo == currentShowPageNo) {
			str_pageNo = "&nbsp;<a class=\"active\">"+pageNo+"</a>&nbsp;";
		} else {
			str_pageNo = "&nbsp;<a href=\""+url+"?currentShowPageNo="+pageNo+"&sizePerPage="+sizePerPage
			+"&colname="+colname+"&search="+search+"&period="+period+"\" >"+pageNo+"</a>"
			+"&nbsp;";
		} // end of if~else
	
		pageBar += str_pageNo;
		pageNo++;
		loop++;
	} // end of while
	
	if (pageNo > totalPage) {
		str_pageNo = "";
	} else {
		str_pageNo = "&nbsp;<a href=\""+url+"?currentShowPageNo="+pageNo+"&sizePerPage="+sizePerPage
		+ "&colname="+colname+"&search="+search+"&period="+period+"\" >"
		+ "<span>>></span></a>&nbsp;";
	} // end of if~else
	
		pageBar += str_pageNo;
	
		return pageBar;
	}// end of getPageBarWithSearch
	
	//==== 의존객체 주입하기(DI : Dependency Injection)  ====
	@Autowired
	private InterYJKBoardService service;
	
	// ==== 파일업로드 및 파일 다운로드를 해주는 FileManager 클래스 의존객체 주입하기 ====
	@Autowired
	private FileManager fileManager;
	
	// ==== 글쓰기 페이지 보여주기 ==== //
	@RequestMapping(value="/boardAdd.ana",method= {RequestMethod.GET})
	public String required_boardAdd(HttpServletRequest req, HttpServletResponse res) {
		
		// ==== 답변 글쓰기 추가된 경우 ====
		String root = req.getParameter("root");
		String groupno = req.getParameter("groupno");
		String depthno = req.getParameter("depthno");
		
		req.setAttribute("root", root);
		req.setAttribute("groupno", groupno);
		req.setAttribute("depthno", depthno);
		
		return "board/boardAdd.tiles1";
	}
	
	// ==== 글쓰기 완료 요청 ==== //
	@RequestMapping(value="/boardAddEnd.ana",method= {RequestMethod.POST})
	public String boardAddEnd(MultipartHttpServletRequest req, HttpServletResponse res, YJKBoardVO boardvo) {

		// ========= !!첨부파일이 있는지 없는지 알아오기 시작!! =========
	//	MultipartFile attach = boardvo.getAttach();
		
		List<MultipartFile> attachList = req.getFiles("attach"); // 다중파일첨부로 변경하면서 List에 넣기
		int idx = service.selectBoardIdx();
		HashMap<String, String> boardMap = new HashMap<String, String>();
		
		boardMap.put("LIBID_FK",boardvo.getLibid_fk());
		boardMap.put("NAME", boardvo.getName());
		boardMap.put("SUBJECT", boardvo.getSubject());
		boardMap.put("CONTENT", boardvo.getContent());
		boardMap.put("PW", boardvo.getPw());
		boardMap.put("READCOUNT", boardvo.getReadCount());
		boardMap.put("REGDATE, ", boardvo.getRegDate());
		boardMap.put("STATUS", boardvo.getStatus());
		boardMap.put("GROUPNO", boardvo.getGroupno());
		boardMap.put("ROOT", boardvo.getRoot());
		boardMap.put("DEPTHNO", boardvo.getDepthno());
		boardMap.put("IDX", String.valueOf(idx));
				
		List<HashMap<String, String>> boardMapList = new ArrayList<HashMap<String, String>>();
		
		if(attachList != null) {
			// attach 가 비어있지 않다면 (즉, 첨부파일이 있는 경우라면)
			// WAS의 webapp 의 절대경로를 알아와야 한다.
			HttpSession session = req.getSession();
			String root = session.getServletContext().getRealPath("/");
			String path = root+"resources"+File.separator+"files";
			// path 가 첨부파일들을 저장할 WAS(톰캣)의 폴더가 된다.
			
			// 2. 파일첨부를 위한 변수의 설정 및 값을 초기화 한 후 파일 올리기
			String newFileName = "";
			// WAS(톰캣) 디스크에 저장 할 파일명
			
			byte[] bytes = null;
			// 첨부파일을 WAS(톰캣) 디스크에 저장 할 떄 사용되는 용도
			
			long fileSize = 0;
			// 파일크기를 읽어오기 위한 용도
			
			for(int i=0; i<attachList.size(); i++) {
				try {
					bytes = attachList.get(i).getBytes();
					// getBytes() 는 첨부된 파일을 바이트 단위로 파일을 다 읽어오는 것이다.
					
					newFileName = fileManager.doFileUpload(bytes, attachList.get(i).getOriginalFilename(), path);
					// 첨부된 파일을 WAS(톰캣)의 디스크로 파일올리기를 하는 것이다.
					
					fileSize = attachList.get(i).getSize();
										
					HashMap<String, String> boardFileMap = new HashMap<String, String>();
					
					boardFileMap.put("BOARD_IDX_FK", String.valueOf(idx));
					boardFileMap.put("FILENAME", newFileName);
					boardFileMap.put("ORGFILENAME", attachList.get(i).getOriginalFilename());					
					boardFileMap.put("FILESIZE", String.valueOf(fileSize));
										
					boardMapList.add(boardFileMap);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}// end of if(attachList != null)-------------
		// ==== !!첨부파일이 있는지 없는지 알아오기 끝!! ==== // 
		// ==== 파일첨부가 없는 경우 또는 있는 경우 Service 단으로 호출하기 ==== //
	
		int n = 0;
		int m = 0;
		int count = 0;
		
		
		if(attachList == null) {
			
			// 파일첨부가 없다라면
			n = service.boardAdd(boardMap);
			
		}
		else if(attachList != null) {
			// 파일첨부가 있다라면
			
			n = service.boardAdd(boardMap);
			
			for(int i=0; i<boardMapList.size(); i++) {
				m = service.boardAdd_withFile(boardMapList.get(i));
				if(m == 1) count++;
			}
			
			if(boardMapList.size() == count) {
				n = 1;
			}
			else {
				n = 0;
			}
		}
		
		String loc = "";
		if(n==1) {
			loc = req.getContextPath()+"/boardList.ana";
		}
		else {
			loc = req.getContextPath()+"/index.ana";
		}
		
		req.setAttribute("n", n);
		req.setAttribute("loc", loc);
		
		return "board/boardAddEnd.tiles1";
	}
	
	// ==== 전체글 목록 페이지 보여주기 ==== //
	@RequestMapping(value="/boardList.ana",method= {RequestMethod.GET})
	public String boardList(HttpServletRequest req, HttpServletResponse res) {
		
		List<YJKBoardVO> boardList = null;
				
		// 검색어
		String colname = req.getParameter("colname");
		String search = req.getParameter("search");
		
		// 페이징 처리
		String str_currentShowPageNo = req.getParameter("currentShowPageNo");
		
		int totalCount = 0;			// 총 게시물 건수		
		int sizePerPage = 10;		// 한 페이지당 보여줄 게시물 건수
		int currentShowPageNo = 0;	// 현재 보여주는 페이지 번호(초기치 1페이지)
		int totalPage = 0;			// 웹 브라우저상에 보여줄 총 페이지 수
		
		int startRno = 0;	// 시작 행 번호
		int endRno = 0;		// 끝 행 번호
		
		int blockSize = 10;	// 페이지바에 보여줄 페이지 수
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("COLNAME", colname);
		paraMap.put("SEARCH", search);
		
		// ==== 총 페이지수(totalPage) 구하기(검색조건이 있을 때, 없을 때 총 페이지수) ====
		// 총 게시물 건수 구하기
		
		if(search != null &&
		   !search.trim().equals("") &&
		   !search.trim().equals("null")) {
			// 검색이 있는 경우
			totalCount = service.getTotalCountWithSearch(paraMap);
		}
		else {
			// 검색이 없는 경우
			totalCount = service.getTotalCountWithNoSearch();
		}
		
		totalPage = (int)Math.ceil((double)totalCount/sizePerPage);
		
		if(str_currentShowPageNo == null) {
			// 게시판 초기화면일 경우
			
			currentShowPageNo = 1;
		}
		else {
			// 특정페이지를 보고자 조회 한 경우
			try {
				currentShowPageNo = Integer.parseInt(str_currentShowPageNo);
				
				if(currentShowPageNo < 1 || currentShowPageNo > totalPage) {
					currentShowPageNo = 1;
				}
			}catch(NumberFormatException e) {
				currentShowPageNo = 1;
			}
		}
			
		// **** 가져올 게시글의 범위를 구한다.
			
		startRno = ((currentShowPageNo-1)*sizePerPage)+1;
		endRno = startRno + sizePerPage - 1;
		
		paraMap.put("STARTRNO", String.valueOf(startRno));
		paraMap.put("ENDRNO", String.valueOf(endRno));
		
		boardList = service.getboardList(paraMap);
		
		List<YJKBoardVO> boardList2 = service.getAttachFileCount(boardList);
		
		List<YJKBoardVO> boardList3 = service.getorgTextCount(boardList2);
		
		
		// 페이지바 만들기
		String pagebar = "<ul>";
		
		pagebar += getPageBarWithSearch(sizePerPage, blockSize, totalPage, currentShowPageNo, colname, search, "", "boardList.ana");
		
		pagebar += "<ul>";
		
		
		HttpSession session = req.getSession();
		session.setAttribute("readCountPermission", "yes");
		
		req.setAttribute("boardList3", boardList3);
		req.setAttribute("colname", colname);	// view단에서 검색어를 유지시키려고 보낸다.
		req.setAttribute("search", search);		// view단에서 검색어를 유지시키려고 보낸다.
		req.setAttribute("pagebar", pagebar);
		
		// 특정 글 제목을 클릭하여 상세내용을 본 이후 페이징 처리된 해당 페이지로 그대로 돌아가기 위해 
		// 돌아갈 페이지를 위해서 뷰단으로 넘겨준다.
		
		String gobackURL = MyUtil.getCurrentURL(req);
		req.setAttribute("gobackURL", gobackURL);
		
		return "board/boardList.tiles1";
	}
	
	// ==== 글 1개를 보여주는 페이지 요청 ====
	@RequestMapping(value="/boardView.ana", method= {RequestMethod.GET})
	public String boardView(HttpServletRequest req, HttpServletResponse res) {
		
		String idx = req.getParameter("idx");// 글 번호 받아오기
		
		String gobackURL = req.getParameter("gobackURL");
		// 특정 글 제목을 클릭하여 상세내용을 본 이후 페이징 처리된 해당 페이지로 그대로 돌아가기 위해 
		// 돌아갈 페이지를 위해서 뷰단으로 넘겨준다.
		
		YJKBoardVO boardvo = null; // 글 1개를 저장한 객체변수
		List<YJKAttachFileVO> attachfilevo = null; // 첨부파일을 저장한 객체변수
						
		HttpSession session = req.getSession();
		
		LibrarianVO loginLibrarian = (LibrarianVO)session.getAttribute("loginLibrarian");
		// 로그인 되어진 사용자 정보를 가져온다.
		
		String readCountPermission = (String)session.getAttribute("readCountPermission");
		
		String libid = null;
		
		if(readCountPermission != null &&
		   "yes".equals(readCountPermission)) {
			// 글 1개를 보기 위해 
			// http://localhost:9090/bookmanage/boardList.ana(목록)
			// 들어온 경우
			if(loginLibrarian != null) {
				libid = loginLibrarian.getLibid();
			}			
			boardvo = service.getView(idx, libid);
			attachfilevo = service.FileView(idx);
			
			// !! 중요함 !!
			// 글 1개를 보기위해 글 목록을 거쳐온 경우라면
			// 확인 후 세션에서 제거한다.
			session.removeAttribute("readCountPermission");	
		}
		else {
			// 특정글 1개를 본 이후 새로고침(F5)을 한 경우나
			// 또는 특정글 1개를 본 이후 이전글보기, 다음글보기를 한 경우이다.
			// 이럴경우 우리는 글 조회수 증가없는 그냥 1개글만 보여주도록 한다.
			boardvo = service.getViewWithNoAddCount(idx);
			// 조회수(readCount)증가 없이 그냥 글 1개만 가져오는 것.
			attachfilevo = service.FileView(idx);
		}
		
		req.setAttribute("attachfilevo", attachfilevo);
		req.setAttribute("boardvo", boardvo);
		req.setAttribute("gobackURL", gobackURL);
		
		return "board/boardView.tiles1";				
	}
	
	// ==== 첨부파일 다운로드 받기 =====
	@RequestMapping(value="/boardDownload.ana", method={RequestMethod.GET})
	public void required_boardDownload(HttpServletRequest req, HttpServletResponse res) {
		String loc = "javascript:history.back();";
		String fileidx = req.getParameter("fileidx"); // 다운로드를 클릭한 해당 첨부파일의 idx값
		String idx = req.getParameter("idx");// 첨부파일이 있는 글번호
		
		YJKBoardVO boardvo = service.getViewWithNoAddCount(idx);
		// 조회수 증가 없이 1개글 가져오기
		YJKAttachFileVO attachfilevo = service.fileDownload(fileidx);
		
		boolean flag = false;
		
			
			String fileName = attachfilevo.getFilename();
			//  WAS(톰캣) 디스크에 저장 된 파일명이다.
			
			System.out.println(fileName);
			
			String orgFilename = attachfilevo.getOrgfilename();
			// 구현환경 및 활용기술 및 팀내역할.txt 와 같은 것을 가져온다.
			
			System.out.println(orgFilename);

			HttpSession session = req.getSession();
			String root = session.getServletContext().getRealPath("/");
			String path = root+"resources"+File.separator+"files";
			// path 가 첨부파일들을 저장 된  WAS(톰캣)의 폴더가 된다.
			// *** 다운로드 하기 *** //
			// 다운로드가 실패 할 경우 메시지를 띄워주기 위해서 
			// boolean 타입 변수 flag 를 선언한다.
			
			
			flag = fileManager.doFileDownload(fileName, orgFilename, path, res);
			// 다운로드가 성공하면 true를 반환하고 
			// 다운로드가 실패하면 false를 반환한다.
			
			if(!flag) {
				// 다운로드가 실패 할 경우 메시지를 띄워준다.
				res.setContentType("text/html; charset=UTF-8");
				req.setAttribute("loc", loc);
				try {
					PrintWriter out = res.getWriter();
					
					// PrintWriter out 이 웹브라우저 상에 내용물을 기재(써주는)해주는 객체이다.
					
					out.println("<script type ='text/javascript'>alert('파일 다운로드가 실패했습니다!!')</script>");
					
					
				} catch (IOException e) {
		
					e.printStackTrace();
				}	
			}	
		}
	
	// ==== 댓글쓰기 ====
	@RequestMapping(value="/boardAddComment.ana", method={RequestMethod.POST})
	@ResponseBody
	public HashMap<String, String> boardAddComment(HttpServletRequest req, HttpServletResponse res, YJKReplyVO replyvo) 
		throws Throwable {
		
		HashMap<String, String> returnMap = new HashMap<String, String>();
		//String commentCount_str = req.getParameter("commnetCount");
		//int commentCount = Integer.parseInt(commentCount_str) +1;
		
		// 댓글쓰기(AJAX 처리) 
		System.out.println(replyvo.getParentidx());
		int n = service.boardAddComment(replyvo);
		System.out.println(n+"=========== cnt");
		if(n == 1) {
			// 댓글쓰기 및 원 게시물(Board 테이블)에 댓글의 갯수(1씩 증가)
			returnMap.put("NAME", replyvo.getName());
			returnMap.put("CONTENT", replyvo.getContent());
			returnMap.put("REGDATE", MyUtil.getNowTime());
		}
		
		
		int cnt = service.getCommentCnt(replyvo);
		returnMap.put("COMMENTCOUNT", String.valueOf(cnt));
		System.out.println(cnt+"===========개수잘가져오는거확인1");
		return returnMap;
		
	}
		
	// ==== 댓글내용 가져오기 ====
	@RequestMapping(value="/replyList.ana", method={RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> boardcommentList(HttpServletRequest req, HttpServletResponse res) {

		List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
		
		String idx = req.getParameter("idx");
		// 원글의 글 번호 받아오기
		System.out.println("idx :"+idx);
		String currentShowPageNo = req.getParameter("currentShowPageNo");
		
		if(currentShowPageNo == null || "".equals(currentShowPageNo)) {
			currentShowPageNo = "1";
		}
		
		int sizePerPage = 5; // 한 페이지당 5개 댓글 보여주기
		
		int rno1 = Integer.parseInt(currentShowPageNo)*sizePerPage - (sizePerPage-1); 	// 공식!!!
		int rno2 = Integer.parseInt(currentShowPageNo)*sizePerPage;	// 공식!!!
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("IDX", idx);
		paraMap.put("RNO1", String.valueOf(rno1));
		paraMap.put("RNO2", String.valueOf(rno2));
		
		List<YJKReplyVO> commentList = service.listComment(paraMap);
		System.out.println(commentList);
		// 원글에 글번호에 대한 댓글 중 페이지 번호에 해당하는 댓글만 조회해온다.
		
		for(YJKReplyVO cmtvo : commentList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("NAME", cmtvo.getName());
			map.put("CONTENT", cmtvo.getContent());
			map.put("REGDATE", cmtvo.getRegdate());
			
			mapList.add(map);
		}
		
		
		return mapList;
	}
		
	// ==== 댓글 TotalPage 가져오기 ====
	@RequestMapping(value="/boardCommentTotalPage.ana", method={RequestMethod.GET})
	@ResponseBody
	public HashMap<String, Integer> getCommentTotalPage(HttpServletRequest req, HttpServletResponse res) {
		
		HashMap<String, Integer> returnMap = new HashMap<String, Integer>();
		
		String idx = req.getParameter("idx");
		// 원글의 글 번호를 받아와서 원글에 딸린 댓글의 갯수를 알아오려고 한다.
		
		String sizePerPage = req.getParameter("sizePerPage");
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("IDX", idx);
		paraMap.put("SIZEPERPAGE", sizePerPage);
		
		int totalCount = service.getCommentTotalCount(paraMap);
		// 원글에 글번호에 해당하는 댓글의 총갯수를 알아오기
		
		// -- 총 페이지수(totalPage) 구하기
		int totalPage = (int)Math.ceil((double)totalCount/Integer.parseInt(sizePerPage));
		
		returnMap.put("TOTALPAGE", totalPage);
		
		return returnMap;
	}
	
	// ==== 글 수정 페이지 요청 ====
	@RequestMapping(value="/boardEdit.ana", method={RequestMethod.GET})
	public String required_boardEdit(HttpServletRequest req, HttpServletResponse res) {
		
		// 수정해야 할 글 번호 가져오기
		String idx = req.getParameter("idx");
		
		// 수정해야 할 글 내용 가져오기
		YJKBoardVO boardvo = service.getViewWithNoAddCount(idx); // 조회수 증가 없이 글 1개 가져오는 것
		
		HttpSession session = req.getSession();

		LibrarianVO loginLibrarian = (LibrarianVO)session.getAttribute("loginLibrarian");
		
		if(!loginLibrarian.getLibid().equals(boardvo.getLibid_fk())) {
			String msg = "다른 관리자의 글은 수정이 불가합니다.";
			String loc = "javascript:history.back();";
			
			req.setAttribute("msg", msg);
			req.setAttribute("loc", loc);
			
			return "msg";
		}
		else {
			req.setAttribute("boardvo", boardvo);
			
			return "board/boardEdit.tiles1";
		}
		
	}
	
	// ==== 글 수정 페이지 완료하기 ====
	@RequestMapping(value="/boardEditEnd.ana", method={RequestMethod.POST})
	public String boardEditEnd(HttpServletRequest req, HttpServletResponse res, YJKBoardVO boardvo) {
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("IDX", boardvo.getIdx());
		paraMap.put("PW", boardvo.getPw()); // 원래 암호와 글 수정 페이지에 입력한 암호와 비교하기
		paraMap.put("SUBJECT", boardvo.getSubject());
		paraMap.put("CONTENT", boardvo.getContent());
		
		int result = service.boardEdit(paraMap);
		// 넘겨받은 값이 1 이면 update 성공 
		// 넘겨받은 값이 0 이면 update 실패(암호가 틀리므로)
		
		String msg = "";
		String loc = "";
		
		if(result == 0) {
			msg = "글 수정 실패!";
			loc = "javascript:history.back();";
		}
		else {
			msg = "글 수정 성공!";
			loc = req.getContextPath()+"/boardView.ana?idx="+boardvo.getIdx();
		}
		
		req.setAttribute("msg", msg);
		req.setAttribute("loc", loc);
		
		return "msg";
	}
	
	// ==== 글 삭제 페이지 요청 =====
	@RequestMapping(value="/boardDel.ana", method={RequestMethod.GET})
	public String required_boardDel(HttpServletRequest req, HttpServletResponse res) {
		
		// 삭제 할 글 번호 가져오기
		String idx = req.getParameter("idx");
		
		// 삭제 할 글 내용 가져오기
		YJKBoardVO boardvo = service.getViewWithNoAddCount(idx);
		
		HttpSession session = req.getSession();
		
		LibrarianVO loginLibrarian = (LibrarianVO)session.getAttribute("loginLibrarian");
		
		
		
		if(!loginLibrarian.getLibid().equals(boardvo.getLibid_fk())) {
			String msg = "다른 관리자의 글은 삭제가 불가합니다.";
			String loc = "javascript:history.back();";
			
			req.setAttribute("msg", msg);
			req.setAttribute("loc", loc);
			
			return "msg";
		}
		else {
			req.setAttribute("idx", idx);
			
			return "board/boardDel.tiles1";
		}

	}
	
	// ==== 글 삭제 페이지 완료하기 ====
	@RequestMapping(value="/boardDelEnd.ana", method={RequestMethod.POST})
	public String boardDelEnd(HttpServletRequest req, HttpServletResponse res) throws Throwable {
		
		String idx = req.getParameter("idx");
		String pw = req.getParameter("pw");
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("IDX", idx);
		paraMap.put("PW", pw);
		
		int result = service.boardDel(paraMap);
		/*
		 넘겨받은 값이 1이면 글 삭제 성공,
		 넘겨받은 값이 0이면 글 삭제 실패(암호가 틀리므로)
		 */
		
		String msg = "";
		String loc = "";
		
		if(result == 0) {
			msg = "글 삭제 실패!!";
			loc = "javascript:history.back();";
		}
		else {
			msg = "글 삭제 성공!!";
			loc = req.getContextPath()+"/boardList.ana";
		}
		
		req.setAttribute("msg", msg);
		req.setAttribute("loc", loc);

		return "msg";
	}	

}
