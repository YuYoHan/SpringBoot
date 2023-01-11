package com.example.book.persistence;

import com.example.book.domain.Board;
import com.example.book.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {
    @Query("select  b FROM  Board b")
    Page<Board> getBoardList(Pageable pageable);
}
