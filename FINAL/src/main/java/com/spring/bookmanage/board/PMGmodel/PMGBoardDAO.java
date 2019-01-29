package com.spring.bookmanage.board.PMGmodel;

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
	
	
	
}
