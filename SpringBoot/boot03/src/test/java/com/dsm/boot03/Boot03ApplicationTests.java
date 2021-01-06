package com.dsm.boot03;

import com.dsm.boot03.domain.Board;
import com.dsm.boot03.persistence.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

@SpringBootTest //ExtendWith(SpringE)
class Boot03ApplicationTests {

	@Autowired
	private BoardRepository repo;

	// insert 200 개
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

	// title로 select
	/*@Test
	public void testByTitle(){
		System.out.println("찾기전");
		repo.findBoardByTitle("제목..177").forEach(board-> System.out.println(board)); // return 해 준 Board Entity 리스트를 하나씩 board에 담는 건가???
		System.out.println("찾음");
	}*/

	// writer로 select
	/*@Test
	public void testByWriter(){
		Collection<Board> results = repo.findByWriter("user00");

		results.forEach(
				board -> System.out.println(board)
		);
	}*/

	@Test
	public void testByWriterContaining(){
		// 작성자 이름에 05가 들어있는 게시글 검색
		// % 키워드 %
		Collection<Board> results = repo.findByWriterContaining("05");

		results.forEach(board -> System.out.println(board));
	}

	@Test
	void contextLoads() {
	}


}
