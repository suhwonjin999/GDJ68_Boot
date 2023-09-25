package com.winter.app.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// 상속관계 명시
public class MemberVO extends MemberInfoVO implements UserDetails {

// 하나의 변수에 어노테이션은 여러개 줄 수 있다.
// username 은 비어있으면 안된다 (NotBlank)
// 2글자 이상 12글자 미만 (message: 기본)
	@NotBlank
	@Size(max = 12, min =2, message = "2글자 이상 12글자 미만 입력해주세요")
	private String username;
	
	//@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\\\W)(?=\\\\S+$).{6,12}", message="올바른 비번을 입력해주세요.")
    private String password;
    
    private String passwordCheck;
    
    // 여러개 RoleVO 타입을 가지고 있다.
    private List<RoleVO> roleVOs;
    
    

    /** 인터페이스를 implements 시킨 후, 메서드를 오버라이드시켜야 함.*/
    /** 리턴 타입은 무조건 true 해줘야 함. false 이면 로그인 실패라 뜬다.*/
    
    /** 어떤 타입인지는 모르겠으나 GrantedAuthority 를 상속받은 자식타입이면 된다.
     *  인터페이스 : LIST, SET 의 부모 : Collection 타입 
     *  Collection 을 구현하는 애 : LSIT, SET
    */
    
    // getAuthorities 메서드를 호춣하면 권한 정보를 가지고온다. 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		// roleVOs에서 roleVO 하나씩 꺼내면 객체가 나올것이다.
		for(RoleVO roleVO:roleVOs) {
			// list에 추가하는 메서드: add()
			authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
		}
		
		return authorities;
	}
	

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

    // 포함관계 명시
/**    
    private MemberInfoVO memberInfoVO;
    
    public MemberVO() {
    	this.memberInfoVO  = new MemberInfoVO();
    }
*/
    
    
    
    
}
