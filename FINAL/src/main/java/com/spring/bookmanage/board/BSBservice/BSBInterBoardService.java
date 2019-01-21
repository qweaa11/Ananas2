package com.spring.bookmanage.board.BSBservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.bookmanage.board.BSBmodel.BSBboardVO;


// Service단 인터페이스 선언
@Service
public interface BSBInterBoardService {

	int getTotalCountWithSearch(HashMap<String, String> paraMap);

	int getTotalCountNoSearch();

	List<BSBboardVO> boardListPaging(HashMap<String, String> paraMap);

	int add(BSBboardVO boardvo);

	BSBboardVO getView(String idx);

	

}
