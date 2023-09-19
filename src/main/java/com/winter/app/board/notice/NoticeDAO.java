package com.winter.app.board.notice;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.winter.app.board.BoardDAO;

/** interface 는 객체를 생성할 수 없다. 그러므로 Mapper 어노테이션을 선언해줌.*/
/** Spring 레거시버전은 sqlSession, namespace 선언을 해줘야 하지만, Spring Boot 버전은 내장서버에서 알아서 해준다.
 *  제공하는 정보 : 어떤 namespace를 매칭시켜 어떤 ID에 맞는 DAO에 있는 메서드를 찾아가 실행할 것인가에 대한 두 가지 정보를 제공해줌.
*/
@Repository
@Mapper
public interface NoticeDAO extends BoardDAO{

	/** sqlSession, namespace 선언 더이상 할 필요 없음.
	*/
	
}
