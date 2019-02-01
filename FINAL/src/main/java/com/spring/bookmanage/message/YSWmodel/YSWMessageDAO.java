package com.spring.bookmanage.message.YSWmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class YSWMessageDAO implements InterYSWMessageDAO {
	
	@Autowired
	private SqlSessionTemplate sqlsession;

	
	@Override
	public List<HashMap<String, String>> findBasicInfo() {

		List<HashMap<String, String>> basicInfoList = sqlsession.selectList("YSW.findBasicInfo");
		return basicInfoList;
	}

}
