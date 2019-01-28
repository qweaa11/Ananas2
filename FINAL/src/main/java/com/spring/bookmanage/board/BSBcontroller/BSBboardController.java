package com.spring.bookmanage.board.BSBcontroller;

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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.bookmanage.JDSmodel.AdminVO;
import com.spring.bookmanage.JDSmodel.LibrarianVO;
import com.spring.bookmanage.board.BSBmodel.BSBboardVO;
import com.spring.bookmanage.board.BSBmodel.BSBcommentVO;
import com.spring.bookmanage.board.BSBmodel.PhotoVO;
import com.spring.bookmanage.board.BSBservice.BSBInterBoardService;
import com.spring.bookmanage.common.AES256;
import com.spring.bookmanage.common.BSBUtil;
import com.spring.bookmanage.common.FileManager;
import com.spring.bookmanage.common.LargeThumbnailManager;
import com.spring.bookmanage.common.MyUtil; 
import com.spring.bookmanage.common.SHA256;
import com.spring.bookmanage.login.BSBmodel.BSBMemberVO;
import com.spring.bookmanage.login.BSBservice.BSBInterLoginService;



@Controller
@Component
public class BSBboardController {
	
	// =====#35.의존객체 주입하기(DI : Dependency Injection) =====
			@Autowired
			private BSBInterBoardService service;
			
			// ===== #139. 파일업로드 및 파일다운로드를 해주는 FileManager 클래스 의존객체 주입하기 (DI: Dependency Injection) =====
			@Autowired
			private FileManager fileManager;
			
			// ===== #스마트에디터3. 스마트에디터 사용시 사진첨부를 할경우 원본 사진의 크기가 아주 클 경우
			// 이미지 width 의 크기를 적절하게 줄여주는 클래스 객체 의존객체 주입하기(DI: Dependency Injection) =====
			@Autowired
			private LargeThumbnailManager largeThumbnailManager;
			
			
	
