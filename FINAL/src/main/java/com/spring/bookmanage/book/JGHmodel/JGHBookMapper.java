package com.spring.bookmanage.book.JGHmodel;

import java.util.HashMap;
import java.util.List;

public interface JGHBookMapper {

	/**
	 * 검색한 삭제도서 목록 조회(페이지네이션 구현)
	 * @param parameterMap
	 * @return
	 */
	List<DeleteBookVO> findAllDeleteBookWithPagination(HashMap<String, String> parameterMap);

	/**
	 * 검색조건에 따른 삭제된 도서목록 조회
	 * 페이지네이션 미구현
	 * @param parameterMap
	 * @return
	 */
	List<DeleteBookVO> findAllDeleteBookBySearchOption(HashMap<String, String> parameterMap);

	/**
	 * 삭제도서 목록 전체조회(삭제도서목록 진입시 AND 검색조건 없을때)
	 * @return
	 */
	List<DeleteBookVO> findAllDeleteBook();

	/**
	 * 해당하는 삭제도서목록 조회
	 * @param delidArray
	 * @return
	 */
	List<DeleteBookVO> findAllDeleteBookByDelid(String[] delidArray);
	/**
	 * 도서 추가
	 * @param delidArray
	 * @return
	 */
	int addSetBook(String[] delidArray, List<DeleteBookVO> bookSetList) throws Throwable;

	/**
	 * 삭제도서 삭제
	 * @param delidArray
	 * @return
	 */
	int deleteSetDeleteBook(String[] delidArray);
}