package com.spring.schedule.KGBservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import com.spring.schedule.KGBmodel.InterKGBScheduleDAO;

@Service
public class KGBScheduleService implements InterKGBScheduleService {
	
	@Autowired
	InterKGBScheduleDAO dao;

	@Override
	public List<HashMap<String, String>> findAllRentalDeadlineOver() {
		
		List<HashMap<String, String>> memberidList = dao.findAllRentalDeadlineOver();
		
		return memberidList;
		
	}// end of findAllRentalDeadlineOver()---------------------
	
}
