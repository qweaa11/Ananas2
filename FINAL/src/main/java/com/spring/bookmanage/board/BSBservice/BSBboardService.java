package com.spring.bookmanage.board.BSBservice;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bookmanage.board.BSBmodel.BSBInterBoardDAO;
import com.spring.bookmanage.board.BSBmodel.BSBboardVO;
import com.spring.bookmanage.board.BSBmodel.BSBcommentVO;
import com.spring.bookmanage.common.AES256;



//===== #31. Service 선언  =====
@Service
public class BSBboardService implements BSBInterBoardService {
	
	//===== #34. 의존객체 주입하기(DI: Dependency Injection) =====
	@Autowired
	private BSBInterBoardDAO dao;
	
	//===== #45_2. 양방향 암호화 알고리즘인 AES256를 사용하여 암호화/복호화 하기 위한  클래스 의존객체 주입하기 (DI: Dependency Injection)=====
		@Autowired
		private AES256 aes;

		@Override
		public int getTotalCountWithSearch(HashMap<String, String> paraMap) {
			int count = dao. getTotalCountWithSearch(paraMap);
			return count;
		}

		@Override
		public int getTotalCountNoSearch() {
			int count = dao.getTotalCountNoSearch();
			return count;
		}

		@Override
		public List<BSBboardVO> boardListPaging(HashMap<String, String> paraMap) {
			List<BSBboardVO> boardList = dao.boardListPaging(paraMap);
			return boardList;
		}

		@Override
		public int add(BSBboardVO boardvo) {
		
		/* === #127. 글쓰기가 원글쓰기인지 답변글쓰기인지를 구분하여
        board 테이블에 insert 를 해주어야 한다.
                  원글쓰기 이라면 board 테이블 groupno 컬럼의 값은 
        groupno 컬럼의 최대값(max)+1 로 해서 insert 해주어야 한다.
        
                  답변글쓰기 이라면 넘겨받은 값을 그대로 insert 해주어야 한다. 
		*/
		// ==== 원글쓰기인지, 답변글쓰기인지 구분하기 ====
		if(boardvo.getRoot() == null || 
				boardvo.getRoot().trim().isEmpty() ) {
			// 원글쓰기인 경우
			int groupno = dao.getGroupnoMax()+1;
			boardvo.setGroupno(String.valueOf(groupno));			
		}
		
		int n = dao.add(boardvo);
		
		return n;
		}

		@Override
		public BSBboardVO getView(String idx, String memberid) {
			BSBboardVO boardvo = dao.getView(idx);
			if(memberid != null && 
					!boardvo.getLibid_fk().equals(memberid)) {
					// 조회수 증가는 로그인 되어져 있는 상태에서
					// 다른사람이 작성한 글을 읽었을때만 조회수 증가하도록 한다.
					dao.setAddReadCount(idx);
					 boardvo = dao.getView(idx);
				}
			return boardvo;
		}

		@Override
		@Transactional(propagation=Propagation.REQUIRED, isolation= Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
		public int addComment(BSBcommentVO commentvo) throws Throwable {
			int result = 0;
			
			int n = 0;
			n =dao.addComment(commentvo);
			
			if(n==1) {
				result  = dao.updateCommentCount(commentvo.getParentidx());
			}
					
			return result;
		}

		@Override
		public List<BSBcommentVO> listComment(HashMap<String, String> paraMap) {
			List<BSBcommentVO> commentList = dao.listComment(paraMap);
			return commentList;
		}

		@Override
		public int getCommentTotalCount(HashMap<String, String> paraMap) {
			int totalCount = dao.getCommentTotalCount(paraMap);
			return totalCount;
		}

		@Override		
		@Transactional(propagation=Propagation.REQUIRED, isolation= Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
		public int del(HashMap<String, String> paraMap)
		    throws Throwable{
			
			boolean checkPW = dao.checkPW(paraMap);
			// 글번호에 대한 암호가 일치하면 true, 일치하지 않으면 false 반환함.
			
			int count = 0; // 원글에 딸린 댓글의 갯수 유무확인용		
			int result1 = 0;
			int result2 = 0;
			int n = 0;
			
			if(checkPW == true) {
				count = dao.isExistsComment(paraMap); // ====#99. 원글에 딸린 댓글이 있는지 없는지 확인하기
				result1 = dao.deleteContent(paraMap); // 원글 1개 삭제하기
				
				if(count > 0) {
					result2 = dao.delComment(paraMap);// ====#100. 원글에 딸린 댓글 삭제하기 ====
				}
			}
			
			if((result1 > 0 && (count > 0 && result2 > 0))||
			   ( result1 > 0 && count == 0) ) {
				n = 1;
			}
			
			System.out.println(count + "" + result1 + result2 + n);
			return n;
		}

		// ==== #69. 조회수(readCount)증가 없이 그냥 글 1개만 가져오는 것
		@Override
		public BSBboardVO getViewWithNoAddCount(String idx) {
			BSBboardVO boardvo = dao.getView(idx);
			return boardvo;
		}

		@Override
		public int add_withFile(BSBboardVO boardvo) {
			/*  글쓰기가 원글쓰기인지 답변글쓰기인지를 구분하여
        	tblBoard 테이블에 insert 를 해주어야 한다.
                     원글쓰기 이라면 tblBoard 테이블 groupno 컬럼의 값은 
        	groupno 컬럼의 최대값(max)+1 로 해서 insert 해주어야 한다.        
                     답변글쓰기 이라면 넘겨받은 값을 그대로 insert 해주어야 한다. 
		*/
		
		// ==== 원글쓰기인지, 답변글쓰기인지 구분하기 ====
		if(boardvo.getRoot() == null || 
			boardvo.getRoot().trim().isEmpty() ) {
		// 원글쓰기인 경우
			int groupno = dao.getGroupnoMax()+1;
			boardvo.setGroupno(String.valueOf(groupno));			
		}
		
		int n = dao.add_withFile(boardvo); // 첨부파일이 있는 경우
		
		return n;
		

	}

		@Override
		@Transactional(propagation=Propagation.REQUIRED, isolation= Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
		public int commentdel(HashMap<String, String> paraMap) throws Throwable {
			
			int result = 0;
			
			int n = 0;
			
			n =dao.commentdel(paraMap);
			
			
			if(n==1) {
				result  = dao.updateCommentdelCount(paraMap.get("ORGIDX"));
			}
					
			return result;
			
			
		
		}

		@Override
		public int edit(HashMap<String, String> paraMap) {
			boolean checkpw = dao.checkPW(paraMap);
			
			// 글번호의 대한 암호가 사용자가 입력한 암호와 일치하면 true 반환
			// 글번호의 대한 암호가 사용자가 입력한 암호와 일치하지않으면 false 반환
			
			int result = 0;
			
			if(checkpw == true) {
				result = dao.updateContent(paraMap);// 글 1개 수정하기
			}
			
			return result;
		
		}
	

	
	
}
