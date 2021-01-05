package com.dsm.boot03.persistence;

import com.dsm.boot03.domain.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {
}
