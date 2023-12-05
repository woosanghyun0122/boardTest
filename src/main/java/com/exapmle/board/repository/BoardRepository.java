package com.exapmle.board.repository;

import com.exapmle.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitleLike(String title);

    @Modifying
    @Query("update Board B set B.count = B.count + 1 where B.id = :id")
    int updateCount(@Param("id") Long id);
}
