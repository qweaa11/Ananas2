package com.spring.bookmanage.library.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.library.model.NSYInterLibraryDAO;
import com.spring.bookmanage.library.model.NSYLibraryVO;

//===== Service 선언  =====
@Service
public class NSYLibraryService implements NSYInterLibraryService{
	
	//===== #34. 의존객체 주입하기(DI : Dependency Injection) =====
    @Autowired
	private NSYInterLibraryDAO dao;

	@Override
	public int resisterLibraryEnd(NSYLibraryVO libraryvo) {
		
		int result = dao.resisterLibraryEnd(libraryvo);
		
		return result;
	}

	@Override
	public List<NSYLibraryVO> getLibraryList(HashMap<String, String>paraMap) {
		List<NSYLibraryVO> libraryList = dao.getLibraryList(paraMap);
		return libraryList;
	}

	@Override
	public int getLibraryTotalList(HashMap<String, String> paraMap) {
		int result = dao.getLibraryTotalList(paraMap);
		return result;
	}

	@Override
	public NSYLibraryVO getLibraryDetailInfo(String idx) {
		NSYLibraryVO libraryInfo = dao.getLibraryDetailInfo(idx);
		return libraryInfo;
	}

	@Override
	public int editLibraryInfoEnd(NSYLibraryVO libraryvo) {
		int result = dao.editLibraryInfoEnd(libraryvo);
		return result;
	}

	@Override
	public int editLibraryInfoEnd_noImg(NSYLibraryVO libraryvo) {
		int result = dao.editLibraryInfoEnd_noImg(libraryvo);
		return result;
	}


}
