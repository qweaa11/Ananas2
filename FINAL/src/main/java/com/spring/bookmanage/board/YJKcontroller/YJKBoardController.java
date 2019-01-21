package com.spring.bookmanage.board.YJKcontroller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.bookmanage.board.YJKmodel.YJKBoardVO;
import com.spring.bookmanage.board.YJKservice.InterYJKBoardService;
import com.spring.bookmanage.common.FileManager;
import com.spring.bookmanage.common.MyUtil;
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
		
		/*
		   웹페이지에 요청form이 enctype="multipart/form-data" 으로 되어있어서 Multipart 요청(파일처리 요청)이 들어올때 
		   컨트롤러에서는 HttpServletRequest 대신 MultipartHttpServletRequest 인터페이스를 사용해야 한다.
		  MultipartHttpServletRequest 인터페이스는 HttpServletRequest 인터페이스와 MultipartRequest 인터페이스를 상속받고있다.
		   즉, 웹 요청 정보를 얻기 위한 getParameter()와 같은 메소드와 Multipart(파일처리) 관련 메소드를 모두 사용가능하다.
		 
		 ===== 사용자가 쓴 글에 파일이 첨부되어 있는 것인지 아니면 파일첨부가 안된것인지 구분을 지어주어야 한다. =====
		 ========= !!첨부파일이 있는지 없는지 알아오기 시작!! ========= */
		MultipartFile attach = boardvo.getAttach();
		
		if(!attach.isEmpty()) {
			// attach 가 비어있지 않다면 (즉, 첨부파일이 있는 경우라면)
			
			/*
			 	1.	사용자가  보낸 파일을 WAS(톰캣)의 특정폴더에 저장해주어야 한다.
			 	>>>> 파일이 업로드 되어질 특정경로 (폴더)지정해주기
			 	우리는 WAS의 webapp/resources/files 라는 폴더로 지정해주겠다.
			 */
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
				// 파일을 올린 후 예를 들어, 20190107238218494214.png 와 같은 파일명을 얻어온다.
				
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
		
		List<YJKBoardVO> boardList = service.getboardList();
				
	//	System.out.println(boardList);
		
		// ==== 글조회수(readCount)증가 (DML문 update)는
		/*      반드시 해당 글제목을 클릭했을 경우에만 글조회수가 증가되고 
		                  이전글보기, 다음글보기를 했을 경우나 웹브라우저에서 새로고침(F5)을 했을 경우에는 증가가 안되도록 한다.
                                    이것을 하기 위해서는 우리는 session 을 이용하여 처리한다.===== */
		
		HttpSession session = req.getSession();
		session.setAttribute("readCountPermission", "yes");
		
		req.setAttribute("boardList", boardList);
		
		// 특정 글 제목을 클릭하여 상세내용을 본 이후 페이징 처리된 해당 페이지로 그대로 돌아가기 위해 
		// 돌아갈 페이지를 위해서 뷰단으로 넘겨준다.
		
		String gobackURL = MyUtil.getCurrentURL(req);
		req.setAttribute("gobackURL", gobackURL);
		
		return "board/boardList.tiles1";
	}
	
	// ==== 글 1개를 보여주는 페이지 요청 ====
	@RequestMapping(value="/view.ana", method= {RequestMethod.GET})
	public String view(HttpServletRequest req) {
		
		String idx = req.getParameter("idx");// 글 번호 받아오기
		
		System.out.println("idx:"+idx);
		
		String gobackURL = req.getParameter("gobackURL");
		// 특정 글 제목을 클릭하여 상세내용을 본 이후 페이징 처리된 해당 페이지로 그대로 돌아가기 위해 
		// 돌아갈 페이지를 위해서 뷰단으로 넘겨준다.
		
		System.out.println("gobackURL : " + gobackURL);
		
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
			
			// 로그인 되어진 사용자 정보를 읽어온다.
			System.out.println("libid:"+libid);
			
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
		
		return "board/view.tiles1";				
	}
	
}
