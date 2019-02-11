package com.spring.bookmanage.message.YMHmodel;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface InterYMHMessageDAO 
{

	List<YMHMessageVO> listMessage(HashMap<String, String> paraMap);
	
	
	List<YMHMessageVO> listReceiveMessage(HashMap<String, String> paraMap);


	int getReceiveMessageTotalCount(HashMap<String, String> paraMap);
	
	int getSendMessageTotalCount(HashMap<String, String> paraMap);
	
	int deleteReceiveMessage(HashMap<String, String> paraMap);
	
	int deleteSendMessage(HashMap<String, String> paraMap);
	
	YMHMessageVO listMessage(String idx);


	int setOpenDate(String idx);
	
	
	
	
	
	
	

}
