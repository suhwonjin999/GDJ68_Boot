package com.winter.app.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FileVO {

	// PK 위한 파일명
	private Long fileNum;
	
	private String fileName;
	
	// 원본파일명
	private String oriName;
}
