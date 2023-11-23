package com.exapmle.board.controller;

import com.exapmle.board.domain.User;
import com.exapmle.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserViewController {

    private final UserService service;

    @GetMapping("/")
    public String home(Model model, HttpSession session) {

        User loginUser = (User)session.getAttribute("loginUser");
        if (loginUser.getUserid() != null) {
            model.addAttribute("loginUser", loginUser);
        }

        return "index";
    }

    @GetMapping("/login")
    public String login() {

        return "user/login";
    }

    @GetMapping("/join")
    public String join() {
        return "user/join";
    }


}
