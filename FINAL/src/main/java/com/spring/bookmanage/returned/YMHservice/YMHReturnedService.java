package com.spring.bookmanage.returned.YMHservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.bookmanage.returned.YMHmodel.InterYMHReturnedDAO;
import com.spring.bookmanage.returned.YMHmodel.ReturnedVO;

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
	}
	
	
	
	
}
































