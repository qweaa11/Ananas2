package com.spring.bookmanage.board.BSBservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.bookmanage.board.BSBmodel.BSBboardVO;
import com.spring.bookmanage.board.BSBmodel.BSBcommentVO;


// Service단 인터페이스 선언
@Service
public interface BSBInterBoardService {

	int getTotalCountWithSearch(HashMap<String, String> paraMap);

	int getTotalCountNoSearch();

	List<BSBboardVO> boardListPaging(HashMap<String, String> paraMap);

	int add(BSBboardVO boardvo);

	BSBboardVO getView(String idx, String memberid);

	int addComment(BSBcommentVO commentvo) throws Throwable;

	List<BSBcommentVO> listComment(HashMap<String, String> paraMap);

	int getCommentTotalCount(HashMap<String, String> paraMap);

	int del(HashMap<String, String> paraMap) throws Throwable;

	BSBboardVO getViewWithNoAddCount(String idx);

	int add_withFile(BSBboardVO boardvo);

	int commentdel(HashMap<String, String> paraMap) throws Throwable;

	int edit(HashMap<String, String> paraMap);

	

}
