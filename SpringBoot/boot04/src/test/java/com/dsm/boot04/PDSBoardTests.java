package com.dsm.boot04;

import com.dsm.boot04.domain.PDSBoard;
import com.dsm.boot04.domain.PDSFile;
import com.dsm.boot04.persistence.PDSBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.Arrays;

@SpringBootTest
@Commit
public class PDSBoardTests {
    @Autowired
    PDSBoardRepository repo;
    /*@Test
    public void testInsertPDS(){
        PDSBoard pds = new PDSBoard();
        pds.setPname("Document");

        PDSFile file1 = new PDSFile();
        file1.setPdsfile("file1.doc");

        PDSFile file2 = new PDSFile();
        file2.setPdsfile("file2.doc");

        pds.setFiles(Arrays.asList(file1,file2));

        repo.save(pds);
    }*/

    @Transactional // delete 나 update 사용하는 경우 트랜잭션 처리를 해줘야 함
    @Test
    public void testUpdateFileName1(){
        Long fno = 1L;
        String newName = "updateFile1.doc";
        repo.updatePDSFile(fno, newName);
    }
}
