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


	@Override
	public int getReceiveMessageTotalCount(HashMap<String, String> paraMap) {
		int totalCount = sqlsession.selectOne("YMH.getReceiveMessageTotalCount",paraMap);
		return totalCount;
	}
	
	
	@Override
	public int getSendMessageTotalCount(HashMap<String, String> paraMap) {
		int totalCount = sqlsession.selectOne("YMH.getSendMessageTotalCount",paraMap);
		return totalCount;
	}


	@Override
	public int deleteReceiveMessage(HashMap<String, String> paraMap) {
		int result = sqlsession.update("YMH.deleteReceiveMessage",paraMap);
		return result;
	}


	@Override
	public int deleteSendMessage(HashMap<String, String> paraMap) {
		int result = sqlsession.update("YMH.deleteSendMessage",paraMap);
		return result;
	}


	@Override
	public YMHMessageVO listMessage(String idx) {
		YMHMessageVO mvo = sqlsession.selectOne("YMH.listYMHMessage",idx);
		return mvo;
	}

	@Override
	public int setOpenDate(String idx) {
		int result = sqlsession.update("YMH.setOpenDate",idx);
		return result;
	}
	
	
	
	

}
