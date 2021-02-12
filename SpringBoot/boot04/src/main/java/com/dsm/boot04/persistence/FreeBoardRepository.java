package com.dsm.boot04.persistence;

import com.dsm.boot04.domain.FreeBoard;
import org.springframework.data.repository.CrudRepository;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long> {
}
