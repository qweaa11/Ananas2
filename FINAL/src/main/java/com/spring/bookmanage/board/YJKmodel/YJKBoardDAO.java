package com.spring.bookmanage.board.YJKmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class YJKBoardDAO implements InterYJKBoardDAO {
	
	//===== 의존객체 주입하기(DI : Dependency Injection)  =====
	@Autowired
	private SqlSessionTemplate sqlsession;

	// ==== 전체 글 목록 가져오기 ====
	@Override
	public List<YJKBoardVO> getboardList(HashMap<String, String> paraMap) {
		
		List<YJKBoardVO> getboardList = sqlsession.selectList("bookmanage.getboardList", paraMap);
		
		return getboardList;
	}

	// ==== 글 1개를 보여주는 페이지 요청(먼저 글 조회수 증가를 할지 안 할지 결정해야함) =====
	@Override
	public YJKBoardVO getView(String idx) {
		
		YJKBoardVO boardvo = sqlsession.selectOne("bookmanage.getView", idx);
	//	System.out.println("idx2:"+idx);
	//	System.out.println("boardvo-idx:"+boardvo.getIdx());
		return boardvo;
	}

	// ==== 글 1개를 보여줄때 조회수(readCount) 1 증가 시키기 =====
	@Override
	public void setAddReadCount(String idx) {
		
		sqlsession.update("bookmanage.setAddReadCount", idx);
		
	}

	// ==== 파일첨부가 없는 글쓰기 요청 ==== //
	@Override
	public int boardAdd(YJKBoardVO boardvo) {
		
		int n = sqlsession.insert("bookmanage.boardAdd", boardvo);
		
		return n;
	}

	// ==== 파일첨부가 있는 글쓰기 요청 ==== //
	@Override
	public int boardAdd_withFile(YJKBoardVO boardvo) {
		
		int n = sqlsession.insert("bookmanage.boardAdd_withFile", boardvo);
		
		return n;
	}

	// ==== Board 테이블에서 groupno 컬럼의 최대값 알아오기
	@Override
	public int getGroupnoMax() {
		
		int maxgroupno = sqlsession.selectOne("bookmanage.getGroupnoMax");
		
		return maxgroupno;
	}

	// ==== 검색조건에 만족하는 총 게시물 건수 알아오기 ====
	@Override
	public int getTotalCountWithSearch(HashMap<String, String> paraMap) {
		
		int count = sqlsession.selectOne("bookmanage.getTotalCountWithSearch", paraMap);
		
		return count;
	}

	// ==== 검색조건이 없는 총 게시물 건수 알아오기 ====
	@Override
	public int getTotalCountWithNoSearch() {
		
		int count = sqlsession.selectOne("bookmanage.getTotalCountWithNoSearch");
		
		return count;
	}

	// ==== 댓글쓰기 ====
	@Override
	public int boardAddComment(YJKReplyVO replyvo) {
		
		int n = sqlsession.insert("bookmanage.boardAddComment", replyvo);
		
		return n;
	}
	
	// ==== 댓글쓰기 이후에 댓글의 갯수(commentCount 컬럼) 1증가 시키기 ====
	@Override
	public int updateCommentCount(String parentidx) {

		int n = sqlsession.update("bookmanage.updateCommentCount", parentidx);
		
		return n;
	}

	// ==== 원글에 글번호에 대한 댓글 중 페이지 번호에 해당하는 댓글만 조회해온다.
	@Override
	public List<YJKReplyVO> listComment(HashMap<String, String> paraMap) {
		
		List<YJKReplyVO> commentList = sqlsession.selectList("bookmanage.listComment", paraMap);
		
		return commentList;
	}

	// ==== 원글에 글번호에 대한 댓글 중 페이지 번호에 해당하는 댓글만 조회해온다.
	@Override
	public int getCommentTotalCount(HashMap<String, String> paraMap) {
		
		int totalCount = sqlsession.selectOne("bookmanage.getCommentTotalCount", paraMap);
		
		return totalCount;
	}

	// 글 수정 및 글 삭제시 암호일치 여부 알아오기
	@Override
	public boolean checkPW(HashMap<String, String> paraMap) {
		
		int n = sqlsession.selectOne("bookmanage.checkPW", paraMap);
		
		boolean result = false;
		
		if(n == 1) {
			result = true;
		}
		
		return result;
	}

	// 글 수정하기
	@Override
	public int updateContent(HashMap<String, String> paraMap) {
		
		int result = sqlsession.update("bookmanage.updateContent", paraMap);
		
		return result;
	}

	// 원글에 댓글이 있는지 없는지 확인
	@Override
	public int isExistsComment(HashMap<String, String> paraMap) {
		
		int count = sqlsession.selectOne("bookmanage.isExistsComment", paraMap);
		
		return count;
	}

	// 원글 삭제
	@Override
	public int deleteContent(HashMap<String, String> paraMap) {

		int n = sqlsession.update("bookmanage.deleteContent", paraMap);
		
		return n;
	}

	// 댓글 삭제
	@Override
	public int delComment(HashMap<String, String> paraMap) {
		
		int n = sqlsession.update("board.delComment", paraMap);
		
		return n;
	}

	

	

}
