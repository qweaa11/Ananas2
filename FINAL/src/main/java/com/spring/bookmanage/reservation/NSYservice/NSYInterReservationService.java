package com.spring.bookmanage.reservation.NSYservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public interface NSYInterReservationService {

	List<HashMap<String, Object>> getReservationList(HashMap<String, String> paraMap);

	int getReservationTotalList(HashMap<String, String> paraMap);

}
