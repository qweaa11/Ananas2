package com.spring.schedule.KGBservice;

import java.util.List;

import java.util.HashMap;

public interface InterKGBScheduleService {

	/**
	 * 연체 되어있는 회원의 데이터를 알아오는 메소드
	 * @return
	 */
	List<HashMap<String, String>> findAllRentalDeadlineOver();

}
