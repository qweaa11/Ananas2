package com.spring.bookmanage.book.YMHmodel;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Repository
public interface InterYMHBookDAO {

	List<YMHBookVO> fieldCodeList(String initFcode);
	// 필드 분류 선택을 위한 메소드
	
	List<YMHBookVO> showLibrary();
	// 도서관 코드와 이름을 불러오기

	int isExistPublisher(YMHBookVO bookvo);
	// 출판사가 publisher 테이블에 있는지 확인하는 메소드

	int addPublisher(YMHBookVO bookvo);
	// publisher 테이블에 insert하는 메소드

	int addOneBook(YMHBookVO bookvo);
	// book 테이블에 insert하는 메소드

	int addOneBook_detail(YMHBookVO bookvo);
	// book_detail 테이블에 insert하는 메소드

	int BookIdN(String bookId);
	// 도서의 시리얼 넘버중 첫번째 - 전까지 알아오는 메소드
	
	
	
}
