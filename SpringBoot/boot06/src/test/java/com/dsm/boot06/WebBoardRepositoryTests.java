package com.dsm.boot06;

import com.dsm.boot06.domain.WebBoard;
import com.dsm.boot06.persistence.WebBoardRepository;
import groovy.util.logging.Log4j;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.stream.IntStream;

@SpringBootTest
@Commit
public class WebBoardRepositoryTests {
    @Autowired
    WebBoardRepository repo;

    @Test
    public void insertBoardDummies(){ // 더미데이터 추가
        IntStream.range(0, 300).forEach(i->{
            WebBoard board = new WebBoard();

            board.setTitle("Sample Board Title "+i);
            board.setContent("Content Sample ... "+ i+"of Board");
            board.setWriter("user0"+(i%10));

            repo.save(board);
        });
    }
}
