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

	
	// 1개글 보여주기
	@Override
	public PMGNoticeVO getView(HashMap<String, String> paraMap) {
		PMGNoticeVO noticevo = sqlsession.selectOne("PMG.getView", paraMap);
		return noticevo;
	}
	// 글 1개를 볼때 조회수(readCount) 1증가
	@Override
	public void setAddReadCount(String idx) {
		sqlsession.update("PMG.setAddReadCount", idx);
	}

	
	// 공지사항 수정, 삭제 글 암호 일치 여부 알아오기
	@Override
	public boolean noticeCheckPw(HashMap<String, String> paraMap) {
		
		int n = sqlsession.selectOne("PMG.noticeCheckPw", paraMap);
		
		boolean result = false;
		
		if(n==1) {
			result = true;
		}
		
		return result;
	}
	// 공지사항 수정
	@Override
	public int noticeEditUpdate(HashMap<String, String> paraMap) {
		int result = sqlsession.update("PMG.noticeEditUpdate", paraMap);
		return result;
	}
	// 공지사항 1개 글 삭제
	@Override
	public int deleteNotice(HashMap<String, String> paraMap) {
		int n = sqlsession.update("PMG.deleteNotice", paraMap);
		return n;
	}
	// 공지사항 1개 글의 딸린 댓글이 있는지 없는 확인하기
	@Override
	public int isExistsComment(HashMap<String, String> paraMap) {
		int count = sqlsession.selectOne("PMG.isExistsComment", paraMap);
		return count;
	}
	// 공지사항 1개 글의 딸린 댓글 삭제
	@Override
	public int deleteComment(HashMap<String, String> paraMap) {
		int n = sqlsession.update("PMG.deleteComment", paraMap);
		return n;
	}
	
	// 댓글쓰기
	@Override
	public int commentWrite(CommentVO commentvo) {
		int n = sqlsession.insert("PMG.commentWrite", commentvo);
		return n;
	}
	// 댓글쓰기 이후에 댓글의 갯수(commentcount 컬럼) 1증가
	@Override
	public int updateCommentCount(String parentIdx) {
		int n = sqlsession.update("PMG.updateCommentCount", parentIdx);
		return n;
	}

	// 원글 공지사항 글번호에 대한 댓글중 페이지 번호에 해당하는 댓글만 조회
	@Override
	public List<CommentVO> commentListLib(HashMap<String, String> paraMap) {
		List<CommentVO> libCommentList = sqlsession.selectList("PMG.commentListLib", paraMap);
		return libCommentList;
	}
	// 원글 공지사항 글번호에 해당하는 댓글의 총갯수 알아오기
	@Override
	public int getCommentLibTotalCount(HashMap<String, String> paraMap) {
		int totalCount = sqlsession.selectOne("PMG.getCommentLibTotalCount", paraMap);
		return totalCount;
	}
	// 도서관명 알아오기
	@Override
	public String findLibName(String libName) {
		String getLibName = sqlsession.selectOne("PMG.findLibName", libName);
		return getLibName;
	}
	// 댓글 삭제
	@Override
	public int commentDelete(HashMap<String, String> paraMap) {
		int n = sqlsession.update("PMG.commentDelete", paraMap);
		return n;
	}
	// 댓글 삭제 이후에 댓글의 갯수(commentCount 컬럼) 1감소
	@Override
	public int reductionCommentCount(String viewIdx) {
		int n = sqlsession.update("PMG.reductionCommentCount", viewIdx);
		return n;
	}

	

	
	
	
	
	
	
	
}
