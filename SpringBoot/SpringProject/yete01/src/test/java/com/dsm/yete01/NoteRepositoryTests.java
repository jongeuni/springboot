package com.dsm.yete01;

import com.dsm.yete01.domain.Note;
import com.dsm.yete01.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class NoteRepositoryTests {
    @Autowired
    NoteRepository repo;

    @Test // note 삽입 테스트
    public void insertNote01(){
        Note note = new Note();
        note.setTitle("title01");
        note.setCategory(2);
        note.setContent("가나다라마바사는 사랑한다는 말이야");

        System.out.println(note.getNumber());

        repo.save(note);

    }
}
