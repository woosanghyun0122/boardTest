package com.exapmle.board.service;

import com.exapmle.board.domain.Board;
import com.exapmle.board.dto.AddBoard;
import com.exapmle.board.dto.UpdateBoard;
import com.exapmle.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;

    public Board save(AddBoard board) {

        return repository.save(board.toEntity());
    }

    public Board findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: "+id) );
    }

    public List<Board> findByTitleLike(String title) {

        return repository.findByTitleLike(title);
    }

    @Transactional
    public Board update(Long id,UpdateBoard board) {

        Board findBoard = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found:" + id));

        findBoard.update(board.getTitle(), board.getContent());

        return findBoard;

    }

    public void delete(Long id) {

        repository.deleteById(id);
    }


    public List<Board> findAll() {
        return repository.findAll();

    }






}
