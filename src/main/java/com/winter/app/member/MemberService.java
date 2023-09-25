package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MemberService {

	// DAO 
	@Autowired
	private MemberDAO memberDAO;
	
	// login
	public MemberVO getLogin(MemberVO memberVO) throws Exception {
		// getMember : username으로 조회를 한다.
		MemberVO loginVO = memberDAO.getMember(memberVO);
		
		// 아이디가 일치하지 않는다면
		if(loginVO == null) {
			return loginVO;
		}
		
		// 아이디가 일치하는 경우 패스워드 일치하는지 확인
		if(loginVO.getPassword().equals(memberVO.getPassword())) {
			return loginVO;
		}
		
		return null; // 로그인이 성공하지 않는다면 null이 리턴된다.
	}
	
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
	
}
