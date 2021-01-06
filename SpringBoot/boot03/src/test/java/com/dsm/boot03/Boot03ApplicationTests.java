package com.dsm.boot03;

import com.dsm.boot03.domain.Board;
import com.dsm.boot03.persistence.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest //ExtendWith(SpringE)
class Boot03ApplicationTests {

	@Autowired
	private BoardRepository repo;



	/*@Test
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
	public void testByTitle(){
		System.out.println("찾기전");
		repo.findBoardByTitle("제목..177").forEach(board-> System.out.println(board)); // return 해 준 Board Entity 리스트를 하나씩 board에 담는 건가???
		System.out.println("찾음");
	}

	@Test
	void contextLoads() {
	}


}
