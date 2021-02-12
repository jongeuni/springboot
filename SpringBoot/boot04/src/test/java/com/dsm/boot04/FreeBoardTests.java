package com.dsm.boot04;

import com.dsm.boot04.domain.FreeBoard;
import com.dsm.boot04.persistence.FreeBoardReplyRepository;
import com.dsm.boot04.persistence.FreeBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.stream.IntStream;

@SpringBootTest
@Commit
public class FreeBoardTests {
    @Autowired
    FreeBoardRepository boardRepo;

    @Autowired
    FreeBoardReplyRepository replyRepo;

    @Test // 게시물 등록
    public void insertDummy(){

        IntStream.range(1,200).forEach(i->{
            FreeBoard board = new FreeBoard();
            board.setTitle("Free Board ... "+i);
            board.setContent("Free Content... "+i);
            board.setWriter("user"+i%10);
            boardRepo.save(board);
        });
    }
}
