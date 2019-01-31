package com.spring.bookmanage.library.NSYservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.library.NSYmodel.NSYInterLibraryDAO;
import com.spring.bookmanage.library.NSYmodel.NSYLibraryVO;

//===== Service 선언  =====
@Service
public class NSYLibraryService implements NSYInterLibraryService{
	
	//===== 의존객체 주입하기(DI : Dependency Injection) =====
    @Autowired
	private NSYInterLibraryDAO dao;
    
    // 도서관등록 완료하기
	@Override
	public int resisterLibraryEnd(NSYLibraryVO libraryvo) {
		
		int result = dao.resisterLibraryEnd(libraryvo);
		
		return result;
	}
	
	// 등록된도서관 목록 가져오기 (페이징, 검색기능 가능)
	@Override
	public List<NSYLibraryVO> getLibraryList(HashMap<String, String>paraMap) {
		List<NSYLibraryVO> libraryList = dao.getLibraryList(paraMap);
		return libraryList;
	}
	
	// 페이징처리를 위한 도서관목록갯수 처리
	@Override
	public int getLibraryTotalList(HashMap<String, String> paraMap) {
		int result = dao.getLibraryTotalList(paraMap);
		return result;
	}
	
	// 특정도서관 상세정보 가져오기
	@Override
	public NSYLibraryVO getLibraryDetailInfo(String idx) {
		NSYLibraryVO libraryInfo = dao.getLibraryDetailInfo(idx);
		return libraryInfo;
	}
	
	// 특정도서관 정보 수정 하기(이미지파일이 있는 경우)
	@Override
	public int editLibraryInfoEnd(NSYLibraryVO libraryvo) {
		int result = dao.editLibraryInfoEnd(libraryvo);
		return result;
	}
	
	// 특정도서관 정보 수정 하기(이미지파일이 없는 경우)
	@Override
	public int editLibraryInfoEnd_noImg(NSYLibraryVO libraryvo) {
		int result = dao.editLibraryInfoEnd_noImg(libraryvo);
		return result;
	}


}