	@RequestMapping(value="/board.ana",method= {RequestMethod.GET})
	public String board(HttpServletRequest req) {
		
		List<BSBboardVO> boardList = null;
		
		String colname = req.getParameter("colname");
		String search = req.getParameter("search");
		
		HashMap<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("COLNAME", colname);
		paraMap.put("SEARCH", search);
		
		// ===== #110. 페이징 처리 =====
					// 페이징 처리를 통한 글목록 보여주기는 예를들어, 3페이지의 내용을 보고자 한다면 
					// http://localhost:9090/board/list.action?colname=name&search=&currentShowPageNo=3 와 같이 해주어야 한다.
					String str_currentShowPageNo = req.getParameter("currentShowPageNo");
					
					int totalCount = 0;         // 총게시물 건수
					int sizePerPage = 10;       // 한 페이지당 보여줄 게시물 건수
					int currentShowPageNo = 0;  // 현재 보여주는 페이지번호로서, 초기치로는 1페이지로 한다.
					int totalPage = 0;          // 총 페이지수(웹브라우저상에 보여줄 총 페이지 갯수)
					
					int startRno = 0;           // 시작 행 번호 
					int endRno = 0;             // 끝 행 번호
					
					int blockSize = 10;         // "페이지바" 에 보여줄 페이지의 갯수
					
					/*
					  === 총 페이지수(totalPage) 구하기 ===
					  검색조건이 없을때(search값이 null 또는 "" 인 경우)의 총 페이지 수와
					  검색조건이 있을때(search값이 null 이 아니고 "" 아닌 경우)의 총 페이지 수를 구해야 한다.			  
					   
					*/
					
					// 먼저, 총 게시물 건수를 구해야 한다.
					// 총 게시물 건수는 검색조건이 있을때와 없을때로 나뉘어진다.
					if(search != null &&
						!search.trim().equals("") && 
						!search.trim().equals("null")) {
						// 검색이 있는 경우(페이징 처리 한것)
						totalCount = service.getTotalCountWithSearch(paraMap);
					}
					else {
						totalCount = service.getTotalCountNoSearch();
						// 검색이 없는 경우(페이징 처리 한것)
					}
					
					totalPage = (int)Math.ceil((double)totalCount/sizePerPage);			
								// 23.7 ==> 24.0 ==> 24
					
					if(str_currentShowPageNo == null) {				
						// 게시판 초기화면일 경우
						currentShowPageNo = 1;
					}
					else {
						// 특정페이지를 보고자 조회한 경우
						try {
							currentShowPageNo = Integer.parseInt(str_currentShowPageNo);
							
							if(currentShowPageNo < 1 || currentShowPageNo > totalPage) {
								currentShowPageNo = 1;
							}
						}catch (NumberFormatException e) {
							currentShowPageNo = 1;
						}
					}
					
					// **** 가져올 게시글의 범위를 구한다.(공식임!) ****
					/*
					 currentShowPageNo  startRno  endRno
					 ===================================
					      1페이지                   1          10
					      2페이지                  11         20
					      3페이지                  21         30
					      ....           ..         ..			 
					*/
					startRno = ((currentShowPageNo -1 )*sizePerPage) +1; //currentShowPageNo 내가 보고자하는 페이지수 
					endRno   =   startRno + sizePerPage - 1; // sizePerPage 페이지개수
					
					paraMap.put("STARTRNO", String.valueOf(startRno));
					paraMap.put("ENDRNO", String.valueOf(endRno));
					
					boardList = service.boardListPaging(paraMap);
					
				//	System.out.println("boardList.size() : "+boardList.size());
				//	System.out.println(search);
					
					// ===== #120. 페이지바 만들기 =====
					String pagebar = "<ul class='pagination pull-center'  >";			
					pagebar += "<li>"+BSBUtil.getPageBarWithSearch(sizePerPage, blockSize, totalPage, currentShowPageNo, colname, search, null, "board.ana")+"</li>";			
					pagebar += "</ul>";
					
					// ===== #68. 글조회수(readCount)증가 (DML문 update)는
					/*            반드시 해당 글제목을 클릭했을 경우에만 글조회수가 증가되고 
					                         이전글보기, 다음글보기를 했을 경우나 웹브라우저에서 새로고침(F5)을 했을 경우에는 증가가 안되도록 한다.
					                         이것을 하기 위해서는 우리는 session 을 이용하여 처리한다. ===== */
								
					HttpSession session = req.getSession();
					session.setAttribute("readCountPermission", "yes");
					
					req.setAttribute("boardList", boardList);
					
					 req.setAttribute("colname", colname); // view단에서 검색어를 유지시키려고 보낸다.
					 req.setAttribute("search", search); //  view단에서 검색어를 유지시키려고 보낸다.
					 req.setAttribute("pagebar", pagebar);// view 단 으로 페이지바 넘기기
					
					 /*
					  특정 글 제목을 클릭하여 상세내용을 본 이후 페이징 처리된 해당 페이지로 그대로 돌아가기 위해 
					  돌아갈 페이지를 위해서 gobackURL을 뷰단으로 넘겨준다.
					  */
					 
					 String gobackURL = MyUtil.getCurrentURL(req);
					 
					 session.setAttribute("listgobackURL", gobackURL);

		return "board/board.tiles1";
	}
	
	// ===== #51. 글쓰기 폼페이지 요청. =====
			@RequestMapping(value="/add.ana", method={RequestMethod.GET})
			public String add(HttpServletRequest req, HttpServletResponse res ) { // requireLogin 로그인 해야만 가능하다
				
				HttpSession session = req.getSession();
				
				AdminVO loginAdmin = (AdminVO)session.getAttribute("loginAdmin");
				
				System.out.println(loginAdmin);
				// 로그인 되어진 사용자 정보를 가져온다.
				if(loginAdmin != null) {
					
					String msg = "관리자는 글쓰기 권한이 없습니다.";
					String loc = "javascript:history.back()";
					
					req.setAttribute("msg", msg);
					req.setAttribute("loc", loc);
					  
					  
					  return "msg";
					
					
				}
				
				
				
				// ===== #125. 답변글 쓰기가 추가된 경우 시작. =====
				String root  = req.getParameter("root");
				String groupno = req.getParameter("groupno");
				String depthno = req.getParameter("depthno");
				
				
				req.setAttribute("root", root);
				req.setAttribute("groupno", groupno);
				req.setAttribute("depthno", depthno);
				
				// =====  답변글 쓰기가 추가된 경우 끝. =====	
				
				
				return "board/add.tiles1";
			}
			
