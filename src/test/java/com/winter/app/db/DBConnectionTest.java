package com.winter.app.db;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DBConnectionTest {

	@Autowired
	private DataSource dataSource;
	
	@Test
	void test() throws Exception{
		Connection connection = dataSource.getConnection();
		
		// null이 아닌지 체크 : null이라면 DB 연결이 안되었다라는 뜻이다. 
		assertNotNull(connection);
	}

}
