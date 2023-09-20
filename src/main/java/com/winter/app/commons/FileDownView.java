package com.winter.app.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.winter.app.board.FileVO;

import lombok.extern.slf4j.Slf4j;

@Component  // bean의 이름: 따로 지정하지 않으면, 클래스명의 앞자리 소문자.  
@Slf4j
public class FileDownView extends AbstractView {
	
	@Value("${app.upload}")
	private String filePath;

/** Map 타입: key: value 속성 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 서버에서 파일을 찾아서 client으로 전송
		// 어떤 파일을 찾아? 그리고 그 파일이 어디에 저장되어 있어? 
		// /GDJ68/upload/
		// value가 Object 타입이기 때문에 형변환 해줘야 함.
		String board = (String)model.get("board");
		
		// 어디에서 어떤 파일을 꺼낼 것인가에 대한 정보를 model에 담음.
		FileVO fileVO = (FileVO)model.get("fileVO");
		
/**filePath+board (여기에서), fileVO.getFileName() 이 파일을 꺼낸다.  */		
		File file = new File(filePath+board, fileVO.getFileName());
		
		// 한글 인코딩 처리
		response.setCharacterEncoding("UTF-8");
		
		// 파일의 크기 정보 : 해당 파일의 길이를 가져와줘.
		// 매개변수를 long 타입으로 줌. 
		response.setContentLengthLong(file.length());
		
		// 다운로드 시 파일의 이름을 인코딩함
		String downName = URLEncoder.encode(fileVO.getOriName(),"UTF-8");
		
		// Header 설정
		response.setHeader("Contents-Disposition", "attachment;filename=\""+downName+"\"");
		response.setHeader("content-Transfer-Encoding", "binary");
		
		// 하드디스크(HDD)에서 파일을 읽어오기
		FileInputStream fi = new FileInputStream(file);
		
		// 읽은 애를 Client으로 전송 (파일 내보내기)
		OutputStream os = response.getOutputStream();
		
		// copy(소스, 목적지) : 앞의 소스에서 읽어들인 것을 뒤의 어디로 전송하자.
		FileCopyUtils.copy(fi, os);
		
		// 자원 해제
		os.close();
		fi.close();
		
//		log.info("======== File Down view ============");
//		log.info("Board : {}" , board);
		
		
	}
	
	
}