			// ===== #53. 글쓰기 완료 요청. =====
			@RequestMapping(value="/addEnd.ana", method={RequestMethod.POST})
			public String addEnd(BSBboardVO boardvo, MultipartHttpServletRequest req) {
				
				/*
				   웹페이지에 요청form이 enctype="multipart/form-data" 으로 되어있어서 Multipart 요청(파일처리 요청)이 들어올때 
				   컨트롤러에서는 HttpServletRequest 대신 MultipartHttpServletRequest 인터페이스를 사용해야 한다.
				  MultipartHttpServletRequest 인터페이스는 HttpServletRequest 인터페이스와 MultipartRequest 인터페이스를 상속받고있다.
				   즉, 웹 요청 정보를 얻기 위한 getParameter()와 같은 메소드와 Multipart(파일처리) 관련 메소드를 모두 사용가능하다.
				 
				 ===== 사용자가 쓴 글에 파일이 첨부되어 있는 것인지 아니면 파일첨부가 안된것인지 구분을 지어주어야 한다. =====
				 ========= !!첨부파일이 있는지 없는지 알아오기 시작!! ========= */
				
				MultipartFile attach = boardvo.getAttach();
				
				if(!attach.isEmpty()) {
					// attach 가 비어있지 않다면(즉, 첨부파일이 있는 경우라면)
					
					/*
					 1. 사용자가 보낸 파일으 WAS(톰캣)의 특정 폴더에 저장해주어야 한다.
					 >>>> 파일이 업로드 되어질 특정경로(폴더)지정해주기
					 우리는 WAS의 webapp/resources/files 라는 폴더로 지정해주겠다.				 
					 */
					 
					
					// WAS의 webapp 의 절대경로를 알아와야 한다.
					HttpSession session = req.getSession();
					String root = session.getServletContext().getRealPath("/");
					String path = root + "resources" + File.separator + "files" ;
					// path 가 첨부파일들을 저장할 WAS(톰캣)의 폴더가 된다.
					
					System.out.println(">>>> 확인용 path ===> "+path);
					//>>>> 확인용 path ===> C:\springworkspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Board\resources\files
					
					/*
					 2. 파일첨부를 위한 변수의 설정 및 값을 초기화한 후 파일올리기				 
					 */
					String newFileName = "";
					// WAS(톰캣) 디스크에 저장할 파일명
					
					byte[] bytes = null;
					//첨부파일을 WAS(톰캣) 디스크에 저장할때 사용되는 용도
					
					long fileSize = 0;
					
					// 파일크기를 읽어오기 위한 용도
					
					
					try {
						bytes = attach.getBytes();
						// getBytes() 첨부된 파일을 바이트 단위로 파일을 다 읽어오는 것이다.
						
						
						newFileName =  fileManager.doFileUpload(bytes,attach.getOriginalFilename(), path);
						// 첨부된 파일을 WAS(톰캣) 디스크로 파일 올리기를 하는 것이다.
						// 파일을 올린후 예를 들얼 12424534664756.png 와 같은 파일명을 얻어온다.
						
						System.out.println(">>>> 확인용 newFileName ==> " + newFileName);
						// >>>> 확인용 newFileName ==> 201901071126222751657863973746.jpg
						
						
						//3. BoardVO boardvo 에 fileName 값과 orgFilename 값과 fileSize 값을 넣어주기
						boardvo.setFileName(newFileName);
						boardvo.setOrgFilename(attach.getOriginalFilename());
						
						fileSize = attach.getSize();
						// 첨부한 파일의 크기인데 리턴타입은 long 타입이다.
						
						boardvo.setFileSize(String.valueOf(fileSize));					
						
					} catch (Exception e) {					
						e.printStackTrace();
					}
					
					  
				}// end of if			
				/*========= !!첨부파일이 있는지 없는지 알아오기 끝!! =========*/
				
							
			//	int n = service.add(boardvo);
				
				// ===== #140. 파일첨부가 없는 경우 또는 파일첨부가 있는 경우 Service 단으로 호출하기 =====
				//       먼저 위의 int n = service.add(boardvo); 을 주석처리한 후 아래처럼 한다.
				int n = 0;
				if(attach.isEmpty()) {
					// 파일첨부가 없다라면
					n = service.add(boardvo);
				}
				
				else {
					// 파일첨부가 있다라면
					n = service.add_withFile(boardvo);				
				}
				
				String loc = "";
				
				if(n==1) {
					loc = req.getContextPath()+"/board.ana";
				}
				else {
					loc = req.getContextPath()+"/add.ana";
				}
				
				req.setAttribute("n", n);
				req.setAttribute("loc", loc);
				
		
				return "board/addEnd.tiles1";
			}
			
