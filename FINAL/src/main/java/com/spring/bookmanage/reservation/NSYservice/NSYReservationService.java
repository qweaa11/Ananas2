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
	
	//예약목록을 가져오는 기능(페이징 검색 가능)
	@Override
	public List<HashMap<String, Object>> getReservationList(HashMap<String, String> paraMap) {
		List<HashMap<String, Object>> resultList = dao.getReservationList(paraMap);
		return resultList;
	}
	
	// 페이징처리를 위한 총페이지를 알아오는 메소드
	@Override
	public int getReservationTotalList(HashMap<String, String> paraMap) {
		int result = dao.getReservationTotalList(paraMap);
		return result;
	}
	
	// 예약된책을 대여하는 기능
	@Override
	public int reservation_rental(HashMap<String, String> paraMap) {
		int result = dao.reservation_rental(paraMap);
		return result;
	}
	
	// 예약된책을 대여하면서 스테이터값을 대여값 1로 변경
	@Override
	public void changeBookStatus(HashMap<String, String> paraMap) {
		dao.changeBookStatus(paraMap);	
	}
	
	// 예약된책목록을 대여하면서 지워주는 기능
	@Override
	public void deleteReservation(HashMap<String, String> paraMap) {
		dao.deleteReservation(paraMap);	
	}
	
}
