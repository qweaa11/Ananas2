package com.spring.bookmanage.book.KKHmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class KKHBookDAO implements InterKKHBookDAO {
	
	
	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public List<KKHBookVO> findBookBysidebar(HashMap<String, Object> parameterMap) {
		//System.out.println("library=>"+parameterMap.get("LIBRARY")+",  language=>"+parameterMap.get("LANGUAGE")+",  category=>"+parameterMap.get("CATEGORY")+",  field=>"+parameterMap.get("FIELD"));
		List<KKHBookVO> bookList = sqlsession.selectList("KKH.findBookBysidebar", parameterMap);
		
		return bookList;
	}

	
	@Override
	public List<KKHBookVO> findBookBysearchbar(HashMap<String, String> parameterMap) {
		List<KKHBookVO> bookList = sqlsession.selectList("KKH.findBookBysearchbar", parameterMap);
		return bookList;
	}


	@Override
	public List<KKHBookVO> findBookDetail(String bookid) {
		List<KKHBookVO> bookDetail = sqlsession.selectList("KKH.findBookDetail", bookid);
		return bookDetail;
	}  

}
