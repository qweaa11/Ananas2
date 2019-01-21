package com.spring.bookmanage.book.KKHmodel;

import java.util.HashMap;
import java.util.List;

public interface InterKKHBookDAO {
	

	List<KKHBookVO> findBookBysidebar(HashMap<String, Object> parameterMap);
	
	List<KKHBookVO> findBookBysearchbar(HashMap<String, String> parameterMap);

	List<KKHBookVO> findBookDetail(String bookid);

}
