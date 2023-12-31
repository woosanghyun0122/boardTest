package com.exapmle.board.controller;

import com.exapmle.board.domain.Board;
import com.exapmle.board.domain.User;
import com.exapmle.board.dto.AddBoard;
import com.exapmle.board.dto.UpdateBoard;
import com.exapmle.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.exapmle.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
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


    @GetMapping("/api/board/{title}")
    public ResponseEntity<List<Board>> findByTitleLike(@PathVariable String title, Model model) {

        List<Board> findBoardList = service.findByTitleLike("%" + title + "%");
        if (findBoardList != null) {
            return ResponseEntity.ok().body(findBoardList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(findBoardList);
        }

    }

    @PutMapping("/api/board/{id}")
    public ResponseEntity<Board> update(@PathVariable Long id, @RequestBody UpdateBoard board) {

        Board updateBoard = service.update(id, board);

        return ResponseEntity.ok().body(updateBoard);

    }

    @PutMapping("/api/board/count/{id}")
    public ResponseEntity<Integer> updateCount(@PathVariable Long id, HttpSession session) {

        User user = (User) session.getAttribute("loginUser");
        Board board = service.findById(id);
        if(user ==null || !user.getId().equals(board.getId())){

            int result = service.updateCount(id);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }

    }

    @DeleteMapping("/api/board/{id}")
    public ResponseEntity<Board> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.ok()
                .build();
    }
}
