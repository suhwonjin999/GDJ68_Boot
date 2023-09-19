package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.commons.Pager;

/** Impl : implement 라는 뜻이다. 
 *  NoticeService 클래스.
*/

@Service
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
		public int add(BoardVO boardVO) throws Exception {
			// TODO Auto-generated method stub
			return noticeDAO.add(boardVO);
		}
		
		@Override
		public BoardVO getDetail(BoardVO boardVO) throws Exception {
			// TODO Auto-generated method stub
			return noticeDAO.getDetail(boardVO);
		}

		@Override
		public int setUpdate(BoardVO boardVO) throws Exception {
			// TODO Auto-generated method stub
			return noticeDAO.setUpdate(boardVO);
		}

		@Override
		public int setDelete(BoardVO boardVO) throws Exception {
			// TODO Auto-generated method stub
			return noticeDAO.setDelete(boardVO);
		}

}
