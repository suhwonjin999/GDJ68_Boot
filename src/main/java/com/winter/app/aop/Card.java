package com.winter.app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect   // 설정
public class Card {
	
	// advice(공통로직)
	// 언제 실행될 것인가 
	// 리턴 타입 상관없고, Transfer 클래스 내 매개변수가 없는 모든 메서드를 before 전에 실행하여라. 
	// 타기 전에 / 탄 후에 공통로직이 실행됨.
	//@Before("execution(* com.winter.app.aop.Transfer.*())")
	//@After("execution(* com.winter.app.aop.Transfer.*())")
//	public void cardCheck() throws Exception{
//		
//		log.info("=======================");
//		log.info("Card Check 타기");

//		log.info("=======================");
//		
//	}
	
	
	@Around("execution(* com.winter.app.aop.Transfer.*())")
	public Object cardCheck(ProceedingJoinPoint joinPoint) throws Throwable{
		
		log.info("=======================");
		log.info("Card Check 타기");
		
		Object [] args = joinPoint.getArgs();
		log.info("Args : {}", args);
		
		// joinPoint 실행하기
		// 예외객체보다 더 상위인 부모인 Throwable를 리턴하여 예외처리 해야함. 
		Object obj = joinPoint.proceed();
		
		log.info("Card Check 내리기");
		log.info("=======================");
		
		return obj;
	}

}
