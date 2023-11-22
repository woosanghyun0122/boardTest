package com.exapmle.board.repository;

import com.exapmle.board.domain.User;
import com.exapmle.board.dto.LoginRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserIdAndPassword(String userId, String password);

    User deleteByUserId(String userId);
}
