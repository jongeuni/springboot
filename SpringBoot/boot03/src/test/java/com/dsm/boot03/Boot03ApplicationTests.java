package com.dsm.boot03;

import com.dsm.boot03.persistence.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest //ExtendWith(SpringE)
class Boot03ApplicationTests {

	@Autowired
	private BoardRepository rnepo;


/*
	@Test
	public void testInsert200(){
		for(int i=1; i<=200; i++){
			Board board = new Board();
			board.setTitle("제목..."+i);
			board.setContent("내용..."+i+"채우기");
			board.setWriter("user0"+(i%10));
			repo.save(board);
		}
	}*/

	@Test
	void contextLoads() {
	}


}
