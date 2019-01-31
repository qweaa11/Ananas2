package com.spring.schedule.KGBservice;

import java.util.List;

import java.util.HashMap;

public interface InterKGBScheduleService {

	List<HashMap<String, String>> findAllRentalDeadlineOver();
	// 연체 되어있는 회원의 아이디를 알아오는 메소드

}
