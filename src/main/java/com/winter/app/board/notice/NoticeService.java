package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.commons.Pager;

/** Impl : implement 라는 뜻이다. 
 *  NoticeService 클래스.
*/

@Service
@Transactional(rollbackFor = Exception.class) // 모든 메서드에 트랜젝션 적용됨.
public class NoticeService implements BoardService {
	
	// NoticeDAO를 인젝션시킴.
		@Autowired
		private NoticeDAO noticeDAO;

		@Override
		public List<BoardVO> getList(Pager pager) throws Exception {
			// TODO Auto-generated method stub
			return noticeDAO.getList(pager);
		}

		@Override
		// 예외가 발생했을 때 ROLLBACK 하게 되도록 트랜젝션을 적용시켜줌.
		// @Transactional(rollbackFor = Exception.class)
		public int add(BoardVO boardVO) throws Exception {
			// TODO Auto-generated method stub
			return noticeDAO.add(boardVO);
		}
		

}
