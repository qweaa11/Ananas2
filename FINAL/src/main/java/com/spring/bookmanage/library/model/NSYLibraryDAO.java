package com.spring.bookmanage.library.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//==== DAO 선언하기 ====
@Repository
public class NSYLibraryDAO implements NSYInterLibraryDAO{

	//===== #33. 의존객체 주입하기(DI : Dependency Injection) ===== 
	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public int resisterLibraryEnd(NSYLibraryVO libraryvo) {
		int result = sqlsession.insert("NSYLibrary.resisterLibraryEnd", libraryvo);
		return result;
	}

	@Override
	public List<NSYLibraryVO> getLibraryList(HashMap<String, String>paraMap) {
		List<NSYLibraryVO> libraryList = sqlsession.selectList("NSYLibrary.getLibraryList",paraMap);
		return libraryList;
	}

	@Override
	public int getLibraryTotalList(HashMap<String, String> paraMap) {
		int result = sqlsession.selectOne("NSYLibrary.getLibraryTotalList",paraMap);
		return result;
	}

	@Override
	public NSYLibraryVO getLibraryDetailInfo(String idx) {
		NSYLibraryVO libraryInfo = sqlsession.selectOne("NSYLibrary.getLibraryDetailInfo",idx);
		return libraryInfo;
	}

	@Override
	public int editLibraryInfoEnd(NSYLibraryVO libraryvo) {
		int result = sqlsession.update("NSYLibrary.editLibraryInfoEnd", libraryvo);
		return result;
	}

	@Override
	public int editLibraryInfoEnd_noImg(NSYLibraryVO libraryvo) {
		int result = sqlsession.update("NSYLibrary.editLibraryInfoEnd_noImg", libraryvo);
		return result;
	}

}
