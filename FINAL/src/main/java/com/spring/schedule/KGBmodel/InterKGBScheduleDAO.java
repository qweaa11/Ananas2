package com.spring.schedule.KGBmodel;

import java.util.HashMap;
import java.util.List;

public interface InterKGBScheduleDAO {

	List<HashMap<String, String>> findAllRentalDeadlineOver();
	// 연체 되어있는 회원의 아이디를 알아오는 메소드

}
