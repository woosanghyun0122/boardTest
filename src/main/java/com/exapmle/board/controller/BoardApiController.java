package com.exapmle.board.controller;

import com.exapmle.board.domain.Board;
import com.exapmle.board.dto.AddBoard;
import com.exapmle.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService service;

    @PostMapping("/api/board")
    public ResponseEntity<Board> save(@RequestBody AddBoard board) {

        Board newBoard = service.save(board);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newBoard);
    }

    @GetMapping("/api/board")
    public ResponseEntity<List<Board>> listAll() {

        List<Board> boardList = service.findAll();

        return ResponseEntity.ok().body(boardList);
    }

    @GetMapping(params = "title")
    public ResponseEntity<List<Board>> findByTitleLike(@RequestParam String title) {

        List<Board> findBoardList = service.findByTitleLike(title);

        return ResponseEntity.ok().body(findBoardList);
    }
}
