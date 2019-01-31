package com.spring.schedule.KGBmodel;

import java.util.HashMap;
import java.util.List;

public interface InterKGBScheduleDAO {

	/**
	 * 연체 되어있는 회원의 데이터를 알아오는 메소드
	 * @return
	 */
	List<HashMap<String, String>> findAllRentalDeadlineOver();

}
