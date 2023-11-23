package com.exapmle.board.dto;

import com.exapmle.board.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddUser {

    private String userid;
    private String password;
    private String username;
    private String phone;
    private String email;
    private String address;

    public User toEntity() {
        return User.builder()
                .userid(userid)
                .password(password)
                .username(username)
                .phone(phone)
                .email(email)
                .address(address)
                .build();
    }
}
