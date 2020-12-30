package com.dsm.boot02.persistence;

import com.dsm.boot02.domain.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {
}
