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

	
	@Override
	public List<HashMap<String, String>> findBasicInfo() {
		
		List<HashMap<String, String>> basicInfoList = dao.findBasicInfo();
		return basicInfoList;
	}
}
