package com.spring.bookmanage.board.YJKmodel;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface InterYJKBoardDAO {

	List<YJKBoardVO> getboardList(HashMap<String, String> paraMap); // 전체글 목록 가져오기

	YJKBoardVO getView(String idx); // 글 1개를 보여주기

	void setAddReadCount(String idx); // 글1개를 볼 때 조회수(readCount) 1증가 시키기 

	int boardAdd(HashMap<String, String> boardMap); // 글쓰기 요청

	int boardAdd_withFile(HashMap<String, String> boardMapList); // 파일첨부 요청
	
	List<YJKAttachFileVO> FileView(String idx); // 첨부파일 보여주기

	int getGroupnoMax(); // tblBoard 테이블에서 groupno 컬럼의 최대값 알아오기

	int getTotalCountWithSearch(HashMap<String, String> paraMap);
	// 검색조건에 만족하는 총 게시물 건수 알아오기

	int getTotalCountWithNoSearch();
	// 검색조건이 없는 총 게시물 건수 알아오기

	int boardAddComment(YJKReplyVO replyvo);
	// 댓글쓰기

	List<YJKReplyVO> listComment(HashMap<String, String> paraMap);
	// 댓글내용 가져오기 

	int getCommentTotalCount(HashMap<String, String> paraMap);
	// 댓글 TotalPage 가져오기

	int updateCommentCount(String parentidx);
	// 댓글쓰기 이후에 댓글의 갯수(commentCount 컬럼) 1증가 시키기

	boolean checkPW(HashMap<String, String> paraMap);
	// 글 수정 및 글 삭제시 암호일치 여부 알아오기

	int updateContent(HashMap<String, String> paraMap);
	// 글 수정하기

	int isExistsComment(HashMap<String, String> paraMap);
	// 원글에 댓글이 있는지 없는지 확인

	int deleteContent(HashMap<String, String> paraMap);
	// 원글 삭제하면서 그에 따른 답글 삭제
	
	int delComment(HashMap<String, String> paraMap);
	// 댓글 삭제
	
	int getCommentCnt(YJKReplyVO replyvo);
	// 댓글 Total 가져오기

	int selectBoardIdx();
	// 글 번호 채번해오기

	List<YJKBoardVO> getAttachFileCount(List<YJKBoardVO> boardList);

	
	YJKAttachFileVO fileDownload(String fileidx);
	// 파일 다운로드

}
