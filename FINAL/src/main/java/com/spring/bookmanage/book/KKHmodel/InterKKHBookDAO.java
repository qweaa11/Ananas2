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

}
