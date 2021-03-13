package com.dsm.boot06re.controller;

import com.dsm.boot06re.domain.WebBoard;
import com.dsm.boot06re.domain.WebReply;
import com.dsm.boot06re.persistence.WebReplyRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
@RequestMapping("/replies/*")
public class WebReplyController {
    @Autowired
    private WebReplyRepository replyRep;

    @PostMapping("/{bno}")
    public ResponseEntity<Void> addReply(@PathVariable("bno")Long bno, @RequestBody WebReply reply){
        log.info("addReply..................");
        log.info("BNO: "+bno);
        log.info("REPLY: "+reply);

        WebBoard board = new WebBoard();
        board.setBno(bno);

        reply.setBoard(board);
        replyRep.save(reply);

        return new ResponseEntity<>()

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private List<WebReply> getListByBoard(WebBoard board)throws RuntimeException{
        log.info("getListByBoard...."+board);
        return replyRep.getRepliesOfBoard(board);

    }
}
