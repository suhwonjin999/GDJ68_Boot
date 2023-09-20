package com.winter.app.board;

import java.util.List;

import com.winter.app.commons.Pager;

public interface BoardDAO {
	
	public Long getCount(Pager pager) throws Exception;
	
	public List<BoardVO> getList(Pager pager) throws Exception;
	
	/** 부모 인터페이스에 고대로 집어넣으면, 상속받은 애가 고대로 가져갈거 아냐. */
	public int add(BoardVO boardVO) throws Exception;
	
	public int fileAdd(FileVO fileVO) throws Exception;
	
	public BoardVO getDetail(BoardVO boardVO)throws Exception;
	
	public int setUpdate (BoardVO boardVO)throws Exception;
	
	public int setHitUpdate (BoardVO boardVO)throws Exception;
	
	public int setDelete(BoardVO boardVO)throws Exception;
	
	public FileVO getFileDetail(FileVO fileVO) throws Exception;
	
	
}
