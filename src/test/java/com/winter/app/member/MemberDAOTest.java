package com.winter.app.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class MemberDAOTest {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	//@Test
	void test() throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("admin");
		
		memberVO = memberDAO.getMember(memberVO);
		
		log.info("Member : {}", memberVO);
		
		// memberVO가 NULL이 아니었으면 좋겠다.
		assertNotNull(memberVO);
		
	}
	
	void test1() throws Exception{
		String pass = passwordEncoder.encode("admin");
		
		// 평문 admin 을 암호화시킨 결과물을 출력해보기 
		// 단방향 : 한번 바뀐 것은 다시 바뀌지 않음.
		// 패스워드가 평문이 아닌 인코딩된 문자열로 들어가있어야 한다.
		// UPDATE MEMBER SET PASSWORD='암호화비번' WHERE USERNAME='ADMIN';
		log.info("pass"+pass);  // DB에 비밀번호 암호화시키기
	
	}

}
