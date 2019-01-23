package com.spring.bookmanage.board.YJKservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bookmanage.board.YJKmodel.InterYJKBoardDAO;
import com.spring.bookmanage.board.YJKmodel.YJKBoardVO;
import com.spring.bookmanage.board.YJKmodel.YJKReplyVO;

@Service
public class YJKBoardService implements InterYJKBoardService {
	
	//===== 의존객체 주입하기(DI : Dependency Injection)  =====
	@Autowired
	private InterYJKBoardDAO dao;

	// ==== 전체 글 목록 보여주기
	@Override
	public List<YJKBoardVO> getboardList(HashMap<String, String> paraMap) {
		
		List<YJKBoardVO> boardList = dao.getboardList(paraMap);
		
		return boardList;
	}

	// ==== 글 1개를 보여주는 페이지 요청(먼저 글 조회수 증가를 할지 안 할지 결정해야함) ====
	@Override
	public YJKBoardVO getView(String idx, String libid) {
		
		YJKBoardVO boardvo = dao.getView(idx);
		
		if(libid != null && !boardvo.getLibid_fk().equals(libid)) {
			// 조회수 증가는 로그인 되어져 있는 상태에서
			// 다른사람이 작성한 글을 읽었을 때만 조회수 증가하도록 한다.
			dao.setAddReadCount(idx);
			boardvo = dao.getView(idx);
			
		}
		
		return boardvo;
	}

	// ==== 조회수(readCount)증가 없이 그냥 글 1개만 가져오는 것 ====
	@Override
	public YJKBoardVO getViewWithNoAddCount(String idx) {
		
		YJKBoardVO boardvo = dao.getView(idx);
		
		return boardvo;
	}

	// ==== 파일 첨부가 없는 글쓰기 요청 ==== //
	@Override
	public int boardAdd(YJKBoardVO boardvo) {
		
		// ==== 원글쓰기인지, 답변글쓰기인지 구분하기 ====
		if(boardvo.getRoot() == null ||
		   boardvo.getRoot().trim().isEmpty()) {
			// 원글쓰기인 경우
			int groupno = dao.getGroupnoMax()+1;
			boardvo.setGroupno(String.valueOf(groupno));
		}
		
		int n = dao.boardAdd(boardvo);
		
		return n;
	}

	// ==== 파일 첨부가 있는 글쓰기 요청 ==== //
	@Override
	public int boardAdd_withFile(YJKBoardVO boardvo) {
	
		// ==== 원글쓰기인지, 답변글쓰기인지 구분하기 ====
		if(boardvo.getRoot() == null ||
		   boardvo.getRoot().trim().isEmpty()) {
			// 원글쓰기인 경우
			int groupno = dao.getGroupnoMax()+1;
			boardvo.setGroupno(String.valueOf(groupno));
		}
		
		int n = dao.boardAdd_withFile(boardvo);
		
		return n;
	}

	// ==== 검색조건에 만족하는 총 게시물 건수 알아오기 ====
	@Override
	public int getTotalCountWithSearch(HashMap<String, String> paraMap) {
		
		int count = dao.getTotalCountWithSearch(paraMap);
		
		return count;
	}

	// ==== 검색조건이 없는 총 게시물 건수 알아오기 ====
	@Override
	public int getTotalCountWithNoSearch() {
		
		int count = dao.getTotalCountWithNoSearch();
		
		return count;
	}

	// ==== 댓글쓰기 ====
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation= Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	public int boardAddComment(YJKReplyVO replyvo) {
		
		int result = 0;
		
		int n = 0;
		
		n = dao.boardAddComment(replyvo);

		if(n == 1) {
			System.out.println(n);
			System.out.println("오니?");
			System.out.println(replyvo.getParentidx());
			result = dao.updateCommentCount(replyvo.getParentidx());
		}
		
		return result;
	}

	// ==== 원글에 글번호에 대한 댓글 중 페이지 번호에 해당하는 댓글만 조회해온다.
	@Override
	public List<YJKReplyVO> listComment(HashMap<String, String> paraMap) {
		
		List<YJKReplyVO> commentList = dao.listComment(paraMap);
		
		return commentList;
	}

	// ==== 댓글 TotalPage 가져오기 ====
	@Override
	public int getCommentTotalCount(HashMap<String, String> paraMap) {
		
		int totalCount = dao.getCommentTotalCount(paraMap);
		
		return totalCount;
	}

	// 글 수정하기
	@Override
	public int boardEdit(HashMap<String, String> paraMap) {
		
		boolean checkpw = dao.checkPW(paraMap);
		// 글 번호의 대한 암호가 사용자가 입력한 암호와 일치하면 true 반환.
		// 글 번호의 대한 암호가 사용자가 입력한 암호와 일치하지 않으면 false 반환.
		
		int result = 0;
		
		if(checkpw == true) {
			result = dao.updateContent(paraMap);
		}
		
		return result;
	}

	// 글 삭제하기
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation= Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	public int boardDel(HashMap<String, String> paraMap) throws Throwable {
		
		boolean checkpw = dao.checkPW(paraMap);
		
		int count = 0;
		int result1 = 0;
		int result2 = 0;
		int n = 0;
		
		if(checkpw == true) {
			count = dao.isExistsComment(paraMap); // 원글에 댓글이 있는지 없는지 확인
			result1 = dao.deleteContent(paraMap); // 원글 1개 삭제
			
			if(count > 0) {
				result2 = dao.delComment(paraMap); // 댓글삭제
			}
		}
		
		if((result1 > 0 && count > 0 && result2 > 0) || 
		  ( result1 > 0 && count == 0)) {
			n = 1;
		}
		
		return n;
	}


}
