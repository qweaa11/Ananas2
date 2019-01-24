package com.spring.bookmanage.reservation.NSYmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NSYReservationDAO implements NSYInterReservationDAO{
	
	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public List<HashMap<String, Object>> getReservationList(HashMap<String, String> paraMap) {
		List<HashMap<String, Object>> resultList = sqlsession.selectList("NSYReservation.getReservationList",paraMap);
		
		return resultList;
	}

	@Override
	public int getReservationTotalList(HashMap<String, String> paraMap) {
		int result = sqlsession.selectOne("NSYReservation.getReservationTotalList",paraMap);
		return result;
	}
	
}
