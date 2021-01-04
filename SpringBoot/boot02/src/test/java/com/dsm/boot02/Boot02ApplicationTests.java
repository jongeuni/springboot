package com.dsm.boot02;

import com.dsm.boot02.persistence.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Boot02ApplicationTests {

	@Autowired
	private BoardRepository bo;

	@Test
	void contextLoads() {
	}

}
