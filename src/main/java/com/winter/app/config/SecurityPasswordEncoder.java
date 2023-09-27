package com.winter.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration  // 설정파일이다 란 뜻의 어노테이션이다.
public class SecurityPasswordEncoder {

	/** Bean(객체) 생성하는 클래스를 따로 사용함. */
	
	// 평문으로 작성된 비밀번호를 암호화 값으로 작업해야 함.
	@Bean
	PasswordEncoder passwordEncoder() {
		// 어떤 객체를 가지고 암호화할 것인가
		return new BCryptPasswordEncoder();
	}
	
	
}
