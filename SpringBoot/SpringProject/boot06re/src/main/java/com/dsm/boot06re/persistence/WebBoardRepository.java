package com.dsm.boot06re.persistence;

import com.dsm.boot06re.domain.QWebBoard;
import com.dsm.boot06re.domain.WebBoard;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import com.querydsl.core.types.Predicate;

public interface WebBoardRepository extends CrudRepository<WebBoard, Long>, QuerydslPredicateExecutor<WebBoard> {
    public default Predicate makePredicate(String type, String keyword){
        BooleanBuilder builder = new BooleanBuilder();

        QWebBoard board = QWebBoard.webBoard;

        // type if~else

        //bno>0
        builder.and(board.bno.gt(0));

        if(type ==null){
            return builder; // 검색 조건이 없을 때
        }
        // 검색 조건이 있을 때
        switch(type){
            case "t":
                builder.and(board.title.like("%"+keyword+"%"));
                break;
            case "c":
                builder.and(board.content.like("%"+keyword+"%"));
            case "w":
                builder.and(board.writer.like("%"+keyword+"%"));
                break;
        }
        return builder;
    }
}
