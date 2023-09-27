package com.winter.app.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService extends DefaultOAuth2UserService implements UserDetailsService {

	// DAO 
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// Social Login
	// 로그인하여 사용자 정보를 userRequest에 담아서 가져온다.
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO Auto-generated method stub
		ClientRegistration clientRegistration = userRequest.getClientRegistration();
		// clientRegistration에 provider 의 정보를 담고있다. 
		log.info("===== {} =======", clientRegistration);
		log.info("===== Social Login 처리 진행 ======");
		
		// super : 부모의 메서드 (DefaultOAuth2UserService 안에 있는 메서드를 의미함.)
		// super 를 제외시킬 수 있지만, 자기 자신을 호출하면 무한반복으로 실행됨.
//		OAuth2User auth2User = loadUser(userRequest);
		// 실제 로그인한 사용자의 정보는 super.loadUser(userRequest)에 담고있다.		
		OAuth2User auth2User = super.loadUser(userRequest);
		
		log.info("===== Auth2User : {} =======", auth2User);
		
		// social 에 카카오가 들어감. (카카오 로그인의 경우에만)
		String social = clientRegistration.getReqistrationId();
		if(social.equals("kakao")) {
			auth2User = this.forKakao(auth2User);
		}
		
		return auth2User; // 실제 유저 정보
	}
	
	// MemberVO 가 UserDetail 타입도 되고, OAuth2User 타입도 된다. 
	private OAuth2User forKakao(OAuth2User auth2User){
		MemberVO memberVO = new MemberVO();
//		memberVO.setUsername();
		// getClass() : 리턴 타입을 모를 경우, 클래스명이 무엇인지 확인
		Object obj = auth2User.getAttribute("properties").getClass();
		log.info("1***** {} ***** " , obj);
		LinkedHashMap<String, Object> map = auth2User.getAttribute("properties");
		log.info("===== {} ===== " , auth2User.getAttribute("properties").toString());
		
		LinkedHashMap<String, Object> kakaoAccount = auth2User.getAttribute("kakao_account");
		LinkedHashMap<String, Object> profile = auth2User.getAttribute("kakao_account");
		
		profile = (LinkedHashMap<String, Object>)profile.get("profile");

		log.info("2**** {} **** ", profile);
		log.info("=== NickName : {} ===", profile.get("nickname"));
		log.info("=== ProfileImage : {} ===", profile.get("profile_image_url"));
		log.info("=== Email : {} ===", kakaoAccount.get("email"));
		log.info("=== Birth : {} ===", kakaoAccount.get("birthday"));
		// 최종목적지 : profile을 꺼내어 닉네임 정보를 알 수 있다.
		
		String birth = kakaoAccount.get("birthday").toString();
		// 시작인덱스 번호 이상, 끝 인덱스번호 미만
		String month = birth.substring(0, 2);
		String day = birth.substring(2);
		
		Calendar ca = Calendar.getInstance();
		// 날짜를 다루는 클래스는 Calendar 이다.
		int y = ca.get(Calendar.YEAR);
		StringBuffer sb = new StringBuffer();
//		sb.append(y).append("-").append(month).append("-").append(day);
		sb.append(y);
		sb.append("-");
		sb.append(month);
		sb.append("-");
		sb.append(day);
		
		// LinkedHashMap<String, Object> 타입이면 toString() 하여 문자열 타입으로 변경해줘야 한다.
		// LinkedHashMap<String, String> 타입이면 toString() 해줄 필요 없다.
		memberVO.setUsername(map.get("nickname").toString());
		memberVO.setEmail(kakaoAccount.get("email").toString());
		
		memberVO.setAttributes(auth2User.getAttributes());
		
		List<RoleVO> list = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleName("ROLE_MEMBER");
		
		list.add(roleVO);
		
		memberVO.setRoleVOs(list);
		
// String 타입을 Date 타입으로 변환시켜야 함. (구분자를 이용하여 생년월일을 집어넣자. // 년도없이 월일만 받았음 // Age를 받으면 년도가 계산됨.// 년도 아무거나 넣어도 상관없음)
		log.info("Date : {}", Date.valueOf(kakaoAccount.get("birthday").toString()));
		log.info("Date : {}", Date.valueOf("09-09")); //null
		log.info("==== Date : {} ====", Date.valueOf(sb.toString()));
//		memberVO.setBirth(kakaoAccount.get("birthday"));
		
		
		
		return memberVO;
	}
	
	
	
	
	// login
