package com.spring.bookmanage.book.JGHservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bookmanage.book.JGHmodel.DeleteBookVO;
import com.spring.bookmanage.book.JGHmodel.JGHBookMapper;

/**
 * 삭제도서 서비스(복구)
 * @author implements(nine9ash)
 *
 */
@Service
public class JGHBookService {

	@Autowired private JGHBookMapper mapper;

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

	/**
	 * 삭제도서 복구
	 * @param delidArray
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	public void restoreBookService(String[] delidArray) throws Throwable {
		List<DeleteBookVO> bookSetList = mapper.findAllDeleteBookByDelid(delidArray);
		mapper.restoreBook(bookSetList);

		return ;
	}// end of restoreBookService
}