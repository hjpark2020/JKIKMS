package com.jkikms.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MovieSchedulerUtil {
	
	// 한시간에 한번 돌아감
	@Scheduled(cron = "0 0 * * * *")
	public void boxOfficUpdateScheduler() {
		// 매정각 어제 날짜의 박스오피스 정보를 우리 디비에서 조회
		// 디비에 있을경우 패스
		// 디비에 없을경우 영화 API를 통해 어제 박스오피스를 가져와 우리 디비에 저장
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE,-1);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		System.out.println(format.format(calendar.getTime()));
				
		
	}
}
