package com.spring.bookmanage.board.YJKmodel;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface InterYJKBoardDAO {

	List<YJKBoardVO> getboardList(); // 전체글 목록 가져오기

	YJKBoardVO getView(String idx); // 글 1개를 보여주기

	void setAddReadCount(String idx); // 글1개를 볼 때 조회수(readCount) 1증가 시키기 

	int boardAdd(YJKBoardVO boardvo); // 파일첨부가 없는 글쓰기 요청

	int boardAdd_withFile(YJKBoardVO boardvo); // 파일첨부가 있는 글쓰기 요청

	int getGroupnoMax(); // tblBoard 테이블에서 groupno 컬럼의 최대값 알아오기

}
