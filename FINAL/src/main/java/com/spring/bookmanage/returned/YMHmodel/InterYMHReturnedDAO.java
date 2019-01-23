package com.spring.bookmanage.returned.YMHmodel;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface InterYMHReturnedDAO
{
	/**
	 * 도서반납(returned)테이블에서 정보 조회해오기
	 * 작성자 유민후
	 *
	 * @param HashMap<String, String> paraMap
	 * @return List<ReturnedVO>
	 */
	List<ReturnedVO> listReturned(HashMap<String, String> paraMap);

	/**
	 * 도서반납(returned)테이블에서 전체 카운트 조회해오기
	 * 작성자 유민후
	 *
	 * @param HashMap<String, String> paraMap
	 * @return int
	 */
	int getReturnedTotalCount(HashMap<String, String> paraMap);

	
	/**
	 * 도서반납(returned)테이블에서 검색된 정보 조회해오기
	 * 작성자 유민후
	 *
	 * @param HashMap<String, String> paraMap
	 * @return List<ReturnedVO>
	 */
	List<ReturnedVO> listReturnedWithSearch(HashMap<String, String> paraMap);

	/**
	 * 도서반납(returned)테이블에서 검색될 카운트 조회해오기
	 * 작성자 유민후
	 *
	 * @param HashMap<String, String> paraMap
	 * @return int
	 */
	int getReturnedTotalCountWithSearch(HashMap<String, String> paraMap);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}



























