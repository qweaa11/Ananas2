package com.spring.bookmanage.board.BSBmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



//===== #32. DAO 선언  =====
@Repository
public class BSBboardDAO implements BSBInterBoardDAO {
	
	//===== #33. 의존객체 주입하기(DI: Dependency Injection) =====
	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public int getTotalCountWithSearch(HashMap<String, String> paraMap) {
		int count = sqlsession.selectOne("BSB.getTotalCountWithSearch", paraMap);
		return count;
	}

	@Override
	public int getTotalCountNoSearch() {
		int count = sqlsession.selectOne("BSB.getTotalCountNoSearch");
		return count;
	}

	@Override
	public List<BSBboardVO> boardListPaging(HashMap<String, String> paraMap) {
		List<BSBboardVO> boardList = sqlsession.selectList("BSB.boardListPaging", paraMap);
		return boardList;
	}

	@Override
	public int add(BSBboardVO boardvo) {
		int n = sqlsession.insert("BSB.add",boardvo);
		
		return n;
	}

	@Override
	public BSBboardVO getView(String idx) {
		BSBboardVO boardvo = sqlsession.selectOne("BSB.getView", idx);
		return boardvo;
	}

	
	


	
}