//	public MemberVO getLogin(MemberVO memberVO) throws Exception {
//		// getMember : username으로 조회를 한다.
//		MemberVO loginVO = memberDAO.getMember(memberVO);
//		
//		// 아이디가 일치하지 않는다면
//		if(loginVO == null) {
//			return loginVO;
//		}
//		
//		// 아이디가 일치하는 경우 패스워드 일치하는지 확인
//		if(loginVO.getPassword().equals(memberVO.getPassword())) {
//			return loginVO;
//		}
//		
//		return null; // 로그인이 성공하지 않는다면 null이 리턴된다.
//	}
	
	// 검증메서드 : 검증이 성공 or 실패했는가
	// 검증의 결과를 담는 객체 : BindingResult
	public boolean getMemberError(MemberVO memberVO, BindingResult bindingResult) throws Exception {
		// true인 경우, error가 있다(검증에 실패했다.)
		// false인 경우, error가 없다.
		boolean check=false;
		
		// MemberVO에서 패스워드를 비교
		// password 일치하는지 검증
		// memberVO에서 password를 꺼내서 PasswordCheck과 일치하는지 비교함.
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			check=true;  //check=!check
			
			// 필드명(멤버변수명),코드명(임의지정 이후 메시지 프로퍼티스에 등록해줘야함.)
			bindingResult.rejectValue("passwordCheck", "memberVO.password.equalCheck");;
		}
		
		// ID 중복 검사 (DB에서 조회를 한 결과물이 있으면 중복, 없으면 중복 아님)
		MemberVO checkVO = memberDAO.getMember(memberVO);
		
		if(checkVO != null) {
			check = true;
			bindingResult.rejectValue("username", "memberVO.username.equalCheck");
		}
		
		return check;
	}

	// Spring Security가 요청을 가로채서 UserDetailsService 타입을 만들어서 loadUserByUsername 메서드를 호출한다.
	// 필터 : 디스패쳐 서블렛으로 가기 전
	// 일반 필터 뒤에 위치한다.
	// 파라미터를 userName과 password를 넘겼는데 매개변수로 userName을 꺼내온다.
	// 상속은 하나까지, 구현은 여러개 가능함.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.info("==== 로그인 시도중 ====");
		MemberVO memberVO = new MemberVO();
		
		memberVO.setUsername(username);
		
		try {
			memberVO = memberDAO.getMember(memberVO);			
		}
		catch(Exception e) {
			e.printStackTrace();
			memberVO=null;
		}
		
		// memberVO를 받아서 파라미터에서 꺼내온 패스워드와 DB에서의 패스워드가 일치하는지 검증함.
		// 패스워드가 일치하면 세션에 저장함.
		
		return memberVO;
	}
	
	
	// 회원가입
	// 둘 중 하나라도 exception 이 발생하면 ROLLBACK 시키겠다.
	@Transactional(rollbackFor = Exception.class)
	public int setJoin(MemberVO memberVO) throws Exception{
		
		// memberVO에서 패스워드를 꺼내서 인코딩하여 암호화한 패스워드를 다시 집어넣는다.
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		int result = memberDAO.setJoin(memberVO);
		Map<String, Object> map = new HashMap<>();
		
		// 관리자 계정도, 매니저 계정도 아닌 일반 유저 계정으로 회원가입 진행
		map.put("roleNum", 3);
		map.put("username", memberVO.getUsername());
		
		// member 테이블에도 INSERT 하고, memberRole 테이블에서도 INSERT 한다.
		result = memberDAO.setMemberRole(map);
		
		return result;
	}
	
	
	
}
