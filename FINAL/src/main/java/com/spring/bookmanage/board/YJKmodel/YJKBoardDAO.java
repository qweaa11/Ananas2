package com.spring.bookmanage.board.YJKmodel;

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
	public List<YJKBoardVO> getboardList() {
		
		List<YJKBoardVO> getboardList = sqlsession.selectList("bookmanage.getboardList");
		
		return getboardList;
	}

	// ==== 글 1개를 보여주는 페이지 요청(먼저 글 조회수 증가를 할지 안 할지 결정해야함) =====
	@Override
	public YJKBoardVO getView(String idx) {
		
		YJKBoardVO boardvo = sqlsession.selectOne("bookmanage.getView", idx);
		System.out.println("idx2:"+idx);
		System.out.println("boardvo-idx:"+boardvo.getIdx());
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

	// ==== tblBoard 테이블에서 groupno 컬럼의 최대값 알아오기
	@Override
	public int getGroupnoMax() {
		
		int maxgroupno = sqlsession.selectOne("bookmanage.getGroupnoMax");
		
		return maxgroupno;
	}

	

}
