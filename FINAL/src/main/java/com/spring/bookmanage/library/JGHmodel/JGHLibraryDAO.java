package com.spring.bookmanage.library.JGHmodel;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JGHLibraryDAO implements JGHLibraryMapper {
	@Autowired private SqlSessionTemplate sqlsession;

	@Override
	public int runScheduler() {
		int row = 0;

		HashMap<String, String> paraMap = new HashMap<>();
		sqlsession.selectOne("jgh.runScheduler",paraMap);

		row = Integer.parseInt(paraMap.get("row"));

		return row;
	}// end of runScheduler
}