package com.spring.bookmanage.reservation.NSYmodel;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface NSYInterReservationDAO {
	//예약목록을 가져오는 기능(페이징 검색 가능)
	List<HashMap<String, Object>> getReservationList(HashMap<String, String> paraMap); 
	
	// 페이징처리를 위한 총페이지를 알아오는 메소드
	int getReservationTotalList(HashMap<String, String> paraMap);
	
	// 예약목록에서 대여시, 대여목록에 추가해주기
	int reservation_rental(HashMap<String, String> paraMap); 
	
	// 예약목록에서 도서 대여시, 도서테이블의 스테이터값을 대여값 1로 변경
	void changBookStatus(HashMap<String, String> paraMap); 
	
	// 예약목록에서 도서 대여시, 해당 예약목록을 지워주는 기능
	void deleteReservation(HashMap<String, String> paraMap); 
	
}
