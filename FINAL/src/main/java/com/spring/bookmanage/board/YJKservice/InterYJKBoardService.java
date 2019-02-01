package com.spring.bookmanage.board.YJKservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.bookmanage.board.YJKmodel.YJKAttachFileVO;
import com.spring.bookmanage.board.YJKmodel.YJKBoardVO;
import com.spring.bookmanage.board.YJKmodel.YJKReplyVO;

@Service
public interface InterYJKBoardService {

	List<YJKBoardVO> getboardList(HashMap<String, String> paraMap); // 전체글 리스트 가져오기

	List<YJKBoardVO> getAttachFileCount(List<YJKBoardVO> boardList); // board_attachfile 테이블에 있는 첨부파일 갯수
	
	List<YJKBoardVO> getorgTextCount(List<YJKBoardVO> boardList2); // board 테이블에 있는 원글 카운트

	YJKBoardVO getView(String idx, String libid);
	// 1개글 보여주기. 글 조회수 증가는 다른 사람의 글을 읽을 때만 증가하도록 한다.
	// 로그인 하지 않은 상태에서 글을 읽을 때는 조회수가 증가가 일어나지 않도록 해야한다.

	YJKBoardVO getViewWithNoAddCount(String idx);
	// 조회수(readCount)증가 없이 그냥 글 1개만 가져오는 것.

	int boardAdd(HashMap<String, String> boardMap); // 파일 첨부가 없는 글쓰기

	int boardAdd_withFile(HashMap<String, String> hashMap); // 파일 첨부하기
	
	List<YJKAttachFileVO> FileView(String idx); // 첨부파일 보여주기

	int getTotalCountWithSearch(HashMap<String, String> paraMap); 
	// 검색조건에 만족하는 총 게시물 건수 알아오기

	int getTotalCountWithNoSearch();
	// 검색조건이 없는 총 게시물 건수 알아오기

	int boardAddComment(YJKReplyVO replyvo);
	// 댓글쓰기

	List<YJKReplyVO> listComment(HashMap<String, String> paraMap);
	// 댓글 내용 가져오기

	int getCommentTotalCount(HashMap<String, String> paraMap);
	// 댓글 TotalPage 가져오기

	int boardEdit(HashMap<String, String> paraMap);
	// 글 수정하기

	int boardDel(HashMap<String, String> paraMap) throws Throwable;
	// 글 삭제하기
	
	int getCommentCnt(YJKReplyVO replyvo);
	// 댓글개수 카운팅

	int selectBoardIdx();
	// board idx 채번

	YJKAttachFileVO fileDownload(String fileidx);
	// 파일 다운로드

}
