package com.exapmle.board.controller;

import com.exapmle.board.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BoardViewController {

    private final BoardService service;

    @GetMapping("/board/main")
    public String main() {

        return "board/main";
    }
}
