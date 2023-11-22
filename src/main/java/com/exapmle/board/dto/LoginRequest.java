package com.exapmle.board.dto;

import com.exapmle.board.domain.User;
import lombok.Getter;

@Getter
public class LoginRequest {

    private final String userId;
    private final String password;

    public LoginRequest(User user) {
        this.userId = user.getUserId();
        this.password = user.getPassword();
    }
}
