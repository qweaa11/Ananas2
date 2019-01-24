package com.spring.bookmanage.library.JGHservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.library.JGHmodel.JGHLibraryMapper;

@Service
public class JGHLibraryService {
	@Autowired private JGHLibraryMapper mapper;

	/**
	 * 스케줄러 실행
	 */
	public int schedulerRunService() {
		int row = mapper.runScheduler();

		return row;
	}// end of schedulerRunService
}