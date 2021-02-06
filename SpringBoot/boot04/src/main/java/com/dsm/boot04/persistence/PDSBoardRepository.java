package com.dsm.boot04.persistence;

import com.dsm.boot04.domain.PDSBoard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long> {


    @Modifying // @Query가 DML 작업을 처리할 수 있도록 (INSERT, UPDATE, DELETE)
    @Query("update PDSFile f set f.pdsfile = ?2 where f.fno = ?1")
    public void updatePDSFile(Long fno, String enwFileName);
}
