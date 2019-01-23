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

import com.spring.bookmanage.board.YJKmodel.YJKBoardVO;
import com.spring.bookmanage.board.YJKmodel.YJKReplyVO;
import com.spring.bookmanage.board.YJKservice.InterYJKBoardService;
import com.spring.bookmanage.common.FileManager;
import com.spring.bookmanage.common.MyUtil;
import com.spring.bookmanage.library.Yjkmodel.LibraryVO;
import com.spring.bookmanage.library.Yjkmodel.YjkVO;

@Controller
public class YJKBoardController {
	
	//==== 의존객체 주입하기(DI : Dependency Injection)  ====
	@Autowired
	private InterYJKBoardService service;
	
	// ==== 파일업로드 및 파일 다운로드를 해주는 FileManager 클래스 의존객체 주입하기 ====
	@Autowired
	private FileManager fileManager;
	
	// ==== 글쓰기 페이지 보여주기 ==== //
	@RequestMapping(value="/boardAdd.ana",method= {RequestMethod.GET})
	public String boardAdd(HttpServletRequest req, HttpServletResponse res) {
		
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
	public String boardAddEnd(YJKBoardVO boardvo, MultipartHttpServletRequest req) {
		
		// ========= !!첨부파일이 있는지 없는지 알아오기 시작!! =========
		MultipartFile attach = boardvo.getAttach();
		
		if(!attach.isEmpty()) {
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
			try {
				bytes = attach.getBytes();
				// getBytes() 는 첨부된 파일을 바이트 단위로 파일을 다 읽어오는 것이다.
				
				newFileName = fileManager.doFileUpload(bytes, attach.getOriginalFilename(), path);
				// 첨부된 파일을 WAS(톰캣)의 디스크로 파일올리기를 하는 것이다.
				
				// 3. BoardVO boardvo 에 fileName 값과 orgFilename 값과 fileSize 값을 넣어주기
				boardvo.setFileName(newFileName);
				boardvo.setOrgFileName(attach.getOriginalFilename());
				
				fileSize = attach.getSize();
				// 첨부한 파일의 크기인데 리턴타입은 long 타입이다.
				
				boardvo.setFileSize(String.valueOf(fileSize));
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		// ==== !!첨부파일이 있는지 없는지 알아오기 끝!! ==== // 
		// ==== 파일첨부가 없는 경우 또는 있는 경우 Service 단으로 호출하기 ==== //
	
		int n = 0;
		if(attach.isEmpty()) {
			// 파일첨부가 없다라면
			n = service.boardAdd(boardvo);
		}
		else {
			// 파일첨부가 있다라면
			n = service.boardAdd_withFile(boardvo);
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
	public String boardList(HttpServletRequest req) {
		
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
		
		// 페이지바 만들기
		String pagebar = "<ul>";
		
		pagebar += MyUtil.getPageBarWithSearch(sizePerPage, blockSize, totalPage, currentShowPageNo, colname, search, "", "boardList.ana");
		
		pagebar += "<ul>";
		
		
		
		HttpSession session = req.getSession();
		session.setAttribute("readCountPermission", "yes");
		
		req.setAttribute("boardList", boardList);
		
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
	public String boardView(HttpServletRequest req) {
		
		String idx = req.getParameter("idx");// 글 번호 받아오기
		
		String gobackURL = req.getParameter("gobackURL");
		// 특정 글 제목을 클릭하여 상세내용을 본 이후 페이징 처리된 해당 페이지로 그대로 돌아가기 위해 
		// 돌아갈 페이지를 위해서 뷰단으로 넘겨준다.
		
		YJKBoardVO boardvo = null; // 글 1개를 저장한 객체변수
				
		HttpSession session = req.getSession();
		YjkVO loginuser = (YjkVO)session.getAttribute("loginuser");
		// 로그인 되어진 사용자 정보를 가져온다.
		
		String readCountPermission = (String)session.getAttribute("readCountPermission");
		
		String libid = null;
		
		if(readCountPermission != null &&
		   "yes".equals(readCountPermission)) {
			// 글 1개를 보기 위해 
			// http://localhost:9090/bookmanage/boardList.ana(목록)
			// 들어온 경우
			if(loginuser != null) {
				libid = loginuser.getLibid();
			}
			System.out.println("idx:"+idx);
			// 로그인 되어진 사용자 정보를 읽어온다.
			
			boardvo = service.getView(idx, libid);
			
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
		}
		
		req.setAttribute("boardvo", boardvo);
		req.setAttribute("gobackURL", gobackURL);
		
		return "board/boardView.tiles1";				
	}
	
	// ==== 댓글쓰기 ====
		@RequestMapping(value="/boardAddComment.ana", method={RequestMethod.POST})
		@ResponseBody
		public HashMap<String, String> boardAddComment(YJKReplyVO replyvo) 
			throws Throwable {
			
			HashMap<String, String> returnMap = new HashMap<String, String>();
			
			// 댓글쓰기(AJAX 처리)
			int n = service.boardAddComment(replyvo);
			
			if(n == 1) {
				// 댓글쓰기 및 원 게시물(Board 테이블)에 댓글의 갯수(1씩 증가)
				returnMap.put("NAME", replyvo.getName());
				returnMap.put("CONTENT", replyvo.getContent());
				returnMap.put("REGDATE", MyUtil.getNowTime());
			}
			
			return returnMap;
			
		}
		
		// ==== 댓글내용 가져오기 ====
		@RequestMapping(value="/replyList.ana", method={RequestMethod.GET})
		@ResponseBody
		public List<HashMap<String, Object>> boardcommentList(HttpServletRequest req) {

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
		@RequestMapping(value="/getCommentTotalPage.ana", method={RequestMethod.GET})
		@ResponseBody
		public HashMap<String, Integer> getCommentTotalPage(HttpServletRequest req) {
			
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
		public String boardEdit(HttpServletRequest req, HttpServletResponse res) {
			
			// 수정해야 할 글 번호 가져오기
			String idx = req.getParameter("idx");
			
			// 수정해야 할 글 내용 가져오기
			YJKBoardVO boardvo = service.getViewWithNoAddCount(idx); // 조회수 증가 없이 글 1개 가져오는 것
			
			/*HttpSession session = req.getSession();
			YjkVO loginadmin = (YjkVO)session.getAttribute("loginadmin");
			
			if(!loginadmin.getLibid().equals(boardvo.getLibid_fk())) {
				String msg = "다른 관리자의 글은 수정이 불가합니다.";
				String loc = "javascript:history.back();";
				
				req.setAttribute("msg", msg);
				req.setAttribute("loc", loc);
				
				return "msg";
			}
			else {*/
				req.setAttribute("boardvo", boardvo);
				
				return "board/boardEdit.tiles1";
		//	}
			
		}
		
		// ==== 글 수정 페이지 완료하기 ====
		@RequestMapping(value="/boardEditEnd.ana", method={RequestMethod.POST})
		public String boardEditEnd(YJKBoardVO boardvo, HttpServletRequest req) {
			
			// 원래 암호와 글 수정 페이지에 입력한 암호와 비교하기
			String adminPW = boardvo.getPw();
			
			HashMap<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("IDX", boardvo.getIdx());
			paraMap.put("PW", boardvo.getPw());
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
		public String boardDel(HttpServletRequest req, HttpServletResponse res) {
			
			// 삭제 할 글 번호 가져오기
			String idx = req.getParameter("idx");
			
			// 삭제 할 글 내용 가져오기
			YJKBoardVO boardvo = service.getViewWithNoAddCount(idx);
			
			/*HttpSession session = req.getSession();
			YjkVO loginuser = (YjkVO)session.getAttribute("loginuser");
			
			if(!loginuser.getLibid().equals(boardvo.getLibid_fk())) {
				String msg = "다른 관리자의 글은 삭제가 불가합니다.";
				String loc = "javascirpt:history.back();";
				
				req.setAttribute("msg", msg);
				req.setAttribute("loc", loc);
				
				return "msg";
			}
			else {*/
				req.setAttribute("idx", idx);
		//	}
			
			return "board/boardDel.tiles1";
			
		}
		
		// ==== 글 삭제 페이지 완료하기 ====
		@RequestMapping(value="/boardDelEnd.ana", method={RequestMethod.POST})
		public String boardDelEnd(HttpServletRequest req) throws Throwable {
			
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
		
		// ==== 첨부파일 다운로드 받기 =====
		@RequestMapping(value="/download.ana", method={RequestMethod.GET})
		public void download(HttpServletRequest req, HttpServletResponse res) {
			
			String idx = req.getParameter("idx");
			// 첨부파일이 있는 글번호
			
			YJKBoardVO boardvo = service.getViewWithNoAddCount(idx);
			// 조회수 증가 없이 1개글 가져오기
			
			String fileName = boardvo.getFileName();
			//  WAS(톰캣) 디스크에 저장 된 파일명이다.
			
			String orgFilename = boardvo.getOrgFileName();
			// 구현환경 및 활용기술 및 팀내역할.txt 와 같은 것을 가져온다.

			HttpSession session = req.getSession();
			String root = session.getServletContext().getRealPath("/");
			String path = root+"resources"+File.separator+"files";
			// path 가 첨부파일들을 저장 된  WAS(톰캣)의 폴더가 된다.
			
			
			// *** 다운로드 하기 *** //
			// 다운로드가 실패 할 경우 메시지를 띄워주기 위해서 
			// boolean 타입 변수 flag 를 선언한다.
			boolean flag = false;
			
			fileManager.doFileDownload(fileName, orgFilename, path, res);
			// 다운로드가 성공하면 true를 반환하고 
			// 다운로드가 실패하면 false를 반환한다.
			
			if(!flag) {
				// 다운로드가 실패 할 경우 메시지를 띄워준다.
				res.setContentType("text/html; charset=UTF-8");
				
				try {
					PrintWriter out = res.getWriter();
					// PrintWriter out 이 웹브라우저 상에 내용물을 기재(써주는)해주는 객체이다.
					
					out.println("<script type ='text/javascript'>alert('파일 다운로드가 실패했습니다!!')</script>");
					
				} catch (IOException e) {
		
					e.printStackTrace();
				}
				
				
			}
			
		}

}
