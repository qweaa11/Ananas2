package com.spring.bookmanage.book.KKHservice;

import java.util.HashMap;
import java.util.List;

import com.spring.bookmanage.book.KKHmodel.KKHBookVO;

public interface InterKKHBookService {

	//DB에 있는 도서관정보와 해당 도서관에 있는 책수를 가져오는 메소드
	List<HashMap<String, String>> findAllLibrary(HashMap<String,String> libcode);

	//DB에 있는 모든 언어(Language테이블)정보와 해당 언어를 가진 책 갯수를 가져오는 메소드
	List<HashMap<String, String>> findAllLanguage(HashMap<String,String> libcode);

	//DB에 있는 모든 종류(Category 테이블)정보와 해당 종류를 가진 책 갯수를 가져오는 메소드
	List<HashMap<String, String>> findAllCategory(HashMap<String,String> libcode);
 
	//DB에 있는 모든 분야(field 테이블) 정보와 해당 분야를 가진 책 갯수를 가져오는 메소드
	List<HashMap<String, String>> findAllField(HashMap<String,String> libcode);
	
	/**
	 * 사이드바에서 넘긴 값들을 파라미터에 담고,  담긴 LIBRARY, CATEGORY, FIELD, LANGUAGE,SORT,LIBCODE(사서 접속시) 값을 이용해 도서 리스트를 검색하는 메소드 
	 * @param parameterMap(LIBRARY,CATEGORY,FIELD,LANGUAGE,SORT,LIBCODE)
	 * @return List<KKHBookVO> 
	 */
	//사이드바에서 넘긴 값들을 파라미터에 담고,  담긴 LIBRARY, CATEGORY, FIELD, LANGUAGE,SORT,LIBCODE(사서 접속시) 값을 이용해 도서 리스트를 검색하는 메소드
	List<KKHBookVO> findBookBysidebar(HashMap<String, Object> parameterMap);

	/**
	 * 검색창에서 입력한 정보(searchType,searchWord,sort) 들을 사용해 도서리스트를 검색하는 메소드
	 * @param parameterMap
	 * @return List<KKHBookVO>
	 */
	//검색창에서 입력한 정보(searchType,searchWord,sort) 들을 사용해 도서리스트를 검색하는 메소드
	List<KKHBookVO> findBookBySearchbar(HashMap<String, String> parameterMap);

	/**
	 * 도서 상세로 가는 메소드 리스트 인 이유는 해당 도서번호 'L1000KRE02530UN-1' 를 가진 모든 책들을 가져오기 때문
	 * ex)L1000KRE02530UN-1-1,L1000KRE02530UN-1-2,L1000KRE02530UN-1-3 등등의 정보들을 가져옴
	 * @param bookid
	 * @return List<KKHBookVO>
	 */
	/*도서 상세로 가는 메소드 리스트 인 이유는 해당 도서번호 'L1000KRE02530UN-1' 를 가진 모든 책들을 가져오기 때문
	  ex)L1000KRE02530UN-1-1,L1000KRE02530UN-1-2,L1000KRE02530UN-1-3 등등의 정보들을 가져옴*/
	List<KKHBookVO> findBookDetail(String bookid);

	
	/**
	 * 도서 상세 페이지에 있는 책중 예약된 책들의 예약정보리스트를 가져오는 메소드
	 * 이때 bookid 는 두번째 하이푼(-)이전 까지의 코드이다.
	 * ex)L1000KRE02530UN-1
	 * @param bookid String
	 * @return List 
	 */
	List<HashMap<String,String>> findBookReservateList(String bookid);

	
	//도서 수정을 위한 항목별 정보 가져오기 시작
		List<HashMap<String, String>> findcategory();

		List<HashMap<String, String>> findlanguage();

		List<HashMap<String, String>> findgenre();

		//분야별 큰 항목(000, 100, 200,.....900)을 가져오는 메소드
		List<HashMap<String, String>> findfield();
		
		//큰 항목에 따른 분야별 작은 항목(110,120,130,....190)등을 가져오는 메소드
		List<HashMap<String, String>> findDetailField(String bigfcode);
	//도서 수정을 위한 항목별 정보 가져오기 끝
		
	//도서 수정을 위해 변경할 정보와 비교하기 위한 기존정보를 가져오는 메소드
	KKHBookVO findOneBook(String bookid);

	/**
	 * 도서 공용정보를 수정할때 새롭게 도서일련번호를 부여해야 할시 실행하는 메소드 
	 * @param parameterMap HashMap<String,String>
	 * @param book KKHBookVO
	 * @return int
	 */
	int editBookPlzChangeBookid(HashMap<String, String> parameterMap, KKHBookVO book);

	/**
	 * 도서 공용정보 수정시 도서일련번호 변경없이 도서정보를 수정할때 실행하는 메소드
	 * @param parameterMap HashMap
	 * @param book KKHBookVO
	 * @return int
	 */
	int eidtBookNoChangeBookid(HashMap<String, String> parameterMap, KKHBookVO book);

	int editIndivBookInfo(HashMap<String, String> parameterMap);

	int deleteIndivBook(String bookid);

	KKHBookVO findBookInfoSample(String bookid);

	int insertAdditionalBook(KKHBookVO bookInfoSample, HashMap<String, String> parameterMap);

	int findStartBookNum(String bookid);

	List<KKHBookVO> findDeleteBook(String bookid);

	int insertAndDeleteBookList(List<KKHBookVO> deleteBookList,String bookid,String cleanerid);

	String[] updateDeadline(String[] extendBookArr);

	int updateDeadline(String extendBookid);

	String[] returnBook(String[] returnBookidArr);

	int returnBook(String returnBookid);

	int reserveCancel(String cancelBookid);

}