			 // ==== #149. 첨부파일 다운로드 받기 ====						
			  @RequestMapping(value="/download.ana", method={RequestMethod.GET})
			  public void download(HttpServletRequest req, HttpServletResponse res) {
				  
				  String idx = req.getParameter("idx");
				  // 첨부파일이 있는 글번호
				  
				  
				  // 첨부파일이 있는 글번호에서
				  // 201901071248442756600225890594.jpg 처럼
				  // 이러한 fileName 값을 DB에서 가져와야 한다.
				  // 또한 쉐보레전면.jpg 처럼 orgFileName 값도 DB에서 가져와야 한다.
				  
				  BSBboardVO boardvo = service.getViewWithNoAddCount(idx);
				  // 조회수 증가 없이 1개글 가져오기
				  
				  String fileName = boardvo.getFileName();
				  // 201901071248442756600225890594.jpg 와 같은 것을 가져온다.
				  // 이것이 바로 WAS(톰캣) 디스크에 저장된 파일명이다.
				  
				  String orgFilename = boardvo.getOrgFilename();
				  // 쉐보레전면.jpg 와 같은것을 가져온다.
				  // 사용자가 파일을 다운받을시 쉐보레전면.jpg 처럼 다운받도록 하는 것이다.
				  
		   		  // 첨부파일이 저장되어 있는  WAS의 절대경로를 알아와야 한다.
				  // 그래야만 첨부파일이 저장되어 있는 곳에서 파일을 다운받을 수 있다.
				  // 이 경로는 파일을 첨부했을때와 동일한 경로이다.
				  HttpSession session = req.getSession();
				  String root = session.getServletContext().getRealPath("/");
				  String path = root + "resources" + File.separator + "files" ;
				  // path 가 첨부파일들이 저장된 WAS(톰캣)의 폴더가 된다.
				  
				  // === *** 다운로드 하기 *** === //
				  // 다운로드가 실패할 경우 메시지를 띄워주기 위해서 
				  // boolean 타입 변수 flag 를 선언한다.
				  boolean flag = false;
				  
				  flag =  fileManager.doFileDownload(fileName, orgFilename, path, res);
				  // 다운로드가 성공이면 true 를 반환해주고,
				  // 다운로드가 실패이면 false 를 반환해준다.
				  
				  if(!flag) {
						  // 다운로드가 실패할 경우 메시지를 띄워주겠다.
						  res.setContentType("text/html; charset=UTF-8");
						  
						  try {
							PrintWriter out = res.getWriter();
							//PrintWriter out 이 웹브라우저 상에 내용물을 기재(쓰는)해주는 객체이다
							
							out.println("<script type='text/javascript'>alert('파일 다운로드가 실패했습니다.');"
									+ 	"location.href=history.back();</script>");
							
							
						} catch (IOException e) {				
							e.printStackTrace();
						}
						  
				  }
				  
				  
			  }
			
			
			// ===== #61. 글1개를 보여주는 페이지 요청 ====
			@RequestMapping(value="/BSBview.ana", method={RequestMethod.GET})
			public String view(HttpServletRequest req) {
				
				String idx = req.getParameter("idx");				
				// 글번호 받아오기
				
				String gobackURL = req.getParameter("gobackURL");
				// 특정 글 제목을 클릭하여 상세내용을 본 이후 페이징 처리된 해당 페이지로 그대로 돌아가기 위해 
				// 돌아갈 페이지를 위해서 gobackURL을 뷰단으로 넘겨준다.
				
			//	System.out.println("gobackURL :"+gobackURL);
				req.setAttribute("gobackURL", gobackURL);
				
				BSBboardVO boardvo = null; // 글1개를 저장할 객체
				
				HttpSession session = req.getSession();
				
				LibrarianVO loginuser = (LibrarianVO)session.getAttribute("loginLibrarian");
				// 로그인 되어진 사용자 정보를 가져온다.
				
				System.out.println(loginuser);
				
				// ===== #67. 글조회수(readCount)증가 (DML문 update)는
				/*            반드시 해당 글제목을 클릭했을 경우에만 글조회수가 증가되고 
				                         이전글보기, 다음글보기를 했을 경우나 웹브라우저에서 새로고침(F5)을 했을 경우에는 증가가 안되도록 한다.===== */
				
				String readCountPermission = (String)session.getAttribute("readCountPermission");
							
				
				String memberid = null;
				
				if(readCountPermission != null && 
						"yes".equals(readCountPermission)) {
						// 글 1개를 보기위해 
						// http://localhost:9090/board/list.action(목록보기)을 거친후
						// 들어온 경우
					if(loginuser != null) {
						// 로그인 한 경우에만 userid 변수에 로그인한 사용자 ID값을 넣어준다.
							memberid = loginuser.getLibid();
							
						}
					
					System.out.println(readCountPermission);
					System.out.println(memberid);
					System.out.println(loginuser);
						
						boardvo = service.getView(idx, memberid);
						
						/*!! 즁요함 !!
						  글 1개를 보기 위해 글목록을 거쳐온 경우이라면
						  확인후 세션에서 제거한다.*/
						
						session.removeAttribute("readCountPermission");
					
				}
				else {
					// 특정글 1개를 본 이후 새로고침(F5)을 한 경우나 
					// 또는 특정글 1개를 본 이후 이전글보기, 다음글보기를 한 경우이다.
					// 이럴경우 우리는 굴조회수 증가없는 그냥 1개글만 보여주도록 한다.
					
					boardvo = service.getViewWithNoAddCount(idx);
					// 조회수(readCount)증가 없이 그냥 글 1개만 가져오는 것
					
				}
				
				
				req.setAttribute("boardvo", boardvo);
				
				
				return "board/BSBview.tiles1";
			}
			
			

