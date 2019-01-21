package com.spring.bookmanage.rental.YSWmodel;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class YSWRentalDAO implements InterYSWRentalDAO {
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	// 대출 목록 페이지 보여주기
	@Override
	public List<YSWRentalVO> findRentalList() {
		
		List<YSWRentalVO> rentalList = sqlsession.selectList("YSW.findRentalList");
		return rentalList;
	}



}
