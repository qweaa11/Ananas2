package com.spring.bookmanage.message.YSWmodel;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface InterYSWMessageDAO {

	List<HashMap<String, String>> findBasicInfo();

}
