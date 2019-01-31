package com.spring.bookmanage.book.YMHmodel;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.bookmanage.common.AES256;

@Repository
public class YMHBookDAO implements InterYMHBookDAO 
{

	@Autowired
	private SqlSessionTemplate sqlsession;
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 첫번째 필드코드를 대분류로 정하면 상세분류 필드코드를 가져오는 메소드
	 * 작성자 유민후
	 *
	 * @param YMHBookVO bookvo
	 * @return List<YMHBookVO>
	 */
	@Override
	public List<YMHBookVO> fieldCodeList(String initFcode) {
		List<YMHBookVO> filedcodelist = sqlsession.selectList("YMH.fieldCodeList", initFcode);
		return filedcodelist;
	}// end of filedCodeList(String initFcode)-------------------------------------------
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	
	/**
	 * 관리자의 경우 어떤 도서관에 등록할지 정할 수 있도록 도서관 명과 도서관코드를 가져오는 메소드
	 * 작성자 유민후
	 *
	 * @param YMHBookVO bookvo
	 * @return List<YMHBookVO>
	 */
	@Override
	public List<YMHBookVO> showLibrary()
	{
		List<YMHBookVO> libraryList = sqlsession.selectList("YMH.showLibrary");
		return libraryList;
	}// end of showLibrary()-------------------------------------------
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	/**
	 * 출판사가 publisher 테이블에 있는지 확인하는 메소드
	 * 작성자 유민후
	 *
	 * @param YMHBookVO bookvo
	 * @return List<YMHBookVO>
	 */
	@Override
	public int isExistPublisher(YMHBookVO bookvo) 
	{
		System.out.println(bookvo.getPubCode_fk() + bookvo.getISBN());
		int n = sqlsession.selectOne("YMH.isExistPublishers", bookvo);
		return n;
	}// end of isExistPublisher(YMHBookVO bookvo)-------------------------------------------
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	/**
	 * publisher 테이블에 insert하는 메소드
	 * 작성자 유민후
	 *
	 * @param YMHBookVO bookvo
	 * @return int
	 */
	@Override
	public int addPublisher(YMHBookVO bookvo) 
	{
		System.out.println("등록번호"+bookvo.getPubCode_fk());
		System.out.println("출판사명"+bookvo.getPubName());
		System.out.println("주소지"+bookvo.getAddr());
		System.out.println("연락처"+bookvo.getTel());
		
		
		
		int n = sqlsession.insert("YMH.addPublisher", bookvo);
		return n;
	}// end of addPublisher(YMHBookVO bookvo)-------------------------------------------
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * book 테이블에 insert하는 메소드
	 * 작성자 유민후
	 *
	 * @param YMHBookVO bookvo
	 * @return int
	 */
	@Override
	public int addOneBook(YMHBookVO bookvo)
	{		
		int n = sqlsession.insert("YMH.addOneBook", bookvo);
		return n;
	}// end of addOneBook(YMHBookVO bookvo)-------------------------------------------
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	/**
	 * book_Detail 테이블에 insert하는 메소드
	 * 작성자 유민후
	 *
	 * @param YMHBookVO bookvo
	 * @return int
	 */
	@Override
	public int addOneBook_detail(YMHBookVO bookvo) {
		int n = sqlsession.insert("YMH.addOneBook_detail", bookvo);
		return n;
	}// end of addOneBook_detail(YMHBookVO bookvo)-------------------------------------------
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	/**
	 * 도서의 시리얼 넘버중 첫번째 - 전까지 알아오는 메소드
	 * 
	 * @author 유민후
	 * @param bookId
	 * @return String 
	 */
	@Override
	public int BookIdN(String bookId) {
		int BookIdN = sqlsession.selectOne("YMH.BookIdN", bookId);
		return BookIdN;
	}// end of BookIdN(String bookId)-------------------------------------------
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	
	
	
	
}











