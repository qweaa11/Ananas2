package com.spring.bookmanage.board.BSBcontroller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.bookmanage.board.BSBmodel.BSBboardVO;
import com.spring.bookmanage.board.BSBservice.BSBInterBoardService;
import com.spring.bookmanage.common.AES256;
import com.spring.bookmanage.common.MyUtil;
import com.spring.bookmanage.common.SHA256;
import com.spring.bookmanage.login.BSBmodel.BSBMemberVO;
import com.spring.bookmanage.login.BSBservice.BSBInterLoginService;

@Controller
public class BSBboardController {
	
	// =====#35.의존객체 주입하기(DI : Dependency Injection) =====
			@Autowired
			private BSBInterBoardService service;
			
			
	
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
					String pagebar = "<ul class=\'pagination pull-right\' style=\'margin-right: 60%;\'>"
					               + "<li class=''><a href='/bookmanage/board.ana?currentShowPageNo="+(startRno)+"'><span class='glyphicon glyphicon-chevron-left'></span></a></li>";	         
					if((currentShowPageNo-1) != 0)
						
						pagebar += "<li class=''><a href='/bookmanage/board.ana?currentShowPageNo="+(currentShowPageNo-1)+"'>"+(currentShowPageNo-1)+"</a></li>";
					
						pagebar += "<li class='active'><a href='/bookmanage/board.ana?currentShowPageNo="+currentShowPageNo+"'>"+currentShowPageNo+"</a></li>";
					
					if((currentShowPageNo+1) <= totalPage)
						pagebar +="<li class=''><a href='/bookmanage/board.ana?currentShowPageNo="+(currentShowPageNo+1)+"'>"+(currentShowPageNo+1)+"</a></li>";
					
													          
						          pagebar+= "<li><a href='/bookmanage/board.ana?currentShowPageNo="+(endRno)+"'><span class='glyphicon glyphicon-chevron-right'></span></a></li>";			
					/*pagebar += MyUtil.getPageBarWithSearch(sizePerPage, blockSize, totalPage, currentShowPageNo, colname, search, null, "board.ana");*/			
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
					 
					 req.setAttribute("gobackURL", gobackURL);
		
		
		
		

		return "board/board.tiles1";
	}
	
	// ===== #51. 글쓰기 폼페이지 요청. =====
			@RequestMapping(value="/add.ana", method={RequestMethod.GET})
			public String add(HttpServletRequest req, HttpServletResponse res ) { // requireLogin 로그인 해야만 가능하다
				
				
				
				// ===== #125. 답변글 쓰기가 추가된 경우 시작. =====
				String fk_seq  = req.getParameter("fk_seq");
				String groupno = req.getParameter("groupno");
				String depthno = req.getParameter("depthno");
				
				
				req.setAttribute("fk_seq", fk_seq);
				req.setAttribute("groupno", groupno);
				req.setAttribute("depthno", depthno);
				
				// =====  답변글 쓰기가 추가된 경우 끝. =====	
				
				
				return "board/add.tiles1";
			}
			
			// ===== #53. 글쓰기 완료 요청. =====
			@RequestMapping(value="/addEnd.ana", method={RequestMethod.POST})
			public String addEnd(BSBboardVO boardvo, HttpServletRequest req) {
				
				System.out.println(boardvo.getLibid_fk());
			
				int n = service.add(boardvo);
				
				
									
				String loc = "";
				
				if(n==1) {
					loc = req.getContextPath()+"/board.ana";
				}
				else {
					loc = req.getContextPath()+"/index.ana";
				}
				
				
				req.setAttribute("n", n);
				req.setAttribute("loc", loc);
				
				
				
				return "board/addEnd.tiles1";
			}
			
			
			// ===== #61. 글1개를 보여주는 페이지 요청 ====
			@RequestMapping(value="/BSBview.ana", method={RequestMethod.GET})
			public String view(HttpServletRequest req) {
				
				String idx = req.getParameter("idx");
				
				BSBboardVO  boardvo = null;
				
				boardvo = service.getView(idx);
				
			req.setAttribute("boardvo", boardvo);
				
				return "board/BSBview.tiles1";
			}
	
	
}
