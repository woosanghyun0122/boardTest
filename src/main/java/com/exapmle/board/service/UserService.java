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

    public User findByUseridAndPassword(String userid, String password) {

        return repository.findByUseridAndPassword(userid, password)
                .orElseThrow(() -> new IllegalArgumentException("not found:" + userid + "not found" + password));


    }

    public boolean existsByUserid(String userid) {

        return repository.existsByUserid(userid);
    }

    public User save(AddUser user) {
        return repository.save(user.toEntity());
    }

    public void deleteByUserId(String userid) {

        repository.deleteByUserid(userid);
    }

    @Transactional
    public User update(Long id, UpdateUser user) {

        User findUser =  repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found" + id));

        findUser.update(user.getUserid(), user.getPassword(), user.getUsername(), user.getPhone(), user.getEmail(), user.getAddress());

        return findUser;
    }

}
