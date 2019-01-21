package com.spring.bookmanage.returned.YMHmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class YMHReturnedDAO implements InterYMHReturnedDAO 
{
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	@Override
	public List<ReturnedVO> listReturned(HashMap<String, String> paraMap) {
		List<ReturnedVO> returnedList =  sqlsession.selectList("YMH.listReturned",paraMap);
		return returnedList;
	}
	
	
	
	
}























