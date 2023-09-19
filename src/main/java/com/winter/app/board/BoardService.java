package com.winter.app.board;

import java.util.List;

import com.winter.app.commons.Pager;

	public interface BoardService {
	
	public List<BoardVO> getList(Pager pager) throws Exception;
	
	/** 부모 인터페이스에 고대로 집어넣으면, 상속받은 애가 고대로 가져갈거 아냐. */
	public int add(BoardVO boardVO) throws Exception;

}
