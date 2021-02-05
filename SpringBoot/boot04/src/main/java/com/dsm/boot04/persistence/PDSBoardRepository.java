package com.dsm.boot04.persistence;

import com.dsm.boot04.domain.PDSBoard;
import org.springframework.data.repository.CrudRepository;

public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long> {

}
