package com.spring.bookmanage.message.YSWservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface InterYSWMessageService {

	// 메세지 보내기 페이지를 보여줄때 도서관 이름 가져오기
	List<HashMap<String, String>> findLibrary();

	// 해당 도서관에 소속 된 관리자, 사서 보여주기 
	List<HashMap<String, Object>> findRecipientList(String libname);

	// 메세지 보내기
	int sendMessage(HashMap<String, String> paraMap);

	
}
