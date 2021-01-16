package com.dsm.boot03.persistence;

import com.dsm.boot03.domain.Board;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {

    public List<Board> findBoardByTitle(String title);

    public Collection<Board> findByWriter(String writer);

    // 작성자 이름에 ~가 들어가있는 게시글을 검색하려고 함
    public Collection<Board> findByWriterContaining(String writer);

    public Collection<Board> findByTitleContainingOrContentContaining(String title, String content);

    public Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);

    // bno 가 특정 숫자보다 큰 것을 (부등호 처리) 역순 처리
    public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);

}
