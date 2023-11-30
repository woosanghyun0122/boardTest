package com.exapmle.board.controller;
import com.exapmle.board.domain.Board;
import com.exapmle.board.domain.User;
import com.exapmle.board.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


import com.exapmle.board.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardViewController {

    private final BoardService service;

    @GetMapping("/board/main")
    public String main(Model model, HttpSession session) {

        List<Board> boardList = service.findAll();


        model.addAttribute("list", boardList);

        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser != null) {
            log.info("loginID={}", loginUser.getUserid());
            model.addAttribute("userid", loginUser.getUserid());
        } else {
            log.info("loginId is null!!!");
        }

        return "board/main";
    }

    @GetMapping("/board/search")
    public String search(@RequestParam String title, Model model) {

        List<Board> searchList = service.findByTitleLike(title);
        log.info("------------------------={}",searchList.get(0).getTitle());
        model.addAttribute("searchList", searchList);

        return "board/main";
    }

    @GetMapping("/board/write")
    public String write(@RequestParam String userid, Model model) {

        model.addAttribute("userid", userid);
        return "board/write";
    }

    @GetMapping("/board/view/{id}")
    public String view(@PathVariable Long id) {

        return "board/view";
    }
    public String main() {

        return "board/main";
    }
}
