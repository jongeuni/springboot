package com.dsm.boot06re;

import com.dsm.boot06re.domain.WebBoard;
import com.dsm.boot06re.domain.WebReply;
import com.dsm.boot06re.persistence.WebReplyRepository;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.Arrays;
import java.util.stream.IntStream;

@SpringBootTest
@Log
@Commit
public class WebReplyRepositoryTests {

    @Autowired
    WebReplyRepository repo;

    /*@Test //댓글 더미데이터 테스트 코드
    public void testInsertReplies(){
        Long[] arr = {304L, 303L, 300L};

        Arrays.stream(arr).forEach(num->{
            WebBoard board = new WebBoard();
            board.setBno(num);

            IntStream.range(0,10).forEach(i->{

                WebReply reply = new WebReply();
                reply.setReplyText("REPLY ..."+i);
                reply.setReplyer("replyer"+i);
                reply.setBoard(board);

                repo.save(reply);

            });
        });
    }*/
}
