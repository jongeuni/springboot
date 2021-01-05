package com.dsm.boot02.persistence;

import com.dsm.boot02.domain.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface BoardRepository extends CrudRepository<Board, Long> {
}
