package com.winter.app.commons;

import lombok.Getter;
import lombok.Setter;

/** 공통파트의 페이징 처리하는 클래스 */
/* Pager를 상속받아서 또 다른 Pager를 받는다.  
 * Outline : 구조 
*/
@Getter
@Setter
public class Pager {
	
	// Reference 타입의 Long 타입
	// 시작 인덱스번호
	private Long startRow;
	
	// 가져올 개수
	private Long lastRow;
	
	//검색할 컬럼명
	private String kind;
	
	//검색어
	private String search;
	
	// Null 처리위한 GETTER
	public Long getStartRow() {
		
		// startRow가 null이거나 마이너스 값이라면 0으로 설정함.
		if(this.startRow==null || this.startRow<0) {
			return 0L;
		}
		return this.startRow;
	}
	
	
	public Long getlastRow() {
		
		// lastRow가 null이거나 마이너스 값이라면 기본값 10으로 설정함.
		if(this.lastRow==null || this.lastRow<0) {
			return 10L;
		}
		return this.getlastRow();
	}
	
	// 검색어 GETTER
	public String getSearch() {
		// search가 null이라면, 빈 문자열을 리턴하자.
		if(this.search==null) {
			return "";
		}
		return this.search;
	}
	
	
}
