package com.spring.bookmanage.board.PMGservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bookmanage.board.PMGmodel.CommentVO;
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

	
	// 1개글 보여주기, 글 조회수 증가는 다른 사람의 글을 읽을때만 증가하도록 한다.
	@Override
	public PMGNoticeVO getView(HashMap<String, String> paraMap) {
		
		PMGNoticeVO noticevo = dao.getView(paraMap);
		
		String userid = paraMap.get("USERID");
		String idx = paraMap.get("IDX");
		
	//	System.out.println(noticevo.getAdminid_fk());
	//	System.out.println(userid.equals(noticevo.getAdminid_fk()));
		
		if(userid != null && !userid.equals(noticevo.getAdminid_fk()) && !userid.equals(noticevo.getLibid_fk()) ) {
			// 조회수 증가는 로그인 되어져 있는 상태에서 다른 사람이 작성한 글을 읽었을때만 조회수 증가
			dao.setAddReadCount(idx);
			noticevo = dao.getView(paraMap);
		}
		
		return noticevo;
	}
	// 조회수(readCount)증가 없이 그냥 글 1개만 가져오는 것
	@Override
	public PMGNoticeVO getViewWithNoAddCount(HashMap<String, String> paraMap) {
		PMGNoticeVO noticevo = dao.getView(paraMap);
		return noticevo;
	}

	
	// 공지사항 수정
	@Override
	public int noticeEdit(HashMap<String, String> paraMap) {
		
		boolean noticeCheckPw = dao.noticeCheckPw(paraMap);
		// 글암호가 일치하면 true, 불일치면 false 반환.
		
		int result = 0;
		
		if(noticeCheckPw == true) {
			result = dao.noticeEditUpdate(paraMap); // 공지사항 1개 글 수정
		}
		
		return result;
	}
	// 공지사항 삭제 // 트랜잭션 처리
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation= Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	public int noticeDelete(HashMap<String, String> paraMap) {
		
		boolean noticeCheckPw = dao.noticeCheckPw(paraMap);
		// 글암호가 일치하면 true, 불일치면 false 반환.
		
		int count = 0; // 공지사항 원글에 딸린 댓글의 갯수 유무확인용
		int result1 = 0;
		int result2 = 0;
		int n = 0;
		
		if(noticeCheckPw == true) {
			count = dao.isExistsComment(paraMap);	// 공지사항 원글에 딸린 댓글의 갯수 유무확인
			result1 = dao.deleteNotice(paraMap);	// 공지사항 1개 삭제
			
			if(count > 0) {
				result2 = dao.deleteComment(paraMap);	// 공지사항에 딸린 댓글 삭제
			}
		}
		
		if( (result1 > 0 && (count > 0 && result2 > 0)) || (result1 > 0 && count == 0) ) {
			n = 1;
		}
		
		return n;
	}

	
	// 댓글쓰기 
	// reply_notice => insert, notice => commentCount 컬럼 1증가(update)
	// 2개 이상의 DML 처리를 하므로 트랜잭션 처리를 해야 한다.
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation= Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	public int commentWrite(CommentVO commentvo) throws Throwable {
		
		int result = 0;
		
		int n = 0;
		n = dao.commentWrite(commentvo);
		
		if(n==1) {
			result = dao.updateCommentCount(commentvo.getParentIdx());
		}
		
		return result;
	}
	// 원글 공지사항 글번호에 대한 댓글중 페이지 번호에 해당하는 댓글만 조회
	@Override
	public List<CommentVO> commentListLib(HashMap<String, String> paraMap) {
		List<CommentVO> libCommentList = dao.commentListLib(paraMap);
		return libCommentList;
	}
	// 원글 공지사항 글번호에 해당하는 댓글의 총갯수 알아오기
	@Override
	public int getCommentLibTotalCount(HashMap<String, String> paraMap) {
		int totalCount = dao.getCommentLibTotalCount(paraMap);
		return totalCount;
	}
	// 도서관명 알아오기
	@Override
	public String findLibName(String libName) {
		String getLibName = dao.findLibName(libName);
		return getLibName;
	}
	// 댓글 삭제
	// 트랜잭션 처리 reply_notice => update, notice => commentCount 컬럼 1감소(update)
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation= Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	public int commentDelete(HashMap<String, String> paraMap) throws Throwable {
		
		int result = 0;
		
		int n = 0;
		n = dao.commentDelete(paraMap);
		
		if(n == 1) {
			result = dao.reductionCommentCount(paraMap.get("VIEWIDX"));
		}
		
		return result;
	}
	
	
	

	
	
	
}
