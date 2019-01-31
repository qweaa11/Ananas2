package com.spring.bookmanage.library.NSYmodel;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

//==== interDAO 선언하기 ====
@Repository
public interface NSYInterLibraryDAO {
	
	// 도서관등록 완료
	int resisterLibraryEnd(NSYLibraryVO libraryvo); 
	
	// 등록된도서관 목록 가져오기 (페이징, 검색기능 가능)
	List<NSYLibraryVO> getLibraryList(HashMap<String, String>paraMap); 
	
	// 페이징처리를 위한 도서관목록갯수 처리
	int getLibraryTotalList(HashMap<String, String> paraMap); 
	
	// 특정도서관 상세정보 가져오기
	NSYLibraryVO getLibraryDetailInfo(String idx); 
	
	// 특정도서관 정보 수정 하기(이미지파일이 있는 경우)
	int editLibraryInfoEnd(NSYLibraryVO libraryvo); 
	
	// 특정도서관 정보 수정 하기(이미지파일이 없는 경우)
	int editLibraryInfoEnd_noImg(NSYLibraryVO libraryvo); 


}
