package com.winter.app.aop;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Transfer {
	
	// 핵심로직 (point-cut)
	// 버스이용자
	public void userBus(String number) {
		log.info("버스 타기");
	}
	
	// 핵심로직 (point-cut)
	// 지하철 이용자
	public void userSubway(String number) {
		log.info("지하철 타기");
	}
	
	
}
