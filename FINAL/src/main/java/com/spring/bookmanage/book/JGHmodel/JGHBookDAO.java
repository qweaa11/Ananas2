package com.spring.bookmanage.book.JGHmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 삭제도서 DAO
 * @author implements(nine9ash)
 *
 */
@Repository
public class JGHBookDAO implements JGHBookMapper {

	@Autowired private SqlSessionTemplate sqlsession;

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

	@Override
	public List<DeleteBookVO> findAllDeleteBookByDelid(String[] delidArray) {
		HashMap<String, Object> paraMap = new HashMap<>();
		paraMap.put("delidArray", delidArray);
				
		List<DeleteBookVO> deleteBookListByDelid = sqlsession.selectList("jghBook.findAllDeleteBookByDelid", paraMap);

		return deleteBookListByDelid;
	}// end of findAllDeleteBookByDelid

	@Override
	public void restoreBook(List<DeleteBookVO> bookSetList) {
		for(DeleteBookVO deleteBookVO : bookSetList)
			sqlsession.delete("jghBook.restoreBook", deleteBookVO);
	}// end of restoreBook
}