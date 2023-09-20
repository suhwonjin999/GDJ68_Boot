package com.winter.app.board.notice;

import com.winter.app.board.FileVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class NoticeFileVO extends FileVO {

	// 어느 글에 대한 파일인가에 대한 FK 
	// 글을 써야지 만들어짐.(글이 등록이 되어야지 번호가 만들어짐)
	private Long boardNo;
	
}
