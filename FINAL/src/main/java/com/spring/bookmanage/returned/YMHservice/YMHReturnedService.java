package com.spring.bookmanage.returned.YMHservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.returned.YMHmodel.InterYMHReturnedDAO;
import com.spring.bookmanage.returned.YMHmodel.ReturnedVO;

@Service
public class YMHReturnedService implements InterYMHReturnedService 
{
	@Autowired
	private InterYMHReturnedDAO dao;
	
	
	/**
	 * 도서반납(returned)테이블에서 정보 조회해오기
	 * 작성자 유민후
	 *
	 * @param HashMap<String, String> paraMap
	 * @return List<ReturnedVO>
	 */
	@Override
	public List<ReturnedVO> listReturned(HashMap<String, String> paraMap) {
		List<ReturnedVO> returnedList = dao.listReturned(paraMap);
		return returnedList;
	}// end of listReturned


	/**
	 * 도서반납(returned)테이블에서 전체 카운트 조회해오기
	 * 작성자 유민후
	 *
	 * @param HashMap<String, String> paraMap
	 * @return int
	 */
	@Override
	public int getReturnedTotalCount(HashMap<String, String> paraMap) {
		int totalCount = dao.getReturnedTotalCount(paraMap);
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
		List<ReturnedVO> returnedList = dao.listReturnedWithSearch(paraMap);
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
		int totalCount = dao.getReturnedTotalCountWithSearch(paraMap);
		return totalCount;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}