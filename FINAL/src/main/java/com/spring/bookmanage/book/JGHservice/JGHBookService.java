package com.spring.bookmanage.book.JGHservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.book.JGHmodel.DeleteBookVO;
import com.spring.bookmanage.book.JGHmodel.JGHBookMapper;

@Service
public class JGHBookService {

	@Autowired private JGHBookMapper mapper;

	public int countDeleteBookWithSearchOption(HashMap<String, String> parameterMap) {

		return 0;
	}// end of countDeleteBookWithSearchOption

	public int countDeleteBookWithOutSearchOption() {

		return 0;
	}// end of countDeleteBookWithOutSearhOption

	/**
	 * 검색조건에 따른 삭제도서 목록 불러오기(페이징 미구현)
	 * @param parameterMap
	 * @return
	 */
	public List<DeleteBookVO> searchList(HashMap<String, String> parameterMap) {
		List<DeleteBookVO> deleteBookListWithSearch = mapper.findAllDeleteBookBySearchOption(parameterMap);

		return deleteBookListWithSearch;
	}// end of searchList

	/**
	 * 검색조건 없이 삭제도서 목록 불러오기(페이징 미구현)
	 * @return
	 */
	public List<DeleteBookVO> noSearchList() {
		List<DeleteBookVO> deleteBookListWithOutSearch = mapper.findAllDeleteBook();

		return deleteBookListWithOutSearch;
	}// end of noSearchList
}