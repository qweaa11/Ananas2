package com.spring.bookmanage.book.JGHmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JGHBookDAO implements JGHBookMapper {

	@Autowired private SqlSessionTemplate sqlsession;

	@Override
	public List<DeleteBookVO> findAllDeleteBookWithPagination(HashMap<String, String> parameterMap) {
		List<DeleteBookVO> deleteBookList = sqlsession.selectList("jghDeleteBook.findAllDeleteBookWithPagination", parameterMap);

		return deleteBookList;
	}// end of findAllDeleteBookWithPagination

	@Override
	public List<DeleteBookVO> findAllDeleteBookBySearchOption(HashMap<String, String> parameterMap) {
		List<DeleteBookVO> deleteBookList = sqlsession.selectList("jghBook.findAllDeleteBookBySearchOption", parameterMap);

		return deleteBookList;
	}// end of findAllDeleteBookBySearchOption

	@Override
	public List<DeleteBookVO> findAllDeleteBook() {
		List<DeleteBookVO> deleteBookList = sqlsession.selectList("jghBook.findAllDeleteBook");

		return deleteBookList;
	}// end of findAllDeleteBook
}