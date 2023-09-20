package com.winter.app.board.notice;

import java.util.List;

import com.winter.app.board.BoardVO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NoticeVO extends BoardVO {
	
/** BoardVO(부모객체)를 상속받는다. */	
	
	// NOTICE 하나가 파일 여러개를 가지고 있다. 선언 후 매퍼에서 매핑시킴.(어떻게 매핑시킬거야?)
	private List<NoticeFileVO> list;
	

}
