package com.spring.bookmanage.reservation.NSYmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NSYReservationDAO implements NSYInterReservationDAO{
	
	//DI주입
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	//예약목록을 가져오는 기능(페이징 검색 가능)
	@Override
	public List<HashMap<String, Object>> getReservationList(HashMap<String, String> paraMap) {
		List<HashMap<String, Object>> resultList = sqlsession.selectList("NSYReservation.getReservationList",paraMap);
		
		return resultList;
	}
	
	// 페이징처리를 위한 총페이지를 알아오는 메소드
	@Override
	public int getReservationTotalList(HashMap<String, String> paraMap) {
		int result = sqlsession.selectOne("NSYReservation.getReservationTotalList",paraMap);
		return result;
	}
	
	// 예약된책을 대여하는 기능
	@Override
	public int reservation_rental(HashMap<String, String> paraMap) {
		int result = sqlsession.insert("NSYReservation.reservation_rental",paraMap);
		return result;
	}
	
	// 예약된책을 대여하면서 스테이터값을 대여값 1로 변경
	@Override
	public void changeBookStatus(HashMap<String, String> paraMap) {
		sqlsession.update("NSYReservation.changeBookStatus",paraMap);
		
	}

	// 예약된책목록을 대여하면서 지워주는 기능
	@Override
	public void deleteReservation(HashMap<String, String> paraMap) {
		sqlsession.delete("NSYReservation.deleteReservation",paraMap);
		
	}
	
}
