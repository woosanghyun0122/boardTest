package com.exapmle.board.repository;

import com.exapmle.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitleLike(String title);
=======
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
>>>>>>> origin/master
}
