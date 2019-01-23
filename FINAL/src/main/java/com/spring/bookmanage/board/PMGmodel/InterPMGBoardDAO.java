package com.spring.bookmanage.board.PMGmodel;

public interface InterPMGBoardDAO {

	int noticeWriteadd(PMGNoticeVO noticevo); // 공지사항 글쓰기(파일첨부가 없는 글쓰기)
	int noticeWriteadd_withFile(PMGNoticeVO noticevo);// 공지사항 글쓰기(파일첨부가 있는 글쓰기)
	
}
