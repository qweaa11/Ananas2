package com.spring.bookmanage.board.YJKservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.board.YJKmodel.InterYJKBoardDAO;
import com.spring.bookmanage.board.YJKmodel.YJKBoardVO;

@Service
public class YJKBoardService implements InterYJKBoardService {
	
	//===== 의존객체 주입하기(DI : Dependency Injection)  =====
	@Autowired
	private InterYJKBoardDAO dao;

	@Override
	public List<YJKBoardVO> getboardList() {
		
		List<YJKBoardVO> boardList = dao.getboardList();
		
		return boardList;
	}

	// ==== 글 1개를 보여주는 페이지 요청(먼저 글 조회수 증가를 할지 안 할지 결정해야함) ====
	@Override
	public YJKBoardVO getView(String idx, String libid) {
		
		YJKBoardVO boardvo = dao.getView(idx);
		
		if(libid != null &&
		   !boardvo.getLibid_fk().equals(libid)) {
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
		
		/* ==== 글쓰기가 원글쓰기인지 아니면 답변글쓰기인지를 구분하여
			   tblBoard 테이블에 insert 를 해주어야 한다.
			       원글쓰기 이라면 tblBoard 테이블에 groupno 컬럼의 값은
			   groupno 컬럼의 최대값(max)+1 로 해서 insert 해주어야 하고
			        답변글쓰기 이라면 넘겨받은 값을 그대로 insert 해주어야 한다.
		 */
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
		
		/* ==== 글쓰기가 원글쓰기인지 아니면 답변글쓰기인지를 구분하여
		   tblBoard 테이블에 insert 를 해주어야 한다.
		       원글쓰기 이라면 tblBoard 테이블에 groupno 컬럼의 값은
		   groupno 컬럼의 최대값(max)+1 로 해서 insert 해주어야 하고
		        답변글쓰기 이라면 넘겨받은 값을 그대로 insert 해주어야 한다.
		 */
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


}
