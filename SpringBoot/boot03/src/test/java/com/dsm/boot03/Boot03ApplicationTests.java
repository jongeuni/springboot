package com.dsm.boot03;

import com.dsm.boot03.domain.Board;
import com.dsm.boot03.persistence.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.List;

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

	/*
	@Test
	public void testByWriterContaining(){
		// 작성자 이름에 05가 들어있는 게시글 검색
		// % 키워드 %
		Collection<Board> results = repo.findByWriterContaining("05");

		results.forEach(board -> System.out.println(board));
	}
	 */
/*
	@Test
	public void testByTitleAndBno(){
		// 제목에 특정 문자가 포함되어 있고 게시물 번호가 특정 숫자보다 큰 데이터 조회
		Collection<Board> results= repo.findByTitleContainingAndBnoGreaterThan("5",50L);

		results.forEach(board -> System.out.println(board));

	}*/
	/*@Test
	public void testBnoOrderBy(){
		Collection<Board> results = repo.findByBnoGreaterThanOrderByBnoDesc(90L);
		results.forEach(board -> System.out.println(board));
	}*/

	/*@Test
	public void testBnoOrderByPaging(){

		PageRequest paging = PageRequest.of(0, 10); //0번째 페이지에 10건의 데이터가 가져와짐
		Collection<Board> results = repo.findByBnoGreaterThanOrderByBnoDesc(0L,paging);
		results.forEach(board-> System.out.println(board));
		//Pageable pagding =  PageRequest.of(0,10);

	}*/
	/*
	@Test
	public void testBnoPagingSort(){
		Pageable paging = PageRequest.of(0,10, Sort.Direction.ASC,"bno");


		Page<Board> result = repo.findByBnoGreaterThan(0L,paging);
		System.out.println("PAGE SIZE: "+result.getSize());
		System.out.println("TOTAL PAGES: "+result.getTotalPages());
		System.out.println("TOTAL COUNT: "+result.getTotalElements());
		System.out.println("NEXT: "+result.nextPageable());

		List<Board> list = result.getContent();
		list.forEach(board -> System.out.println(board));
	}*/

	@Test
	public void testByTilte2(){
		repo.findByTitle("17")
				.forEach(board -> System.out.println(board));
	}

	@Test
	void contextLoads() {
	}


}
