package com.exapmle.board.controller;

import com.exapmle.board.domain.Board;
import com.exapmle.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService service;

    @GetMapping("/api/board")
    public ResponseEntity<List<Board>> listAll() {

        List<Board> boardList = service.findAll();

        return ResponseEntity.ok().body(boardList);
    }
}
