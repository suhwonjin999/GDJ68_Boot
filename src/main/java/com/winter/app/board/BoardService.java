package com.winter.app.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.Pager;

	public interface BoardService {
	
	public List<BoardVO> getList(Pager pager) throws Exception;
	
	/** 부모 인터페이스에 고대로 집어넣으면, 상속받은 애가 고대로 가져갈거 아냐. */
	public int add(BoardVO boardVO, MultipartFile [] files) throws Exception;
	
    public BoardVO getDetail(BoardVO boardVO)throws Exception;
	
	public int setUpdate (BoardVO boardVO)throws Exception;
	
	public int setDelete(BoardVO boardVO)throws Exception;

}
