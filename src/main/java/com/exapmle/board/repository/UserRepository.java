package com.exapmle.board.repository;

import com.exapmle.board.domain.User;
import com.exapmle.board.dto.LoginRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUseridAndPassword(String userid, String password);

    @Transactional
    void deleteByUserid(String userid);

    boolean existsByUserid(String userid);
}