			// ===== #85. 댓글쓰기 =====
			@RequestMapping(value="/addComment.ana", method={RequestMethod.POST})
			@ResponseBody
			public HashMap<String, String> addComment(BSBcommentVO commentvo) throws Throwable{
				
				HashMap<String, String> returnMap = new HashMap<String, String>();
				
				// 댓글쓰기(*** AJAX로 처리함 ***)
				int n = service.addComment(commentvo);
				
				if(n==1) {
					// 댓글쓰기 및 원게시물(tblBoard 테이블)에 댓글의 갯수(1씩증가)증가가 성공했다라면
					returnMap.put("NAME", commentvo.getName());
					returnMap.put("CONTENT", commentvo.getContent());
					returnMap.put("REGDATE", MyUtil.getNowTime());
					
				}
				
				
				return returnMap;
				
			}
			
			// ===== #92-1. 댓글내용 가져오기(페이징처리를 하므로 특정페이지(1페이지, 2페이지, 3페이지...)에 대한 댓글의 내용을 가져오는것 =====		
			@RequestMapping(value="/commentList.ana", method={RequestMethod.GET})
			@ResponseBody
			public List<HashMap<String, Object>> commentList(HttpServletRequest req){
				
					 List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
					 
					 String idx = req.getParameter("idx");
					 // 원글의 글번호를 받아와서 원글에 딸린 댓글을 보여주려고 한다.
					 
					 String currentShowPageNo = req.getParameter("currentShowPageNo");
					 
					 if(currentShowPageNo == null || "".equals(currentShowPageNo)) {
						 currentShowPageNo = "1";
					 }
					 
					 int sizePerPage = 5; // 한페이지당 5개의 댓글을 보여줄 것임.
					 
					 int rno1 = Integer.parseInt(currentShowPageNo) * sizePerPage - (sizePerPage-1);   // 공식 !! 
					 int rno2 =	Integer.parseInt(currentShowPageNo) * sizePerPage;	 // 공식 !!
					 
					 /*
						  페이지번호     rno1     rno2
						  =======================
						  1페이지         1        5
						  2페이지         6       10
						  3페이지         11      15
						  .......   ..      .. 
					  */
					 
					 HashMap<String, String> paraMap = new HashMap<String, String>();
					 paraMap.put("IDX", idx);
					 paraMap.put("RNO1", String.valueOf(rno1));
					 paraMap.put("RNO2", String.valueOf(rno2));
					 
					 List<BSBcommentVO> commentList = service.listComment(paraMap);
					 // 원글에 글번호에 대한 댓글중 페이지 번호에 해당하는 댓글만 조회헤온다.
					 
					 for(BSBcommentVO cmtvo : commentList) {
						 HashMap<String, Object> map = new HashMap<String, Object>();						 
						 map.put("IDX", cmtvo.getidx());
						 map.put("NAME", cmtvo.getName());
						 map.put("CONTENT", cmtvo.getContent());
						 map.put("REGDATE", cmtvo.getRegDate());
						 
						 mapList.add(map);
					 }
						
					 return mapList;
				
			}
			
			
			// ===== #92-2. 댓글 TotalPage 가져오기 =====		
						@RequestMapping(value="/getCommentTotalPage.ana", method={RequestMethod.GET})
						@ResponseBody
						public HashMap<String, Integer> getCommentTotalPage(HttpServletRequest req){
							
							HashMap<String, Integer> returnMap = new HashMap<String, Integer>();
								 
								 String idx = req.getParameter("idx");
								 // 원글의 글번호를 받아와서 원글에 딸린 댓글의 갯수를 알아오려고 한다.
								 
								 String sizePerPage = req.getParameter("sizePerPage");
								 // 한페이지당 보여줄 댓글의 갯수
								
								 HashMap<String, String> paraMap = new HashMap<String, String>();
								 paraMap.put("IDX", idx);
								 paraMap.put("SIZEPERPAGE", sizePerPage);
								 
								// -- 원글 글번호에 해당하는 댓글의 총갯수를 알아오기
								 int totalCount = service.getCommentTotalCount(paraMap);
								 
								 
								 // -- 총 페이지수 (totalPage) 구하기 
								 int totalPage = (int)Math.ceil((double)totalCount/Integer.parseInt(sizePerPage));
								 /*
								   
								     57.0(행갯수)/10(sizePerPage) == 5.7 ==> 6.0 ==> 6
								     57.0(행갯수)/5(sizePerPage) == 11.4 ==> 12.0 ==> 12
								     57.0(행갯수)/3(sizePerPage) == 19.0 ==> 19.0 ==> 19
								   
								  */
								 
								 returnMap.put("TOTALPAGE",totalPage);
								 returnMap.put("TOTALCOUNT",totalCount);

								 
																	
								 return returnMap;
							
						}
						
