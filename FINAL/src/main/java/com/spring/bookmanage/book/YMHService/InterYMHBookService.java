package com.spring.bookmanage.book.YMHService;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.bookmanage.book.YMHmodel.YMHBookVO;

public interface InterYMHBookService 
{
	/**
	 * 도서관 코드와 이름을 불러오기
	 * 
	 * @author 유민후
	 * @param YMHBookVO bookvo
	 * @return List<YMHBookVO>
	 */
	List<YMHBookVO> showLibrary();
	
	
	/**
	 * 필드 분류 선택을 위한 메소드
	 *
	 * @author 유민후
	 * @param YMHBookVO bookvo
	 * @return List<YMHBookVO>
	 */
	List<YMHBookVO> fieldCodeList(String initFcode);
	
	
	/**
	 * 출판사가 publisher 테이블에 있는지 확인하는 메소드
	 * 
	 * @author 유민후
	 * @param YMHBookVO bookvo
	 * @return List<YMHBookVO>
	 */
	int isExistPublisher(YMHBookVO bookvo);

	
	/**
	 * publisher 테이블에 insert하는 메소드
	 * 
	 * @author 유민후
	 * @param YMHBookVO bookvo
	 * @return int
	 */
	int addPublisher(YMHBookVO bookvo);


	/**
	 * book 테이블에 insert하는 메소드
	 * 
	 * @author 유민후
	 * @param YMHBookVO bookvo
	 * @return int
	 */
	int addOneBook(YMHBookVO bookvo);


	/**
	 * book_Detail 테이블에 insert하는 메소드
	 * 
	 * @author 유민후
	 * @param YMHBookVO bookvo
	 * @return int
	 */
	int addOneBook_detail(YMHBookVO bookvo);


	
	/**
	 * 도서의 시리얼 넘버중 첫번째 - 전까지 알아오는 메소드
	 * 
	 * 'L1000ENF02520SF-1-1' 중에 'L1000ENF02520SF'까지
	 * 
	 * @author 유민후
	 * @param bookId
	 * @return int
	 */
	int getBookIdN(String bookId); 
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

















