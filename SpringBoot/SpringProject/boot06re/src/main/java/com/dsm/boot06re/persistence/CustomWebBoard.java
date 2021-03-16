package com.dsm.boot06re.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// 사용자 정의 인터페이스
public interface CustomWebBoard {

    public Page<Object[]> getCustomPage(String type, String keyword, Pageable page);
}
