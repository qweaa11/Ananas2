package com.spring.bookmanage.book.KKHmodel;

import java.util.HashMap;
import java.util.List;

public interface InterKKHBookDAO {
	

	List<KKHBookVO> findBookBysidebar(HashMap<String, Object> parameterMap);
	
	List<KKHBookVO> findBookBysearchbar(HashMap<String, String> parameterMap);

	List<KKHBookVO> findBookDetail(String bookid);

	List<HashMap<String,String>> findBookReservateList(String bookid);

	List<HashMap<String, String>> findAllLibrary(HashMap<String,String> libcode);

	List<HashMap<String, String>> findAllLanguage(HashMap<String,String> libcode);

	List<HashMap<String, String>> findAllCategory(HashMap<String,String> libcode);

	List<HashMap<String, String>> findAllField(HashMap<String,String> libcode);

	List<HashMap<String, String>> findcategory();

	List<HashMap<String, String>> findlanguage();

	List<HashMap<String, String>> findgenre();

	List<HashMap<String, String>> findfield();

	List<HashMap<String, String>> findDetailField(String bigfcode);

	KKHBookVO findOneBook(String bookid);

	String findNewBook1stNum(String newBookid);
 
	int updateNewBookid(HashMap<String, String> parameterMap);
	
	List<KKHBookVO> selectAndDelBookDetail(String bookid);

	
	int updateNewBookDetail(HashMap<String, String> parameterMap);
	
	int updateBookDetail(HashMap<String, String> parameterMap);

	int updateBookInfo(HashMap<String, String> parameterMap);

	void insertNewBookDetail(List<KKHBookVO> bookDetailList);

	int editIndivBookInfo(HashMap<String, String> parameterMap);

	int deleteIndivBook(String bookid);

	KKHBookVO findBookInfoSample(String bookid);

	int findStartBookNum(String bookid);

	int insertAdditionalBook(KKHBookVO bookInfoSample, HashMap<String, String> parameterMap);

	List<KKHBookVO> findDeleteBook(String bookid);

	int insertDelete_BookList(List<KKHBookVO> deleteBookList,String cleanerid);

	int deleteBookAndBookDetail(String bookid);

	int updateDeadline(String extendBookid);

	HashMap<String, String> findRentalBook(String returnBookid);

	int insertReturnedBook(HashMap<String, String> rentalBookInfo);

	int updateReturnedBookStatus(HashMap<String, String> rentalBookInfo);

	int updateLateMemberInfo(HashMap<String, String> rentalBookInfo);

	int deleteRentalBook(String returnBookid);

	int reserveCancel(String cancelBookid);



	



}
