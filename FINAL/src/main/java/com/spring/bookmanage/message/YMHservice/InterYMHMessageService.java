package com.spring.bookmanage.message.YMHservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.bookmanage.message.YMHmodel.YMHMessageVO;

@Service
public interface InterYMHMessageService 
{

	List<YMHMessageVO> listMessage(HashMap<String, String> paraMap);

	List<YMHMessageVO> listReceiveMessage(HashMap<String, String> paraMap);
	
	
	
	
	
	
}
