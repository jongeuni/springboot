package com.dsm.boot06re;

import com.dsm.boot06re.domain.WebBoard;
import com.dsm.boot06re.persistence.WebBoardRepository;
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

    @Test // 더미 데이터 추가
    public void insertBoardDummies(){
        IntStream.range(0,300).forEach(i->{
            WebBoard board = new WebBoard();

            board.setTitle("Sample Board Title "+i);
            board.setContent("Content Sample... "+i+" of Board");
            board.setWriter("user0"+(i%10));

            repo.save(board);
        });
    }
}
