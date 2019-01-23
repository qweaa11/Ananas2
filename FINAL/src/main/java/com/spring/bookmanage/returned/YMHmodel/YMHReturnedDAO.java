package com.spring.bookmanage.returned.YMHmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class YMHReturnedDAO implements InterYMHReturnedDAO 
{
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	@Override
	public List<ReturnedVO> listReturned(HashMap<String, String> paraMap) {
		List<ReturnedVO> returnedList =  sqlsession.selectList("YMH.listReturned",paraMap);
		return returnedList;
	}

	
	/**
	 * 도서반납(returned)테이블에서 전체 카운트 조회해오기
	 * 작성자 유민후
	 *
	 * @param HashMap<String, String> paraMap
	 * @return int
	 */
	@Override
	public int getReturnedTotalCount(HashMap<String, String> paraMap) {
		int totalCount = sqlsession.selectOne("YMH.getReturnedTotalCount",paraMap);
		return totalCount;
	}


	/**
	 * 도서반납(returned)테이블에서 검색된 정보 조회해오기
	 * 작성자 유민후
	 *
	 * @param HashMap<String, String> paraMap
	 * @return List<ReturnedVO>
	 */
	@Override
	public List<ReturnedVO> listReturnedWithSearch(HashMap<String, String> paraMap) {
		List<ReturnedVO> returnedList =  sqlsession.selectList("YMH.listReturnedWithSearch",paraMap);
		return returnedList;
	}


	/**
	 * 도서반납(returned)테이블에서 검색될 카운트 조회해오기
	 * 작성자 유민후
	 *
	 * @param HashMap<String, String> paraMap
	 * @return int
	 */
	@Override
	public int getReturnedTotalCountWithSearch(HashMap<String, String> paraMap) {
		int totalCount = sqlsession.selectOne("YMH.getReturnedTotalCountWithSearch",paraMap);
		return totalCount;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}























