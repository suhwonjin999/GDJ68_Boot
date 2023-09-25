package com.winter.app.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService implements UserDetailsService {

	// DAO 
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
