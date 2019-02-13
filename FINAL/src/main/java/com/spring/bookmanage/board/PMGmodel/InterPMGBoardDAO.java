package com.spring.bookmanage.board.PMGmodel;

import java.util.HashMap;
import java.util.List;

public interface InterPMGBoardDAO {

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
	
	
	PMGNoticeVO getView(HashMap<String, String> paraMap);
	// 글 1개 보여주기
	void setAddReadCount(String idx);
	// 글 1개를 볼때 조회수(readCount) 1증가
	
	
	boolean noticeCheckPw(HashMap<String, String> paraMap); // 공지사항 수정, 삭제 글 암호 일치 여부 알아오기
	int noticeEditUpdate(HashMap<String, String> paraMap); // 공지사항 수정
	int deleteNotice(HashMap<String, String> paraMap);  // 공지사항 1개 글 삭제
	int isExistsComment(HashMap<String, String> paraMap); // 공지사항 1개 글의 딸린 댓글이 있는지 없는 확인하기
	int deleteComment(HashMap<String, String> paraMap); // 공지사항 1개 글의 딸린 댓글 삭제
	
	int commentWrite(CommentVO commentvo);
	// 댓글쓰기
	String findLibName(String libName);
	// 도서관명 알아오기
	int updateCommentCount(String parentIdx);
	// 댓글쓰기 이후에 댓글의 갯수(commentcount 컬럼) 1증가
	
	List<CommentVO> commentListLib(HashMap<String, String> paraMap);
	// 원글 공지사항 글번호에 대한 댓글중 페이지 번호에 해당하는 댓글만 조회
	int getCommentLibTotalCount(HashMap<String, String> paraMap);
	// 원글 공지사항 글번호에 해당하는 댓글의 총갯수 알아오기
	
	int commentDelete(HashMap<String, String> paraMap);
	// 댓글 삭제
	int reductionCommentCount(String viewIdx);
	// 댓글 삭제 이후에 댓글의 갯수(commentCount 컬럼) 1감소
	
	public int findNoticeMaxidx();
	// 등록한 공지사항 idx 가져오기
	
	
	void insertAlarm(HashMap<String, String> paraMap, List<String> libIdList) throws Throwable;
	// 공지사항 글 등록이 완료되면 알람테이블에 insert
	
	List<String> findLibId(HashMap<String, String> paraMap);
	// librarian테이블의 조건(libcode)에 맞는 아이디(도서관장, 사서) 가져오기
	
	
	int findAlarmByMap(CommentVO commentvo);
	// 알람테이블의 댓글을 썼는지 안썼는지 가져오는 카운트용
	int updateAlarm(CommentVO commentvo);
	// 댓글을 작성하면 알람테이블 status 1로 변경(공지사항 게시글 읽음으로 변경)
	
	
}
