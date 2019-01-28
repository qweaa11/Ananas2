package com.spring.bookmanage.board.PMGservice;

import java.util.HashMap;
import java.util.List;

import com.spring.bookmanage.board.PMGmodel.PMGNoticeVO;

public interface InterPMGBoardService {

	int noticeWriteadd(PMGNoticeVO noticevo); // 공지사항 글쓰기(파일첨부가 없는 글쓰기)
	int noticeWriteadd_withFile(PMGNoticeVO noticevo);// 공지사항 글쓰기(파일첨부가 있는 글쓰기)
	
	
	int getTotalCountWithSearch(HashMap<String, String> paraMap);
	// 검색조건에 만족하는 총게시물 갯수를 알아오기
	int getTotalCountNoSerach();
	// 검색조건이 없는 총게시물 갯수를 알아오기
	List<PMGNoticeVO> noticeListPaging(HashMap<String, String> paraMap);
	// 검색조건이 없는것 또는 검색조건이 있는 것을 포함한 전체 공지사항 목록 가져오기(페이징 처리 함)
	
	
	List<PMGNoticeVO> noticeAdminListWithSearch(HashMap<String, String> paraMap);
	// 총괄관리자 => 공지사항 글 목록(검색까지)
	int getNoticeTotalCount(HashMap<String, String> paraMap);
	// notice테이블 총 페이지수 알아오기
	
	
	List<PMGNoticeVO> noticelib1ListWithSearch(HashMap<String, String> paraMap);
	// 도서관장 => 공지사항 글 목록(검색까지)
	int lib1getNoticeTotalCount(HashMap<String, String> paraMap);
	// 도서관장 notice테이블 총 페이지수 알아오기
	
	
	List<PMGNoticeVO> noticelib0ListWithSearch(HashMap<String, String> paraMap);
	// 사서 => 공지사항 글 목록(검색까지)
	int lib0getNoticeTotalCount(HashMap<String, String> paraMap);
	// 사서 notice테이블 총 페이지수 알아오기
	
	
}
