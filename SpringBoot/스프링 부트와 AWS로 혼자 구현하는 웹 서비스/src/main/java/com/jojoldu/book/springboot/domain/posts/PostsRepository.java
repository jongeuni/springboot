package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    // 인터페이스를 생성 후 JpaRepository<Entity 클래스, pk 타입>을 상속하면 기본 crud 메소드가 생성된다. (Entity 클래스와 기본 Entity Repository는 함께 위치해야 한다)
}
