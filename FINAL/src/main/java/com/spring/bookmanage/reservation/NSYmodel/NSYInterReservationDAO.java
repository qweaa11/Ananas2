package com.spring.bookmanage.reservation.NSYmodel;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface NSYInterReservationDAO {

	List<HashMap<String, Object>> getReservationList(HashMap<String, String> paraMap);

	int getReservationTotalList(HashMap<String, String> paraMap);
	
}
