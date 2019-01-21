package com.spring.bookmanage.board.PMGservice;

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
	
	
	
	
}
