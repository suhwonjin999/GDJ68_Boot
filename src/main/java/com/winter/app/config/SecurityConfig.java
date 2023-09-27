package com.winter.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration.ProviderDetails.UserInfoEndpoint;
import org.springframework.security.web.SecurityFilterChain;

import com.winter.app.member.MemberService;

@Configuration
@EnableWebSecurity  // 웹 시큐리티 설정을 통해 가져가겠다.
public class SecurityConfig {
	
//	@Autowired
//	private SecuritySuccessHandler handler;
	
	@Autowired
	private MemberService memberService;
	

	// API 이기 때문에 클래스 선언할 수 없어서 어노테이션을 이용하여 객체 생성한다.
	@Bean // 객체를 만들어서 Spring FULL에 등록하는 어노테이션 
	// 프론트 자원만 시큐리티 적용 안되게 통과시키는 메서드 
	WebSecurityCustomizer webSecurityCustomizer() {
	
		return web -> web
				// 이런 URL이 매칭되는 것을 무시하라(통과시켜라)
				// 폴더 포함하여 css 밑으로 들어오는 모든 URL은 통과시켜라
				.ignoring()
				.antMatchers("/css/**")
				// 메서드를 체인으로 연결하는 형식
				.antMatchers("/img/**")
				.antMatchers("/js/**")
				.antMatchers("/vendor/**")
				;
	}
	
	// 백앤드에서 로그인 처리 
	// 메서드명은 상관없다. 메서드 타입이 중요하다.
	// Bean이라고 되어있는 메서드를 호출하면, 리턴으로 WebSecurityCustomizer가 객체를 받아 Spring Full에 등록한다.
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		// 매개변수로 받은 HttpSecurity 를 이용하여 설정함.
		httpSecurity
		// 외부 API 요청시 받는 에러
			.cors()
			.and()
			.csrf()
			// 실행하지 않겠다 (이 정책을 무시하겠다. CORS 정책과 CSRF 정책을 허용하겠다.)
			.disable()
			// 권한을 가진다.
			.authorizeHttpRequests()
				// 인증된 사람(로그인한 사람들)만 notice/add 경로에 접속되는 것을 허용하겠다
	//			.antMatchers("/notice/add").authenticated()
				// ADMIN 계정을 가지고 있는 사람만 접속이 가능하다.
				.antMatchers("/notice/add").hasRole("ADMIN") // ROLE_NDMIN에서 ROLE_ 제외함
				// 둘 중 하나의 계정만 있으면 이 사이트에 접속이 가능하다.
				.antMatchers("/manager/*").hasAnyRole("ADMIN","MANAGER")
	//			.antMatchers("/manager/*").access("hasRole('ROLE_ADMIN')")
				// 루트로 시작하는 경로는 전부 다 허용하겠다. (누구든지 사용가능함)
				.antMatchers("/").permitAll()
				.and()
			//form 관련 설정
			.formLogin()
				// 내장된 로그인폼을 사용하지 않고 개발자가 만든 폼을 사용하겠다 선언함 
			    // 메서드 형식이 post 일 때, 시큐리티에서 해당 요청을 가로챈다.
				.loginPage("/member/login")
				.defaultSuccessUrl("/")
				.failureUrl("/member/login")
				.permitAll()  // 로그인창까지 가는 경로를 누구나 가도록 허용하겠다.
				.and()
			.logout()
				// 어떤 URL 주소가 들어왔을 때 로그아웃 시키는가
				.logoutUrl("/member/logout")
				
				// 로그아웃 성공했을 때 어디 경로로 가는가
//				.logoutSuccessUrl("/")
// 메서드를 만들어서 생성된 객체를 받아오자.				
				.addLogoutHandler(getLogoutAdd())
				.logoutSuccessHandler(null)
				
				// 세션을 true를 주면 강제종료 시키겠다.
				.invalidateHttpSession(true)
				// 쿠키 지워줌
				.deleteCookies("JSESSIONID") 
				.and()
			.rememberMe()
				.tokenValiditySeconds(60)
// properties 에 등록하고 key로 등록되는 변수값을 가져오는 방법도 있다.
				.key("rememberKey")
				.userDetailsService(memberService)
//				.authenticationSuccessHandler(handler)
				.and()
//			.oauth2Login()
//				.userInfoEndpoint()
//				.userService()
//				.and()
				
			.sessionManagement()
			;
		
		return httpSecurity.build();
	}
	
// 로그아웃 할 때 로그아웃한 유저의 정보도 받아올 수 있다.	
	private ScurityLogoutAdd getLogoutAdd() {
		return new SecurityLogoutAdd();
	}
	
	
}
