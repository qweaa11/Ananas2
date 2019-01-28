package com.spring.bookmanage.board.PMGmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PMGBoardDAO implements InterPMGBoardDAO {

	// 의존객체 주입하기(DI : Dependency Injection)
	@Autowired
	private SqlSessionTemplate sqlsession;

	// 공지사항 글쓰기(파일첨부가 없는 글쓰기)
	@Override
	public int noticeWriteadd(PMGNoticeVO noticevo) {
		int n = sqlsession.insert("PMG.noticeWriteadd", noticevo);
		return n;
	}

	// 공지사항 글쓰기(파일첨부가 있는 글쓰기)
	@Override
	public int noticeWriteadd_withFile(PMGNoticeVO noticevo) {
		int n = sqlsession.insert("PMG.noticeWriteadd_withFile", noticevo);
		return n;
	}

	
	// 검색조건에 만족하는 총게시물 갯수를 알아오기
	@Override
	public int getTotalCountWithSearch(HashMap<String, String> paraMap) {
		int count = sqlsession.selectOne("PMG.getTotalCountWithSearch", paraMap);
		return count;
	}
	// 검색조건이 없는 총게시물 갯수를 알아오기
	@Override
	public int getTotalCountNoSerach() {
		int count = sqlsession.selectOne("PMG.getTotalCountNoSerach");
		return count;
	}
	// 검색조건이 없는것 또는 검색조건이 있는 것을 포함한 전체 공지사항 목록 가져오기(페이징 처리 함)
	@Override
	public List<PMGNoticeVO> noticeListPaging(HashMap<String, String> paraMap) {
		List<PMGNoticeVO> noticeList = sqlsession.selectList("PMG.noticeListPaging", paraMap);
		return noticeList;
	}

	
	// 총괄관리자 => 공지사항 글 목록(검색까지)
	@Override
	public List<PMGNoticeVO> noticeAdminListWithSearch(HashMap<String, String> paraMap) {
		List<PMGNoticeVO> noticeList = sqlsession.selectList("PMG.noticeListWithSearch", paraMap);
		return noticeList;
	}
	// notice테이블 총 페이지수 알아오기
	@Override
	public int getNoticeTotalCount(HashMap<String, String> paraMap) {
		int totalCount = sqlsession.selectOne("PMG.getNoticeTotalCount",paraMap);
		return totalCount;
	}

	
	// 도서관장 => 공지사항 글 목록(검색까지)
	@Override
	public List<PMGNoticeVO> noticelib1ListWithSearch(HashMap<String, String> paraMap) {		
		List<PMGNoticeVO> noticeList = sqlsession.selectList("PMG.noticelib1ListWithSearch", paraMap);
		return noticeList;
	}
	// 도서관장 notice테이블 총 페이지수 알아오기
	@Override
	public int lib1getNoticeTotalCount(HashMap<String, String> paraMap) {		
		int totalCount = sqlsession.selectOne("PMG.lib1getNoticeTotalCount", paraMap);
		return totalCount;
	}

	
	// 사서 => 공지사항 글 목록(검색까지)
	@Override
	public List<PMGNoticeVO> noticelib0ListWithSearch(HashMap<String, String> paraMap) {
		List<PMGNoticeVO> noticeList = sqlsession.selectList("PMG.noticelib0ListWithSearch", paraMap);
		return noticeList;
	}
	// 사서 notice테이블 총 페이지수 알아오기
	@Override
	public int lib0getNoticeTotalCount(HashMap<String, String> paraMap) {
		int totalCount = sqlsession.selectOne("PMG.lib0getNoticeTotalCount", paraMap);
		return totalCount;
	}

	
	
	
	
}
