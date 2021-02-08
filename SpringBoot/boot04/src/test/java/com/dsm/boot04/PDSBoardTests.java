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
import java.util.List;
import java.util.Optional;

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

    /*@Transactional // delete 나 update 사용하는 경우 트랜잭션 처리를 해줘야 함
    @Test
    public void testUpdateFileName1(){
        Long fno = 1L;
        String newName = "updateFile1.doc";
        repo.updatePDSFile(fno, newName);
    }*/

    /*
    @Transactional
    @Test
    public void testUpdateFileName2(){
        String newName = "updatedFile2.doc";
        // 번호 존재하는지 확인
        Optional<PDSBoard> result = repo.findById(2l);

        // ifPresent는  Optional 객체가 감싸고 있는 값이 존재할 경우에만 실행될 로직을 함수형 인자로 넘길 수 있음
        result.ifPresent(pds->{
            PDSFile target = new PDSFile();
            target.setFno(2L);
            target.setPdsfile(newName);

            int idx = pds.getFiles().indexOf(target);

            if(idx>-1){
                List<PDSFile> list = pds.getFiles();
                list.remove(idx);
                list.add(target);
                pds.setFiles(list);
            }
            repo.save(pds);
        });
    }*/

    @Transactional
    @Test
    public void deletePDSFile(){
        Long fno = 2L;
        int count = repo.deletePDSFile(fno);
    }
}
