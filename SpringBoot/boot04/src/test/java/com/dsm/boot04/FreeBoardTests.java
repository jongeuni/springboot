package com.dsm.boot04;


import com.dsm.boot04.domain.FreeBoard;
import com.dsm.boot04.domain.FreeBoardReply;
import com.dsm.boot04.persistence.FreeBoardReplyRepository;
import com.dsm.boot04.persistence.FreeBoardRepository;
import org.apache.juli.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Commit
public class FreeBoardTests {
    @Autowired
    FreeBoardRepository boardRepo;

    @Autowired
    FreeBoardReplyRepository replyRepo;

    Logger log = LogManager.getLogger(FreeBoardTests.class); // 로그!

    /*@Test // 게시물 등록
    public void insertDummy(){

        IntStream.range(1,200).forEach(i->{
            FreeBoard board = new FreeBoard();
            board.setTitle("Free Board ... "+i);
            board.setContent("Free Content... "+i);
            board.setWriter("user"+i%10);
            boardRepo.save(board);
        });
    }*/
/*
    @Transactional
    @Test // 댓글 랜덤하게 추가 (양방향)
    public void insertReply2Way(){
        Optional<FreeBoard> result = boardRepo.findById(199L);

        result.ifPresent(board->{
            List<FreeBoardReply> replies = board.getReplies();
            FreeBoardReply reply = new FreeBoardReply();
            reply.setReply("REPLY...............");
            reply.setReplyer("replyer00");
            reply.setBoard(board);

            replies.add(reply);

            board.setReplies(replies);

            boardRepo.save(board);
        });
    }*/
/*
    @Test // (단방향)
    public void insertReplyWay(){
        FreeBoard board = new FreeBoard();
        board.setBno(199L);

        FreeBoardReply reply = new FreeBoardReply();
        reply.setReply("REPLY...............");
        reply.setReplyer("replyer00");
        reply.setBoard(board);

        replyRepo.save(reply);
    }*/

    /*@Test // 첫번째 페이지 출력
    public void testList1(){
        Pageable page = PageRequest.of(0,10, Sort.Direction.DESC, "bno");

        boardRepo.findByBnoGreaterThan(0L,page).forEach(board->{
            log.info(board.getBno()+": "+board.getTitle());
        });
    }*/

    @Test // 오류있음
    public void testList2(){
        Pageable page = PageRequest.of(0,10,Sort.Direction.DESC, "bno");


        boardRepo.findByBnoGreaterThan(0L,page).forEach(board->{
            log.info(board.getBno()+": "+board.getTitle()+": "+board.getReplies().size()); // 댓글갯수
        });

    }

}
