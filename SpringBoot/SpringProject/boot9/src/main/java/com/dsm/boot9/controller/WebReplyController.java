package com.dsm.boot9.controller;

import com.dsm.boot9.domain.WebBoard;
import com.dsm.boot9.domain.WebReply;
import com.dsm.boot9.persistence.WebReplyRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Log
@RestController
@RequestMapping("/replies/*")
public class WebReplyController {
    @Autowired
    private WebReplyRepository replyRepo;

    @Secured(value={"ROLE_BASIC","ROLE_MANAGER","ROLE_ADMIN"})
    @Transactional
    @PostMapping("/{bno}")
    public ResponseEntity<List<WebReply>> addReply(@PathVariable("bno")Long bno, @RequestBody WebReply reply){
        log.info("addReply..................");
        log.info("BNO: "+bno);
        log.info("REPLY: "+reply);

        WebBoard board = new WebBoard();
        board.setBno(bno);

        reply.setBoard(board);
        replyRepo.save(reply);

        return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
    }

    private List<WebReply> getListByBoard(WebBoard board)throws RuntimeException{
        log.info("getListByBoard...."+board);
        return replyRepo.getRepliesOfBoard(board);

    }

    @Secured(value={"ROLE_BASIC","ROLE_MANAGER","ROLE_ADMIN"})
    @Transactional
    @DeleteMapping("/{bno}/{rno}") // 댓글 삭제
    public ResponseEntity<List<WebReply>> remove(@PathVariable("bno")Long bno, @PathVariable("rno")Long rno){
        log.info("delete reply: "+rno);

        replyRepo.deleteById(rno);

        WebBoard board = new WebBoard();
        board.setBno(bno);

        return new ResponseEntity<>(getListByBoard(board),HttpStatus.OK);

    }

    @Secured(value={"ROLE_BASIC","ROLE_MANAGER","ROLE_ADMIN"})
    @Transactional
    @PutMapping("/{bno}")
    public ResponseEntity<List<WebReply>> modify(@PathVariable("bno")Long bno, @RequestBody WebReply reply){
        log.info("modify reply: "+reply);

        replyRepo.findById(reply.getRno()).ifPresent(origin->{
            origin.setReplyText(reply.getReplyText());

            replyRepo.save(origin);
        });

        WebBoard board = new WebBoard();
        board.setBno(bno);

        return new ResponseEntity<>(getListByBoard(board),HttpStatus.CREATED);
    }

    @GetMapping("/{bno}") // 댓글 목록 처리
    public ResponseEntity<List<WebReply>> getReplies(@PathVariable("bno")Long bno){
        log.info("get All Replies........................");

        WebBoard board = new WebBoard();
        board.setBno(bno);
        return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
    }
}
