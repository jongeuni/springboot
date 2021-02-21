package com.dsm.boot06re;

import com.dsm.boot06re.domain.WebBoard;
import com.dsm.boot06re.persistence.WebBoardRepository;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.util.stream.IntStream;

import static org.reflections.Reflections.log;

@Log
@SpringBootTest
@Commit
public class WebBoardRepositoryTests {
    @Autowired
    WebBoardRepository repo;

    /*@Test // 더미 데이터 추가
    public void insertBoardDummies(){
        IntStream.range(0,300).forEach(i->{
            WebBoard board = new WebBoard();

            board.setTitle("Sample Board Title "+i);
            board.setContent("Content Sample... "+i+" of Board");
            board.setWriter("user0"+(i%10));

            repo.save(board);
        });
    }*/

    @Test
    public void testList1(){
        Pageable pageable = PageRequest.of(0,20, Sort.Direction.DESC, "bno");

        Page<WebBoard> result = repo.findAll(repo.makePredicate(null,null),pageable);

        log.info("PAGE: "+result.getPageable());

        log.info("---------------");
        result.getContent().forEach(board->log.info(""+board));
    }
}
