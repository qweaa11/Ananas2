package com.spring.bookmanage.message.YMHservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.message.YMHmodel.InterYMHMessageDAO;
import com.spring.bookmanage.message.YMHmodel.YMHMessageVO;


@Service
public class YMHMessageService implements InterYMHMessageService 
{
	
	@Autowired
	private InterYMHMessageDAO dao;

	@Override
	public List<YMHMessageVO> listMessage(HashMap<String, String> paraMap) {
		List<YMHMessageVO> messageList = dao.listMessage(paraMap);
		return messageList;
	}

	@Override
	public List<YMHMessageVO> listReceiveMessage(HashMap<String, String> paraMap) {
		List<YMHMessageVO> messageList = dao.listReceiveMessage(paraMap);
		return messageList;
	}

	@Override
	public int getReceiveMessageTotalCount(HashMap<String, String> paraMap) {
		int totalCount = dao.getReceiveMessageTotalCount(paraMap);
		return totalCount;
	}

	@Override
	public int getSendMessageTotalCount(HashMap<String, String> paraMap) {
		int totalCount = dao.getSendMessageTotalCount(paraMap);
		return totalCount;
	}

	@Override
	public int deleteReceiveMessage(HashMap<String, String> paraMap) {
		int result = dao.deleteReceiveMessage(paraMap);
		return result;
	}

	
	@Override
	public int deleteSendMessage(HashMap<String, String> paraMap) {
		int result = dao.deleteSendMessage(paraMap);
		return result;
	}

	@Override
	public YMHMessageVO listMessage(String idx) {
		YMHMessageVO mvo = dao.listMessage(idx);
		return mvo;
	}
	
	
	@Override
	public int setOpenDate(String idx) {
		int n = dao.setOpenDate(idx);
		return n;
	}
	
}
