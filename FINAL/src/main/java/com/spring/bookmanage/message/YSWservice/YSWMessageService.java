package com.spring.bookmanage.message.YSWservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.message.YSWmodel.InterYSWMessageDAO;

@Service
public class YSWMessageService implements InterYSWMessageService {
	
	@Autowired
	private InterYSWMessageDAO dao;

	
	// 메세지 보내기 페이지를 보여줄때 도서관 이름 가져오기
	@Override
	public List<HashMap<String, String>> findLibrary() {

		List<HashMap<String, String>> libraryName = dao.findLibrary();
		return libraryName;
	}


	// 해당 도서관에 소속 된 관리자, 사서 보여주기 
	@Override
	public List<HashMap<String, Object>> findRecipientList(String libname) {

		List<HashMap<String, Object>> recipientList = dao.findRecipientList(libname);
		return recipientList;
	}


	// 메세지 보내기
	@Override
	public int sendMessage(HashMap<String, String> paraMap) {

		int result = dao.sendMessage(paraMap);
		return result;
	}


	
}
