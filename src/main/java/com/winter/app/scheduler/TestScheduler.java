package com.winter.app.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.winter.app.board.BoardVO;
import com.winter.app.board.notice.NoticeDAO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestScheduler {
	
	@Autowired
	NoticeDAO noticeDAO;
	
	/** 이 클래스 내 메서드가 특정한 주기로 반복 실행함
	 * 지속적으로 반복하는 것은 너무 많은 자원을 소비하는 것이기 때문에 부하가 많이 걸림
	 * 이때문에 Delay() 와 Rate() 메서드를 잘 사용하지 않는다.
	*/
	// 특정 주기로 반복 (1초 지연하다 == 1초마다 한번씩 반복하라.)
	// 메인 컨트롤러에 @EnableScheduling 어노테이션을 선언해야 함.
	// 서버가 실행된 후 2초 뒤에 실행하라.
//	@Scheduled(fixedDelay = 1000, initialDelay = 2000)
	public void useFixedDelay() throws Exception{
		
		log.info("========== Fixed Schedule Delay =============");
		
//		Object obj = new Object();
		// wait() 메서드를 만나면 기다렸다가 NOTIFile을 만나면 다시 깨어나서 Runnable(실행가능한 상태)로 간다.
//		obj.wait();
		// 실행상태에서 sleep() 메서드를 만나면 2초동안 잠들다가 다시 runnable 상태로 간다.
//		Thread.sleep(2000);
		
		
	}
	
	
	// 값을 줄 때 LONG 타입으로 주느냐, Spring 타입으로 주느냐 타입만 다르지, 다른건 다 동일하다.
//	@Scheduled(fixedRateString = "2000", initialDelayString = "1000") // fixedRate=2000 와 동일한 코드이다.
	public void userFixRate() throws Exception{
		
		log.info("========== Fixed Rate =============");
		// 해당 메서드가 언제 종료되는지 상관없다.
		// 메서드의 실행하고 종료할 때 까지 4초가 걸린다고 가정하자. 
		// 2초 간격으로 실행하라 명령어를 내리지만, 내용을 실행할 때까지 4초가 걸린다고 가정해보자.
		// 메서드 실행 간격이 4초가 걸리던 8초가 걸리던 상관없이 2초 실행됨.
		// initialDelayString = "1000"  (서버가 실행된 후 1초 뒤에 실행하라.)
	}
	
	// 0초 50분 시간마다 매일 매월마다 실행하라. (서버시간 기준) // "0 50 * * * *"
	// 10초 간격으로 해줘
	@Scheduled(cron = "*/10 * * * * *")
	public void useCron() throws Exception{
		
		log.info("========== 10초 지날때마다 스케줄 만들어줘 ============");
		Pager pager = new Pager();
		List<BoardVO> ar = noticeDAO.getList(pager);
		log.info("List {}:"+ar);
	
	}
	
	
	
	
	

}
