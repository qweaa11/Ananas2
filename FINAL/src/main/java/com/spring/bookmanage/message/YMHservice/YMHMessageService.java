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



	
}
