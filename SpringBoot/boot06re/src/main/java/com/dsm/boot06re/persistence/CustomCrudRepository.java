package com.dsm.boot06re.persistence;

import com.dsm.boot06re.domain.WebBoard;
import org.springframework.data.repository.CrudRepository;

public interface CustomCrudRepository extends CrudRepository<WebBoard, Long>, CustomWebBoard {
}
