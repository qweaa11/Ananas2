package com.spring.bookmanage.board.YJKservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.bookmanage.board.YJKmodel.YJKBoardVO;

@Service
public interface InterYJKBoardService {

	List<YJKBoardVO> getboardList(); // 전체글 리스트 가져오기

	YJKBoardVO getView(String idx, String libid);
	// 1개글 보여주기. 글 조회수 증가는 다른 사람의 글을 읽을 때만 증가하도록 한다.
	// 로그인 하지 않은 상태에서 글을 읽을 때는 조회수가 증가가 일어나지 않도록 해야한다.

	YJKBoardVO getViewWithNoAddCount(String idx);
	// 조회수(readCount)증가 없이 그냥 글 1개만 가져오는 것.

	int boardAdd(YJKBoardVO boardvo); // 파일 첨부가 없는 글쓰기

	int boardAdd_withFile(YJKBoardVO boardvo); // 파일 첨부가 있는 글쓰기

	

	

}
