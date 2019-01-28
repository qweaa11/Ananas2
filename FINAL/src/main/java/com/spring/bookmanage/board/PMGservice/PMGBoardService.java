package com.spring.bookmanage.board.PMGservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.board.PMGmodel.PMGBoardDAO;
import com.spring.bookmanage.board.PMGmodel.PMGNoticeVO;
import com.spring.bookmanage.common.AES256;

@Service
public class PMGBoardService implements InterPMGBoardService {

	// 의존객체 주입하기(DI : Dependency Injection)
	@Autowired
	private PMGBoardDAO dao;
	
	@Autowired
	private AES256 aes;

	
	// 공지사항 글쓰기(파일첨부가 없는 글쓰기)
	@Override
	public int noticeWriteadd(PMGNoticeVO noticevo) {
		int n = dao.noticeWriteadd(noticevo);
		return n;
	}

	// 공지사항 글쓰기(파일첨부가 있는 글쓰기)
	@Override
	public int noticeWriteadd_withFile(PMGNoticeVO noticevo) {
		int n = dao.noticeWriteadd_withFile(noticevo);
		return n;
	}

	
	// 검색조건에 만족하는 총게시물 갯수를 알아오기
	@Override
	public int getTotalCountWithSearch(HashMap<String, String> paraMap) {
		int n = dao.getTotalCountWithSearch(paraMap);
		return n;
	}
	// 검색조건이 없는 총게시물 갯수를 알아오기
	@Override
	public int getTotalCountNoSerach() {
		int n = dao.getTotalCountNoSerach();
		return n;
	}
	// 검색조건이 없는것 또는 검색조건이 있는 것을 포함한 전체 공지사항 목록 가져오기(페이징 처리 함)
	@Override
	public List<PMGNoticeVO> noticeListPaging(HashMap<String, String> paraMap) {
		List<PMGNoticeVO> noticeList = dao.noticeListPaging(paraMap);
		return noticeList;
	}

	
	// 총괄관리자 => 공지사항 글 목록(검색까지)
	@Override
	public List<PMGNoticeVO> noticeAdminListWithSearch(HashMap<String, String> paraMap) {
		
		List<PMGNoticeVO> noticeList = dao.noticeAdminListWithSearch(paraMap);
		
		return noticeList;
	}
	// notice테이블 총 페이지수 알아오기
	@Override
	public int getNoticeTotalCount(HashMap<String, String> paraMap) {
		int totalCount = dao.getNoticeTotalCount(paraMap);
		return totalCount;
	}

	
	// 도서관장 => 공지사항 글 목록(검색까지)
	@Override
	public List<PMGNoticeVO> noticelib1ListWithSearch(HashMap<String, String> paraMap) {
		List<PMGNoticeVO> noticeList = dao.noticelib1ListWithSearch(paraMap);
		return noticeList;
	}
	// 도서관장 notice테이블 총 페이지수 알아오기
	@Override
	public int lib1getNoticeTotalCount(HashMap<String, String> paraMap) {
		int totalCount = dao.lib1getNoticeTotalCount(paraMap);
		return totalCount;
	}

	
	// 사서 => 공지사항 글 목록(검색까지)
	@Override
	public List<PMGNoticeVO> noticelib0ListWithSearch(HashMap<String, String> paraMap) {
		List<PMGNoticeVO> noticeList = dao.noticelib0ListWithSearch(paraMap);
		return noticeList;
	}
	// 사서 notice테이블 총 페이지수 알아오기
	@Override
	public int lib0getNoticeTotalCount(HashMap<String, String> paraMap) {
		int totalCount = dao.lib0getNoticeTotalCount(paraMap);
		return totalCount;
	}

	
	

	
	
	
}
