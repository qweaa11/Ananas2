package com.spring.bookmanage.book.KKHservice;

import java.util.HashMap;
import java.util.List;

import com.spring.bookmanage.book.KKHmodel.KKHBookVO;

public interface InterKKHBookService {

	List<KKHBookVO> findBookBysidebar(HashMap<String, Object> parameterMap);

	List<KKHBookVO> findBookBySearchbar(HashMap<String, String> parameterMap);

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

	int editBookPlzChangeBookid(HashMap<String, String> parameterMap, KKHBookVO book);

	int eidtBookNoChangeBookid(HashMap<String, String> parameterMap, KKHBookVO book);

	int editIndivBookInfo(HashMap<String, String> parameterMap);

	int deleteIndivBook(String bookid);

	KKHBookVO findBookInfoSample(String bookid);

	int insertAdditionalBook(KKHBookVO bookInfoSample, HashMap<String, String> parameterMap);

	int findStartBookNum(String bookid);

	List<KKHBookVO> findDeleteBook(String bookid);

	int insertAndDeleteBookList(List<KKHBookVO> deleteBookList,String bookid,String cleanerid);

}
