package com.winter.app.aop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransferTest {

	@Autowired  // 객체 가져오기
	private Transfer transfer;
	
	@Autowired
	private Card card;
	
// AOP : 메서드가 잘 실행되었는지 확인하고 싶은 경우, 사용함.
	
	@Test
	void test() throws Exception {
		// 핵심 로직만 실행하자.
		
		// 메서드 호출할 때 값을 줘야함.
		//버스, 지하철 타기 전과 내리기 전 카드 찍는 반복 행위를 한다.
//		card.cardCheck();
		transfer.userBus("1000");
//		card.cardCheck();
		
//		card.cardCheck();
		transfer.userSubway("2000");
//		card.cardCheck();
		
	}

}
