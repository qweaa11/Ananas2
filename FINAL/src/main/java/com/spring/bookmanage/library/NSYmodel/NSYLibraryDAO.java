package com.spring.bookmanage.library.NSYmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//==== DAO 선언하기 ====
@Repository
public class NSYLibraryDAO implements NSYInterLibraryDAO{

	//===== 의존객체 주입하기(DI : Dependency Injection) ===== 
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	// 도서관등록 완료
	@Override
	public int resisterLibraryEnd(NSYLibraryVO libraryvo) {
		int result = sqlsession.insert("NSYLibrary.resisterLibraryEnd", libraryvo);
		return result;
	}
	
	// 등록된도서관 목록 가져오기 (페이징, 검색기능 가능)
	@Override
	public List<NSYLibraryVO> getLibraryList(HashMap<String, String>paraMap) {
		List<NSYLibraryVO> libraryList = sqlsession.selectList("NSYLibrary.getLibraryList",paraMap);
		return libraryList;
	}
	
	// 페이징처리를 위한 도서관목록갯수 처리
	@Override
	public int getLibraryTotalList(HashMap<String, String> paraMap) {
		int result = sqlsession.selectOne("NSYLibrary.getLibraryTotalList",paraMap);
		return result;
	}
	
	// 특정도서관 상세정보 가져오기
	@Override
	public NSYLibraryVO getLibraryDetailInfo(String idx) {
		NSYLibraryVO libraryInfo = sqlsession.selectOne("NSYLibrary.getLibraryDetailInfo",idx);
		return libraryInfo;
	}
	
	// 특정도서관 정보 수정 하기(이미지파일이 있는 경우)
	@Override
	public int editLibraryInfoEnd(NSYLibraryVO libraryvo) {
		int result = sqlsession.update("NSYLibrary.editLibraryInfoEnd", libraryvo);
		return result;
	}
	
	// 특정도서관 정보 수정 하기(이미지파일이 없는 경우)
	@Override
	public int editLibraryInfoEnd_noImg(NSYLibraryVO libraryvo) {
		int result = sqlsession.update("NSYLibrary.editLibraryInfoEnd_noImg", libraryvo);
		return result;
	}

}
