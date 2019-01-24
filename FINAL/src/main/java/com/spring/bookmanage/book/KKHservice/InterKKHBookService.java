package com.spring.bookmanage.book.KKHservice;

import java.util.HashMap;
import java.util.List;

import com.spring.bookmanage.book.KKHmodel.KKHBookVO;

public interface InterKKHBookService {

	List<KKHBookVO> findBookBysidebar(HashMap<String, Object> parameterMap);

	List<KKHBookVO> findBookBySearchbar(HashMap<String, String> parameterMap);

	List<KKHBookVO> findBookDetail(String bookid);

	List<HashMap<String,String>> findBookReservateList(String bookid);

	List<HashMap<String, String>> findAllLibrary();

	List<HashMap<String, String>> findAllLanguage();

	List<HashMap<String, String>> findAllCategory();

	List<HashMap<String, String>> findAllField();

	List<HashMap<String, String>> findcategory();

	List<HashMap<String, String>> findlanguage();

	List<HashMap<String, String>> findgenre();

	List<HashMap<String, String>> findfield();

	List<HashMap<String, String>> findDetailField(String bigfcode);

}
