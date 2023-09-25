package com.winter.app.member;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// 상속관계 명시
public class MemberVO extends MemberInfoVO {

// 하나의 변수에 어노테이션은 여러개 줄 수 있다.
// username 은 비어있으면 안된다 (NotBlank)
// 2글자 이상 12글자 미만 (message: 기본)
	@NotBlank
	@Size(max = 12, min =2, message = "2글자 이상 12글자 미만 입력해주세요")
	private String username;
	
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\\\W)(?=\\\\S+$).{6,12}", message="올바른 비번을 입력해주세요.")
    private String password;
    
    private String passwordCheck;

    // 포함관계 명시
/**    
    private MemberInfoVO memberInfoVO;
    
    public MemberVO() {
    	this.memberInfoVO  = new MemberInfoVO();
    }
*/
    
}
