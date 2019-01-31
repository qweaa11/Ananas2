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
	
	// 예약목록 가져오기
	@Override
	public List<HashMap<String, Object>> getReservationList(HashMap<String, String> paraMap) {
		List<HashMap<String, Object>> resultList = sqlsession.selectList("NSYReservation.getReservationList",paraMap);
		return resultList;
	}
	
	// 예약모록 페이지방 생성을 위해 예약목록 총갯수 구해오기
	@Override
	public int getReservationTotalList(HashMap<String, String> paraMap) {
		int result = sqlsession.selectOne("NSYReservation.getReservationTotalList",paraMap);
		return result;
	}
	
	// 예약목록에서 대여시, 대여목록에 추가해주기
	@Override
	public int reservation_rental(HashMap<String, String> paraMap) {
		int result = sqlsession.insert("NSYReservation.reservation_rental",paraMap);
		return result;
	}
	
	// 예약목록에서 도서 대여시, 해당 예약목록을 지워주는 기능
	@Override
	public void changBookStatus(HashMap<String, String> paraMap) {
		sqlsession.update("NSYReservation.changBookStatus",paraMap);
		
	}
	
	// 예약목록에서 도서 대여시, 해당 예약목록을 지워주는 기능
	@Override
	public void deleteReservation(HashMap<String, String> paraMap) {
		sqlsession.delete("NSYReservation.deleteReservation",paraMap);
		
	}
	
}
