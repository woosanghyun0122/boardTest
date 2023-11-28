package com.exapmle.board.controller;

import com.exapmle.board.domain.User;
import com.exapmle.board.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserViewController {

    private final UserService service;

    @GetMapping("/")
    public String home(Model model, HttpSession session)  {

        User loginUser = (User)session.getAttribute("loginUser");

        if (loginUser != null && loginUser.getUserid() != null) {

            User updatedId = service.findById(loginUser.getId());
            model.addAttribute("loginUser", updatedId);
        }

        return "index";
    }

    @GetMapping("/login")
    public String login() {

        return "user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/myPage/{id}")
    public String myPage(@PathVariable Long id, Model model) {

        User loginUser = service.findById(id);
        model.addAttribute("loginUser", loginUser);
        return "user/myPage";
    }

    @GetMapping("/join")
    public String join() {
        return "user/join";
    }


}
