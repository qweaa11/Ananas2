package com.spring.schedule.JGHscheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 스케줄러
 * @author implements(nine9ash)
 *
 */
@Component
@EnableScheduling
public class JGHScheduler {

	@Autowired private ScheduleMapper mapper;

	/**
	 * 이용정지회원에 정지기간 관리
	 */ /*
	@Scheduled(cron = "0 00 00 * * *")
	public void schedulingRevokeAccount() {
		mapper.schedulingAllMemberByRevokeDate();
	}// end of schedulingRevokeAccount */
}