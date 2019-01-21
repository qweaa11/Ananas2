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

	
	

	
}
