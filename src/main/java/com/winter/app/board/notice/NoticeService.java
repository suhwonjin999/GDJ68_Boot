package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.commons.FileManager;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

/** Impl : implement 라는 뜻이다. 
 *  NoticeService 클래스.
*/

@Service
@Slf4j  // 로그기록을 찍기 위해 사용하는 어노테이션
public class NoticeService implements BoardService {
	
	// NoticeDAO를 인젝션시킴.
		@Autowired
		private NoticeDAO noticeDAO;
		
		@Autowired
		private FileManager fileManager;

		// spEL : 스프링에서 사용하는 EL 언어 {프로퍼티스 파일의 key를 사용함.}
		// properties 에 있는 값을 JAVA에서 사용하기 위해 value라는 이름에 properties의 키를 적는다. 
		@Value("${app.upload}")
		private String uploadPath;
		
		@Value("${app.board.notice}")
		private String boardName;
		
		@Override
		public List<BoardVO> getList(Pager pager) throws Exception {
			// TODO Auto-generated method stub
			return noticeDAO.getList(pager);
		}

//		@Override
//		public int add(BoardVO boardVO) throws Exception {
//			// TODO Auto-generated method stub
//			return noticeDAO.add(boardVO);
//		}
		
		@Override
		public int add(BoardVO boardVO, MultipartFile[] files) throws Exception {
			// TODO Auto-generated method stub
//			log.info("====================");
//			log.info("uploadPath: {}", uploadPath);
//			log.info("====================");
			
			log.info("BoardNo: {}", boardVO.getBoardNo()); // insert 쿼리 실행 전 INFO
			int result = noticeDAO.add(boardVO);  // boardNum이 생성됨.
			log.info("=======================");
			log.info("BoardNo: {}", boardVO.getBoardNo());  // // insert 쿼리 실행 힌 후 INFO
			
			// 업로드시 apload 폴더에 사진 자동 저장됨
			for(MultipartFile multipartFile:files) {
				
				// 파일이 비어있다면 다시 위로 올라감. (비어있지 않다면 아래 코드를 실행시킴)
				if(multipartFile.isEmpty()) {
					continue;
				}
				
				NoticeFileVO fileVO = new NoticeFileVO();
				String fileName = fileManager.save(this.uploadPath+this.boardName, multipartFile);
				fileVO.setBoardNo(boardVO.getBoardNo());
				fileVO.setFileName(fileName);
				fileVO.setOriName(multipartFile.getOriginalFilename());
				result = noticeDAO.fileAdd(fileVO);
			}
			
			return result;
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
