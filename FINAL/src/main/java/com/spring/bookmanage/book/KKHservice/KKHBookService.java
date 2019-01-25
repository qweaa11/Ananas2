package com.spring.bookmanage.book.KKHservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.book.KKHmodel.InterKKHBookDAO;
import com.spring.bookmanage.book.KKHmodel.KKHBookVO;

@Service
public class KKHBookService implements InterKKHBookService{
	
	@Autowired
	private InterKKHBookDAO bookdao;
	
	@Override
	public List<HashMap<String, String>> findAllLibrary(HashMap<String,String> libcode) {
		List<HashMap<String,String>> libraryList = bookdao.findAllLibrary(libcode);
		return libraryList;
	}
	@Override
	public List<HashMap<String, String>> findAllLanguage(HashMap<String,String> libcode) {
		List<HashMap<String,String>> languageList = bookdao.findAllLanguage(libcode);
		return languageList;
	}
	@Override
	public List<HashMap<String, String>> findAllCategory(HashMap<String,String> libcode) {
		List<HashMap<String,String>> categoryList = bookdao.findAllCategory(libcode);
		return categoryList;
	}
	@Override
	public List<HashMap<String, String>> findAllField(HashMap<String,String> libcode) {
		List<HashMap<String,String>> fieldList = bookdao.findAllField(libcode);
		return fieldList;
	}
	
	
	@Override
	public List<KKHBookVO> findBookBysidebar(HashMap<String, Object> parameterMap) {
		List<KKHBookVO> bookList = bookdao.findBookBysidebar(parameterMap);
		return bookList;
	}

	@Override
	public List<KKHBookVO> findBookBySearchbar(HashMap<String, String> parameterMap) {
		List<KKHBookVO> bookList = bookdao.findBookBysearchbar(parameterMap);
		return bookList;
	}

	@Override
	public List<KKHBookVO> findBookDetail(String bookid) {
		List<KKHBookVO> bookDetail = bookdao.findBookDetail(bookid);
		return bookDetail;
	}

	@Override
	public List<HashMap<String,String>> findBookReservateList(String bookid) {
		List<HashMap<String,String>> bookReservateList = bookdao.findBookReservateList(bookid);
		return bookReservateList;
	}
	@Override
	public List<HashMap<String, String>> findcategory() {
		List<HashMap<String,String>> categoryList = bookdao.findcategory();
		return categoryList;
	}
	@Override
	public List<HashMap<String, String>> findlanguage() {
		List<HashMap<String,String>> languageList = bookdao.findlanguage();
		return languageList;
	}
	@Override
	public List<HashMap<String, String>> findgenre() {
		List<HashMap<String,String>> genreList = bookdao.findgenre();
		return genreList;
	}
	@Override
	public List<HashMap<String, String>> findfield() {
		List<HashMap<String,String>> fieldList = bookdao.findfield();
		return fieldList;
	}
	@Override
	public List<HashMap<String, String>> findDetailField(String bigfcode) {
		List<HashMap<String,String>> detailFieldList = bookdao.findDetailField(bigfcode);
		return detailFieldList;
	}
	@Override
	public KKHBookVO findOneBook(String bookid) {
		KKHBookVO book = bookdao.findOneBook(bookid);
		return book;
	}
	

	

}
