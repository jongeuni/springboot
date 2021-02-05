package com.dsm.boot04;

import com.dsm.boot04.domain.PDSBoard;
import com.dsm.boot04.domain.PDSFile;
import com.dsm.boot04.persistence.PDSBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class PDSBoardTests {
    @Autowired
    PDSBoardRepository repo;
    @Test
    public void testInsertPDS(){
        PDSBoard pds = new PDSBoard();
        pds.setPname("Document");

        PDSFile file1 = new PDSFile();
        file1.setPdsfile("file1.doc");

        PDSFile file2 = new PDSFile();
        file2.setPdsfile("file2.doc");

        pds.setFiles(Arrays.asList(file1,file2));

        repo.save(pds);
    }
}
