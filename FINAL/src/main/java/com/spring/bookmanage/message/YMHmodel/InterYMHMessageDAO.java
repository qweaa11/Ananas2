package com.spring.bookmanage.message.YMHmodel;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface InterYMHMessageDAO 
{

	List<YMHMessageVO> listMessage(HashMap<String, String> paraMap);
	
	
	List<YMHMessageVO> listReceiveMessage(HashMap<String, String> paraMap);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
