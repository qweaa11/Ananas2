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
	public List<HashMap<String, String>> findAllLibrary(HashMap<String,String> libcode) {
		List<HashMap<String,String>> libraryList = sqlsession.selectList("KKH.findAllLibrary",libcode);
		return libraryList;
	}  
	@Override
	public List<HashMap<String, String>> findAllLanguage(HashMap<String,String> libcode) {
		List<HashMap<String,String>> languageList = sqlsession.selectList("KKH.findAllLanguage",libcode);
		return languageList;
	}

	@Override
	public List<HashMap<String, String>> findAllCategory(HashMap<String,String> libcode) {
		List<HashMap<String,String>> categoryList = sqlsession.selectList("KKH.findAllCategory",libcode);
		return categoryList;
	}

	@Override
	public List<HashMap<String, String>> findAllField(HashMap<String,String> libcode) {
		List<HashMap<String,String>> fieldList = sqlsession.selectList("KKH.findAllField",libcode);
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
	@Override
	public List<HashMap<String, String>> findDetailField(String bigfcode) {
		List<HashMap<String,String>> detailFieldList = sqlsession.selectList("KKH.findDetailField", bigfcode);
		return detailFieldList;
	}
	@Override
	public KKHBookVO findOneBook(String bookid) {
		KKHBookVO book = sqlsession.selectOne("KKH.findOneBook", bookid);
		return book;
	}

	


	

}
