package com.exapmle.board.dto;

import com.exapmle.board.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateUser {

    private String userId;
    private String password;
    private String username;
    private String phone;
    private String email;
    private String address;


}
