package com.spring.bookmanage.message.YMHmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class YMHMessageDAO implements InterYMHMessageDAO 
{
	
	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public List<YMHMessageVO> listMessage(HashMap<String, String> paraMap) {
		List<YMHMessageVO> messageList = sqlsession.selectList("YMH.listMessage",paraMap);
		return messageList;
	}

	
	@Override
	public List<YMHMessageVO> listReceiveMessage(HashMap<String, String> paraMap) {
		List<YMHMessageVO> messageList = sqlsession.selectList("YMH.listReceiveMessage",paraMap);
		return messageList;
	}
	
	
	


	

}
