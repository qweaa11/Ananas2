package com.spring.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;

import com.spring.bookmanage.common.AES256;
import com.spring.schedule.KGBservice.InterKGBScheduleService;

@Component
@EnableScheduling
public class Scheduled {
	
	@Autowired
	InterKGBScheduleService service;
	
	@Autowired
	AES256 aes;
	
	@org.springframework.scheduling.annotation.Scheduled(cron = "30 15 19 * * *")
    public void deadlineOverMailSubmit(){
        
		List<HashMap<String, String>> memberidList = service.findAllRentalDeadlineOver();
		
		for(HashMap<String, String> memberid : memberidList) {
			
			String email = memberid.get("EMAIL");
			
			try {
				memberid.put("EMAIL", aes.decrypt(email) );
			} catch (UnsupportedEncodingException | GeneralSecurityException e) {
				e.printStackTrace();
			}
			
		}// end of for----------------------------------
		
		List<HashMap<String, String>> lastMapList = new ArrayList<HashMap<String, String>>();
		
		for(HashMap<String, String> memberMap : memberidList) {
			
			int count = 0;
			
			for(HashMap<String, String> lastMap : lastMapList) {
				
				if(memberMap.get("MEMBERID").equals(lastMap.get("MEMBERID"))) {
					count ++;
					lastMap.put("TITLE", lastMap.get("TITLE") + "," + memberMap.get("TITLE"));
				}
				
			}// end of for 내부 -----------
			
			if(count == 0) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("MEMBERID", memberMap.get("MEMBERID"));
				map.put("TITLE", memberMap.get("TITLE"));
				map.put("NAME", memberMap.get("NAME"));
				map.put("EMAIL", memberMap.get("EMAIL"));
				
				lastMapList.add(map);
			}
			
		}// end of for 외부------------------
		
		for(HashMap<String, String> lastMap : lastMapList) {
			GoogleMail mail = new GoogleMail();
			
			try {
				mail.sendmail(lastMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
    }// end of deadlineOverMailSubmit()----------------------------------------
	
}
