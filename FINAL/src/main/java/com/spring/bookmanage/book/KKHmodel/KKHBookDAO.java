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
	public List<HashMap<String, String>> findAllLibrary() {
		List<HashMap<String,String>> libraryList = sqlsession.selectList("KKH.findAllLibrary");
		return libraryList;
	}  
	@Override
	public List<HashMap<String, String>> findAllLanguage() {
		List<HashMap<String,String>> languageList = sqlsession.selectList("KKH.findAllLanguage");
		return languageList;
	}

	@Override
	public List<HashMap<String, String>> findAllCategory() {
		List<HashMap<String,String>> categoryList = sqlsession.selectList("KKH.findAllCategory");
		return categoryList;
	}

	@Override
	public List<HashMap<String, String>> findAllField() {
		List<HashMap<String,String>> fieldList = sqlsession.selectList("KKH.findAllField");
		return fieldList;
	}
	
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


	@Override
	public List<HashMap<String,String>> findBookReservateList(String bookid) {
		List<HashMap<String,String>> bookReservateList =  sqlsession.selectList("KKH.findBookReservation",bookid);
		return bookReservateList;
	}
	@Override
	public List<HashMap<String, String>> findcategory() {
		List<HashMap<String,String>> categoryList = sqlsession.selectList("KKH.findCategory");
		return categoryList;
	}
	@Override
	public List<HashMap<String, String>> findlanguage() {
		List<HashMap<String,String>> languageList = sqlsession.selectList("KKH.findLanguage");
		return languageList;
	}
	@Override
	public List<HashMap<String, String>> findgenre() {
		List<HashMap<String,String>> genreList = sqlsession.selectList("KKH.findGenre");
		return genreList;
	}
	@Override
	public List<HashMap<String, String>> findfield() {
		List<HashMap<String,String>> fieldList = sqlsession.selectList("KKH.findField");
		return fieldList;
	}

	


	

}
