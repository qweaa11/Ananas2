package com.spring.bookmanage.message.YSWservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface InterYSWMessageService {

	List<HashMap<String, String>> findBasicInfo();
}
