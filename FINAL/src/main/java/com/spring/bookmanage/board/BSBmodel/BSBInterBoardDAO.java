package com.spring.bookmanage.board.BSBmodel;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;





// model단 (DAO)의 인터페이스 선언
@Repository
public interface BSBInterBoardDAO {

	int getTotalCountWithSearch(HashMap<String, String> paraMap);

	int getTotalCountNoSearch();

	List<BSBboardVO> boardListPaging(HashMap<String, String> paraMap);

	int add(BSBboardVO boardvo);

	BSBboardVO getView(String idx);

	int addComment(BSBcommentVO commentvo);

	int updateCommentCount(String parentidx);

	List<BSBcommentVO> listComment(HashMap<String, String> paraMap);

	int getCommentTotalCount(HashMap<String, String> paraMap);

	boolean checkPW(HashMap<String, String> paraMap);

	int isExistsComment(HashMap<String, String> paraMap);

	int deleteContent(HashMap<String, String> paraMap);

	int delComment(HashMap<String, String> paraMap);

	int getGroupnoMax();

	int add_withFile(BSBboardVO boardvo);

	int commentdel(HashMap<String, String> paraMap);
	
	int updateCommentdelCount(String string);

	int updateContent(HashMap<String, String> paraMap);

	void setAddReadCount(String idx);



	
	

	
}
