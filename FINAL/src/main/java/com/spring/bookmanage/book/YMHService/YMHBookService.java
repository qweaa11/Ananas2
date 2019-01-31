package com.spring.bookmanage.book.YMHService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.bookmanage.book.YMHmodel.InterYMHBookDAO;
import com.spring.bookmanage.book.YMHmodel.YMHBookVO;
import com.spring.bookmanage.common.AES256;

@Service
public class YMHBookService implements InterYMHBookService 
{

	@Autowired
	private InterYMHBookDAO dao;
	
	@Autowired
	private AES256 ase;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	
	
	/**
	 * 출판사 이름과 코드 불러오기
	 * 
	 * @author 유민후
	 * @param String initFcode (분류코드의 제일 첫자리)
	 * @return List<YMHBookVO>
	 */
	@Override
	public List<YMHBookVO> fieldCodeList(String initFcode) {
		List<YMHBookVO> fieldcodelist = dao.fieldCodeList(initFcode);
		
		return fieldcodelist;
	}
	
	
	/**
	 * 도서관 이름과 코드 불러오기
	 * 
	 * @author 유민후
	 * @param 
	 * @return List<YMHBookVO>
	 */
	@Override
	public List<YMHBookVO> showLibrary() 
	{
		List<YMHBookVO> libraryList = dao.showLibrary();
		
		return libraryList;
	}


	
	/**
	 * 출판사가 publisher 테이블에 있는지 확인하는 메소드
	 * 
	 * @author 유민후
	 * @param YMHBookVO bookvo
	 * @return List<YMHBookVO>
	 */
	@Override
	public int isExistPublisher(YMHBookVO bookvo) {
		int n = dao.isExistPublisher(bookvo);
		return n;
	}


	/**
	 * publisher 테이블에 insert하는 메소드
	 * 
	 * @author 유민후
	 * @param YMHBookVO bookvo
	 * @return int
	 */
	@Override
	public int addPublisher(YMHBookVO bookvo) {
		int n = dao.addPublisher(bookvo);
		return n;
	}


	
	/**
	 * book 테이블에 insert하는 메소드
	 * 
	 * @author 유민후
	 * @param YMHBookVO bookvo
	 * @return int
	 */
	@Override
	public int addOneBook(YMHBookVO bookvo) {
		int n = dao.addOneBook(bookvo);
		return n;
	}


	/**
	 * book_Detail 테이블에 insert하는 메소드
	 * 
	 * @author 유민후
	 * @param YMHBookVO bookvo
	 * @return int
	 */
	@Override
	public int addOneBook_detail(YMHBookVO bookvo) {
		int n = dao.addOneBook_detail(bookvo);
		return n;
	}



	/**
	 * 도서의 시리얼 넘버중 첫번째 - 전까지 알아오는 메소드
	 * 
	 * @author 유민후
	 * @param bookId
	 * @return String 
	 */
	@Override
	public int getBookIdN(String bookId) {
		int n = dao.BookIdN(bookId);
		return n;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}














