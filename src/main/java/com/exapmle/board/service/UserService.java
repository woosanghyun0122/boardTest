package com.exapmle.board.service;

import com.exapmle.board.domain.User;
import com.exapmle.board.dto.AddUser;
import com.exapmle.board.dto.UpdateUser;
import com.exapmle.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User findByUserIdAndPassword(String userId, String password) {

        return repository.findByUserIdAndPassword(userId, password)
                .orElseThrow(() -> new IllegalArgumentException("not found:" + userId + "not found" + password));


    }

    public User save(AddUser user) {
        return repository.save(user.toEntity());
    }

    public void deleteByUserId(String userId) {

        repository.deleteByUserId(userId);
    }

    @Transactional
    public User update(Long id, UpdateUser user) {

        User findUser =  repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found" + id));

        findUser.update(user.getUserId(), user.getPassword(), user.getUsername(), user.getPhone(), user.getEmail(), user.getAddress());

        return findUser;
    }

}
