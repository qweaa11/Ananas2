package com.spring.bookmanage.library.PMGservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.library.PMGmodel.PMGLibraryDAO;

@Service
public class PMGLibraryService {

	@Autowired
	private PMGLibraryDAO dao;

	public int alarmCount(String libId) {
		
		int libCount = dao.alarmCount(libId);
		
		return libCount;
	}
	
}
