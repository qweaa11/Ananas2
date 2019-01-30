package com.spring.schedule.KGBmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleDAO implements InterKGBScheduleDAO {
	
	@Autowired
	SqlSessionTemplate sqlsession;

	
	@Override
	public List<HashMap<String, String>> findAllRentalDeadlineOver() {
		
		List<HashMap<String, String>> memberidList = sqlsession.selectList("kgb.findAllRentalDeadlineOver");
		
		return memberidList;
		
	}// end of findAllRentalDeadlineOver()-----------------------------
	
}
