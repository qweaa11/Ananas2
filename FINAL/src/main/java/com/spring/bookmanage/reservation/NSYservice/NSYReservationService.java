package com.spring.bookmanage.reservation.NSYservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.reservation.NSYmodel.NSYInterReservationDAO;

@Service
public class NSYReservationService implements NSYInterReservationService{
	
	@Autowired
	private NSYInterReservationDAO dao;

	@Override
	public List<HashMap<String, Object>> getReservationList(HashMap<String, String> paraMap) {
		List<HashMap<String, Object>> resultList = dao.getReservationList(paraMap);
		return resultList;
	}

	@Override
	public int getReservationTotalList(HashMap<String, String> paraMap) {
		int result = dao.getReservationTotalList(paraMap);
		return result;
	}
	
}
