package com.exapmle.board.dto;

import com.exapmle.board.domain.User;
import lombok.Getter;

@Getter
public class LoginRequest {

    private final String userid;
    private final String password;

    public LoginRequest(User user) {
        this.userid = user.getUserid();
        this.password = user.getPassword();
    }
}
