package com.spring.bookmanage.book.KKHmodel;

import java.util.HashMap;
import java.util.List;

public interface InterKKHBookDAO {
	
	//DB에 있는 도서관정보와 해당 도서관에 있는 책수를 가져오는 메소드
	List<HashMap<String, String>> findAllLibrary(HashMap<String,String> libcode);

	//DB에 있는 모든 언어(Language테이블)정보와 해당 언어를 가진 책 갯수를 가져오는 메소드
	List<HashMap<String, String>> findAllLanguage(HashMap<String,String> libcode);

	//DB에 있는 모든 종류(Category 테이블)정보와 해당 종류를 가진 책 갯수를 가져오는 메소드
	List<HashMap<String, String>> findAllCategory(HashMap<String,String> libcode);

	//DB에 있는 모든 분야(field 테이블) 정보와 해당 분야를 가진 책 갯수를 가져오는 메소드
	List<HashMap<String, String>> findAllField(HashMap<String,String> libcode);
	
	//사이드바에서 넘긴 값들을 파라미터에 담고,  담긴 LIBRARY, CATEGORY, FIELD, LANGUAGE,SORT,LIBCODE(사서 접속시) 값을 이용해 도서 리스트를 검색하는 메소드
	List<KKHBookVO> findBookBysidebar(HashMap<String, Object> parameterMap);
	
	//검색창에서 입력한 정보(searchType,searchWord,sort) 들을 사용해 도서리스트를 검색하는 메소드
	List<KKHBookVO> findBookBysearchbar(HashMap<String, String> parameterMap);

	/*도서 상세로 가는 메소드 리스트 인 이유는 해당 도서번호 'L1000KRE02530UN-1' 를 가진 모든 책들을 가져오기 때문*/
	List<KKHBookVO> findBookDetail(String bookid);

	//도서 상세 페이지에 있는 책중 예약된 책들의 예약정보리스트를 가져오는 메소드, 이때 bookid 는 두번째 하이푼(-)이전 까지의 코드이다. ex)L1000KRE02530UN-1
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
	 * 새로 부여할 bookid 의 큰번호를 채번해오는 메소드
	 * ex)L1000KRE02530UN-1  <<-- 요 1이 큰번호임
	 * @param newBookid
	 * @return newBookid1stNum
	 */
	String findNewBook1stNum(String newBookid);
 
	
	/**
	 * book_detail 테이블에서 수정할 도서의 정보(intro,price,weight 등등)을 가져온뒤 삭제하는 메소드
	 * @param bookid
	 * @return List
	 */
	List<KKHBookVO> selectAndDelBookDetail(String bookid);

	/**
	 * book 테이블의 bookid 를 새롭게 부여해주는 메소드
	 * @param parameterMap HashMap
	 * @return List
	 */
	int updateNewBookid(HashMap<String, String> parameterMap);
	
	/**
	 * book_detail 테이블에 저장해두었던 정보와 새롭게 부여한 bookid 값을 insert해주는 메소드
	 * @param bookDetailList List[KKHBookVO]
	 */
	void insertNewBookDetail(List<KKHBookVO> bookDetailList);
	
	/**
	 * 이미지 파일을 변경했을경우 book_detail 테이블의 image 컬럼을 update해주는 메소드
	 * @param parameterMap
	 * @return
	 */
	int updateNewBookDetail(HashMap<String, String> parameterMap);

	/**
	 * 수정하려는 도서들의 book 테이블의 정보를 update해준다.
	 * @param parameterMap
	 * @return
	 */
	int updateBookInfo(HashMap<String, String> parameterMap);
	
	/**
	 * 이미지 파일을 변경했을경우 book_detail 테이블의 image컬럼값을 update 해준다.
	 * @param parameterMap
	 * @return
	 */
	int updateBookDetail(HashMap<String, String> parameterMap);

	/**
	 * 개별 도서정보를 수정하는 메소드
	 * 이때 ISBN은 book 테이블에 있기때문에 따로 update 해준다.
	 * @param parameterMap
	 * @return
	 */
	int editIndivBookInfo(HashMap<String, String> parameterMap);

	//개별 도서를 테이블에서 삭제하는 메소드
	int deleteIndivBook(String bookid);
	
	/**
	 * 추가될 도서의 시작 일련번호를 채번해오는 메소드
	 * @param bookid String
	 * @return int
	 */
	int findStartBookNum(String bookid);

	/**
	 * 추가시킬 도서에 입력될 도서정보를 해당 도서번호를 가진 책들중 첫	번째책에서 가져오는 메소드
	 * @param bookid String
	 * @return KKHBookVO
	 */
	KKHBookVO findBookInfoSample(String bookid);

	/**
	 * 추가할 도서객수만큼 도서를 book, book_detail 테이블에 insert해주는 메소드
	 * @param bookInfoSample KKHBookVO
	 * @param parameterMap HashMap
	 * @return int
	 */
	int insertAdditionalBook(KKHBookVO bookInfoSample, HashMap<String, String> parameterMap);

	/**
	 * 삭제할 도서의 정보를 VO에 저장하는 메소드
	 * @param bookid
	 * @return List[KKHBookVO]
	 */
	List<KKHBookVO> findDeleteBook(String bookid);

	/**
	 * book, book_detail 테이블에서 해당 도서번호를 가진 모든 책을 delete 하고
	 * delete_book 테이블에 insert 해준다.
	 * @param deleteBookList[KKHBookVO]
	 * @param bookid String
	 * @param cleanerid String
	 * @return int
	 */
	int insertDelete_BookList(List<KKHBookVO> deleteBookList,String cleanerid);

	/**
	 * 해동 도서번호  [ ex)L1000KRE02530UN-1 큰번호까지 ] 를 가진 모든 책을 
	 * book, book_detail 테이블에서 delete 해주는 메소드
	 * @param bookid String
	 * @return int
	 */
	int deleteBookAndBookDetail(String bookid);

	int updateDeadline(String extendBookid);

	HashMap<String, String> findRentalBook(String returnBookid);

	int insertReturnedBook(HashMap<String, String> rentalBookInfo);

	int updateReturnedBookStatus(HashMap<String, String> rentalBookInfo);

	int updateLateMemberInfo(HashMap<String, String> rentalBookInfo);

	int deleteRentalBook(String returnBookid);

	int reserveCancel(String cancelBookid);



	



}
