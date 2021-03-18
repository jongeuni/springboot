package com.dsm.yete01.controller;

import com.dsm.yete01.domain.Note;
import jdk.jfr.Category;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/note")
@Log
public class NoteController {
    // 메모 등록
    @PostMapping("/noteInset")
    public void noteInsert(@RequestBody Note note, @RequestParam("category") Category category){

    }
}
