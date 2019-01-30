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

	@Override
	public List<DeleteBookVO> findAllDeleteBookByDelid(String[] delidArray) {
		HashMap<String, Object> paraMap = new HashMap<>();
		paraMap.put("delidArray", delidArray);
		List<DeleteBookVO> deleteBookListByDelid = sqlsession.selectList("jgh.findAllDeleteBookByDelid", paraMap);

		return deleteBookListByDelid;
	}// end of findAllDeleteBookByDelid

	@Override
	public int addSetBook(String[] delidArray, List<DeleteBookVO> bookSetList) throws Throwable {
		String delid = "";
		int addRow = 0;
		int addRow2 = 0;
		int transaction = 0;
		
		for(int i=0;i<delidArray.length;i++) {
			for(int j=0;j<bookSetList.get(i).getCount();j++) {
				delid = delidArray[i];
				addRow = sqlsession.insert("jghBook.insertBook", bookSetList.get(i));
				addRow2 = sqlsession.insert("jghBook.insertBookDetail", bookSetList.get(i));
				transaction = addRow*addRow2;

				if(transaction != 1) {
					Throwable rollback = new Throwable("도서복구에 실패했습니다.");
				}// end of if
			}// end of inner for
		}// end of outer for

		return transaction;
	}// end of addSetBook

	@Override
	public int deleteSetDeleteBook(String[] delidArray) {
		String delid = "";
		int row = 0;

		for(int i=0;i<delidArray.length;i++) {
			delid = delidArray[i];
			sqlsession.delete("jghBook.deleteSetDeleteBook", delid);
		}// end of for
		return row;
	}// end of deleteSetDeleteBook
}