						// ===== #77. 글삭제 페이지 요청 =====
						@RequestMapping(value="/del.ana", method={RequestMethod.GET})
						public String del(HttpServletRequest req, HttpServletResponse res) {
							
							// 삭제해야할 글 번호 가져오기
							String idx = req.getParameter("idx");
							
							// 삭제해야할 글전체 내용가져오기
							BSBboardVO boardvo = service.getViewWithNoAddCount(idx); 
							// 조회수(readCount) 증가 없이 그냥 글1개 가져오는것
							
							HttpSession session = req.getSession();
							BSBMemberVO loginuser = (BSBMemberVO)session.getAttribute("loginuser");
							
							if(!loginuser.getmemberid().equals(boardvo.getLibid_fk())) {
								String msg = "다른 사용자의 글은 삭제가 불가합니다.";
								String loc = "javascript:history.back()";
								
								req.setAttribute("msg", msg);
								req.setAttribute("loc", loc);
								
								return "msg";
							}
							else {
								// 삭제해야할 글번호를 request 영역에 저장시켜서 view단 페이지로 넘긴다.
								req.setAttribute("idx", idx);
								
								// 글삭제시 글암호를 입력받아 글작성할때 입력한 암호와 비교할 수 있도록
								// view 단에서 만들어 주어야 한다.
								
								return "board/board.tiles1";
								
								
							}
							
							
						}
						
						
						// ===== #78. 글삭제 페이지 완료하기 =====
						@RequestMapping(value="/delEnd.ana", method={RequestMethod.POST})
						public String delEnd(HttpServletRequest req)  throws Throwable{
							
							
						
							
							// 글 삭제를 하려면 삭제할 글의 글번호와
							// 사용자가 입력한 글암호를 알아와서 
							// 삭제할 글의 암호와 사용자가 입력한 글암호와 일치할 경우에만 삭제하도록 한다.
							String idx = req.getParameter("idx");
							String pw = req.getParameter("pw");
							
							// 삭제해야할 글전체 내용가져오기
							BSBboardVO boardvo = service.getViewWithNoAddCount(idx); 
							// 조회수(readCount) 증가 없이 그냥 글1개 가져오는것
							
							HttpSession session = req.getSession();
							LibrarianVO loginuser = (LibrarianVO)session.getAttribute("loginLibrarian");
							
							if(!loginuser.getLibid().equals(boardvo.getLibid_fk())) {
								String msg = "다른 사용자의 글은 삭제가 불가합니다.";
								String loc = "javascript:history.back()";
								
								req.setAttribute("msg", msg);
								req.setAttribute("loc", loc);
								
								return "msg";
							}
							else {
							
							
							
							HashMap<String, String> paraMap = new HashMap<String, String>();
							paraMap.put("IDX", idx);
							paraMap.put("PW", pw);
							
							int result = 0;
							
								result = service.del(paraMap);
							
							/*
							 넘겨받은 값이 1이면 글삭제 성공,
							 넘겨받은 값이 0이면 글삭제 실패(암호가틀리므로)
							 */
							

							String msg = "";
							String loc = "";
							
							if(result == 0) {
								msg = "글 삭제 실패";
								loc = "javascript:history.back();";
								
							}
							else {
								msg = "글 삭제 성공";
								loc = req.getContextPath()+"/board.ana";
								
							}
							
							req.setAttribute("msg", msg);
							req.setAttribute("loc", loc);
							 req.setAttribute("idx", idx);
									
							}
							return "msg";
						}
						
						
						
