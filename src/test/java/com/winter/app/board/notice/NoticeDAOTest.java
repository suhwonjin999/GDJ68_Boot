package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.board.BoardVO;
import com.winter.app.commons.Pager;

@SpringBootTest
class NoticeDAOTest {
	
	@Autowired
	private NoticeDAO noticeDAO;

	//@Test
/*
	void addTest() throws Exception{
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardTitle("tetle");
		boardVO.setBoardWriter("winter");
		boardVO.setBoardContents("contents");
		int result = noticeDAO.add(boardVO);
		assertEquals(1, result);  // 0이라면 성공하지않음, 1이라면 insert 성공했음.
	}
*/	
	/** NOTICE 테이블에 자동으로 150개씩 INSERT 하는지 테스트 함. */
	//@Test
	void addTest() throws Exception{
		for(int i=0; i<150; i++) {
			BoardVO boardVO = new BoardVO();
			boardVO.setBoardTitle("title" +i);
			boardVO.setBoardWriter("winter"+i);
			boardVO.setBoardContents("contents"+i);
			noticeDAO.add(boardVO);	 
			if(i%10==0) {
				// 10개씩 데이터가 INSERT 된 후 5초 휴식 후 다시 반복작업하라는 명령어.
				Thread.sleep(500);
			}
		}
		System.out.println("finish");
	}
	
	@Test
	void getCountTest() throws Exception{
		Pager pager = new Pager();
		pager.setKind("1");
		pager.setSearch("20");
		Long count = noticeDAO.getCount(pager);
		// count가 2개 출력되는 게 맞는지 확인
		assertEquals(2L, count);
		
	}
	
	
	// DAO에 있는 메섣를 가지고 와서 테스트할거야.
	// 접근지정자: 없으면 Default 이다.
	// @Test
	void getListTest() throws Exception {
		Pager pager = new Pager();
		pager.setStartRow(0L);
		pager.setLastRow(15L);
		pager.setKind("1");
		pager.setSearch("20");
		
		List<BoardVO> ar = noticeDAO.getList(pager);
		// ar에서 size()를 꺼냈을 때 0과 같지 않았으면 좋겠어.
		// assertNotEquals(0, ar.size());
		assertEquals(2, ar.size());
	}

}
