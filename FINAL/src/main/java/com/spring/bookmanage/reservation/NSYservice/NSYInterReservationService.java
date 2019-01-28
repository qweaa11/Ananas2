package com.spring.bookmanage.reservation.NSYservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public interface NSYInterReservationService {

	List<HashMap<String, Object>> getReservationList(HashMap<String, String> paraMap); //예약목록을 가져오는 기능(페이징 검색 가능)

	int getReservationTotalList(HashMap<String, String> paraMap);// 페이징처리를 위한 총페이지를 알아오는 메소드

	int reservation_rental(HashMap<String, String> paraMap);// 예약된책을 대여하는 기능

	void changBookStatus(HashMap<String, String> paraMap);// 예약된책을 대여하면서 스테이터값을 대여값 1로 변경

	void deleteReservation(HashMap<String, String> paraMap);// 예약된책목록을 대여하면서 지워주는 기능

}
