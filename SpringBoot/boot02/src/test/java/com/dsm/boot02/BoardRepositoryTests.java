package com.dsm.boot02;


import com.dsm.boot02.domain.Board;
import com.dsm.boot02.persistence.BoardRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepo;


    @Test
    public void inspect(){
        // 실제 객체의 클래스 이름
        Class<?> clz = boardRepo.getClass();
        System.out.println(clz.getName());

        // 클래스가 구현하고 있는 인터페이스 목록
        Class<?>[] interfaces = clz.getInterfaces();
        Stream.of(interfaces).forEach(inter -> System.out.println(inter.getName()));

        // 클래스의 부모 클래스
        Class<?> superClasses = clz.getSuperclass();
        System.out.println(superClasses.getName());
    }

    @Test
    public void testInsert(){
        Board board = new Board();
        board.setTitle("게시물 제목");
        board.setContent("게시물 내용 넣기");
        board.setWriter("user00");

        boardRepo.save(board);
    }

    @Test
    public void testRead(){
        // db 1번에 해당하는 long 데이터가 존재해야함.
        boardRepo.findById(1L).ifPresent((board) -> {
            System.out.println(board);
        });
    }

    /*
    @BeforeEach
    public void testBeforeTest(){
        Board board = new Board();
        board.setTitle("테스트");
        board.setContent("누가먼저");
        board.setWriter("user02");

        boardRepo.save(board);
    }
    */
}
