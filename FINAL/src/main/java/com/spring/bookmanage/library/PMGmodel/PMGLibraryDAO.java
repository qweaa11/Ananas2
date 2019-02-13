package com.spring.bookmanage.library.PMGmodel;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PMGLibraryDAO {

	@Autowired
	private SqlSessionTemplate sqlsession;

	public int alarmCount(String libId) {
		
		int n = sqlsession.selectOne("PMG.alarmCount", libId);
		
		return n;
	}
	
}
