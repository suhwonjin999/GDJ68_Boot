package com.winter.app.board;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString  // 참조변수를 찍게되면 내부적으로 toString 메서드를 호출함. 메서드의 결과물이 참조변수가 가리키는 주소이다.
public class BoardVO {
	
	private Long boardNo;
	
	private String boardTitle;
	
	private String boardWriter;
	
	private String boardContents;
	
	private Date boardDate;
	
	private Long boardHit;

}
