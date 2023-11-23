package com.exapmle.board.controller;

import com.exapmle.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserViewController {

    private final UserService service;

    @GetMapping("/login")
    public String login() {

        return "user/login";
    }

    @GetMapping("/join")
    public String join() {
        return "user/join";
    }


}