						// ===== #78. 댓글삭제 페이지 완료하기 =====
						@RequestMapping(value="/commentdelEnd.ana", method={RequestMethod.POST})
						@ResponseBody
						public HashMap<String, Integer> commentdelEnd(HttpServletRequest req)  throws Throwable{
							
							
						
							
							
							// 글 삭제를 하려면 삭제할 글의 글번호와
							// 사용자가 입력한 글암호를 알아와서 
							// 삭제할 글의 암호와 사용자가 입력한 글암호와 일치할 경우에만 삭제하도록 한다.
							String orgIdx = req.getParameter("orgIdx");
							String idx = req.getParameter("idx");
							
							
							
							HashMap<String, String> paraMap = new HashMap<String, String>();
							paraMap.put("ORGIDX", orgIdx);
							paraMap.put("IDX", idx);
							
							
								int result = 0;
								
								result = service.commentdel(paraMap);
							
							/*
							 넘겨받은 값이 1이면 글삭제 성공,
							 넘겨받은 값이 0이면 글삭제 실패(암호가틀리므로)
							 */
							
							

							String msg = "";
							String loc = "";
							
							HashMap<String, Integer> json = new HashMap<String, Integer>();
							
							json.put("RESULT", result);
								
						
							return json;					
						}
						
						
						  // =====#스마트에디터1. 단일사진 파일업로드 =====
						  @RequestMapping(value="/image/photoUpload.ana", method={RequestMethod.POST}) // 파일첨부는 무조건 POST
						  public String photoUpload(PhotoVO photovo, HttpServletRequest req) {
							  
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
									HttpSession session = req.getSession();
									String root = session.getServletContext().getRealPath("/"); 
									String path = root + "resources"+File.separator+"photo_upload";
									// path 가 첨부파일들을 저장할 WAS(톰캣)의 폴더가 된다. 
									
								//	System.out.println(">>>> 확인용 path ==> " + path); 
									// >>>> 확인용 path ==> C:\SpringWorkspaceTeach\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Board\resources\photo_upload
									
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
										
										String CP = req.getContextPath();  // board
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
							@RequestMapping(value="/image/multiplePhotoUpload.ana", method={RequestMethod.POST})
							public void multiplePhotoUpload(HttpServletRequest req, HttpServletResponse res) {
							    
								/*
								   1. 사용자가 보낸 파일을 WAS(톰캣)의 특정 폴더에 저장해주어야 한다.
								   >>>> 파일이 업로드 되어질 특정 경로(폴더)지정해주기
								        우리는 WAS 의 webapp/resources/photo_upload 라는 폴더로 지정해준다.
								 */
								
								// WAS 의 webapp 의 절대경로를 알아와야 한다. 
								HttpSession session = req.getSession();
								String root = session.getServletContext().getRealPath("/"); 
								String path = root + "resources"+File.separator+"photo_upload";
								// path 가 첨부파일들을 저장할 WAS(톰캣)의 폴더가 된다. 
								
							//	System.out.println(">>>> 확인용 path ==> " + path); 
								// >>>> 확인용 path ==> C:\SpringWorkspaceTeach\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Board\resources\photo_upload   
								
								File dir = new File(path);
								if(!dir.exists())
									dir.mkdirs();
								
								String strURL = "";
								
								try {
									if(!"OPTIONS".equals(req.getMethod().toUpperCase())) {
							    		String filename = req.getHeader("file-name"); //파일명을 받는다 - 일반 원본파일명
							    		
							    //		System.out.println(">>>> 확인용 filename ==> " + filename); 
							    		// >>>> 확인용 filename ==> berkelekle%ED%8A%B8%EB%9E%9C%EB%94%9405.jpg
							    		
							    		InputStream is = req.getInputStream();
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
							    	
										String CP = req.getContextPath(); // board
									
										strURL += "&bNewLine=true&sFileName="; 
						            	strURL += newFilename;
						            	strURL += "&sWidth="+width;
						            	strURL += "&sFileURL="+CP+"/resources/photo_upload/"+newFilename;
							    	}
								
							    	/// 웹브라우저상에 사진 이미지를 쓰기 ///
									PrintWriter out = res.getWriter();
									out.print(strURL);
								} catch(Exception e){
									e.printStackTrace();
								}
								
							}// end of void multiplePhotoUpload(HttpServletRequest req, HttpServletResponse res)----------------
							
							
							// ===== #70. 글수정 페이지 요청 =====
							@RequestMapping(value="/edit.ana", method={RequestMethod.GET})
							public String edit(HttpServletRequest req, HttpServletResponse res) {
								
								
								// 글 수정해야할 글번호 가져오기
								String idx = req.getParameter("idx");
								
								// 글 수정해야할 글전체 내용 가져오기
								BSBboardVO boardvo = service.getViewWithNoAddCount(idx); 
								// 조회수(readCount) 증가 없이 그냥 글1개 가져오는것
								
								HttpSession session = req.getSession();
								LibrarianVO loginuser = (LibrarianVO)session.getAttribute("loginLibrarian");
								
								System.out.println("글수정아이디"+loginuser);
								
								if(!loginuser.getLibid().equals(boardvo.getLibid_fk())) {
									String msg = "다른 사용자의 글은 수정이 불가합니다.";
									String loc = "javascript:history.back()";
									
									req.setAttribute("msg", msg);
									req.setAttribute("loc", loc);
									
									return "msg";
								}
								else {
									// 가져온 1개글을 request 영역에 저장시켜서 view 단 페이지로 넘긴다.
									req.setAttribute("boardvo", boardvo);				
									
									return "board/edit.tiles1";
									
									
								}
								
								
								
							}
							
							// ===== #71. 글수정 페이지 완료하기 =====
							@RequestMapping(value="/editEnd.ana", method={RequestMethod.POST})
							public String editEnd(BSBboardVO boardvo, HttpServletRequest req) {
								
								HashMap<String, String> paraMap = new HashMap<String, String>();
								
								paraMap.put("IDX", boardvo.getIdx());
								paraMap.put("PW", boardvo.getPw());
								paraMap.put("SUBJECT", boardvo.getSubject());
								paraMap.put("CONTENT", boardvo.getContent());
								
								int result = service.edit(paraMap);
								// 넘겨받은 값이 1이면 update 성공
								// 넘겨받은 값이 0이면 update 실패(암호가 틀리므로)
								System.out.println(result);
								
								String msg = "";
								String loc = "";
								
								if(result == 0) {
									msg = "글 수정 실패";
									loc = "javascript:history.back();";
									
								}
								else {
									msg = "글 수정 성공";
									loc = req.getContextPath()+"/BSBview.ana?idx="+boardvo.getIdx();
									
								}
								
								req.setAttribute("msg", msg);
								req.setAttribute("loc", loc);
								
										
								
								return "msg";
								
							}
							
							
	
	
}
