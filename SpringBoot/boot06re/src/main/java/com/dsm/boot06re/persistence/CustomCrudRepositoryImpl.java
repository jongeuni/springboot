package com.dsm.boot06re.persistence;

import com.dsm.boot06re.domain.QWebBoard;
import com.dsm.boot06re.domain.QWebReply;
import com.dsm.boot06re.domain.WebBoard;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;
import java.util.List;

@Log
public class CustomCrudRepositoryImpl extends QuerydslRepositorySupport implements CustomWebBoard {

        public CustomCrudRepositoryImpl(){
            super(WebBoard.class);
        }


    @Override
    public Page<Object[]> getCustomPage(String type, String keyword, Pageable page){
        log.info("==========================");
        log.info("TYPE: "+type);
        log.info("KEYWORD: "+keyword);
        log.info("PAGE: "+page);
        log.info("==========================");

        QWebBoard b = QWebBoard.webBoard;

        JPQLQuery<WebBoard> query = from(b);

        JPQLQuery<Tuple> tuple = query.select(b.bno, b.title, b.writer, b.regdate);

        tuple.where(b.bno.gt(0L));

        tuple.orderBy(b.bno.desc());

        tuple.offset(page.getOffset());
        tuple.limit(page.getPageSize());


        List<Tuple> list = tuple.fetch();

        List<Object[]> resultList = new ArrayList<>();

        list.forEach(t->{
            resultList.add(t.toArray());
        });

        long total = tuple.fetchCount();

        return new PageImpl<>(resultList, page, total);
    }
}
