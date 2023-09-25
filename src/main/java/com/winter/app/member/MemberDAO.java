package com.winter.app.member;

import org.apache.ibatis.annotations.Mapper;

// DAO : InterFace
@Mapper
public interface MemberDAO {
	
	public MemberVO getMember(MemberVO memberVO) throws Exception;

}
