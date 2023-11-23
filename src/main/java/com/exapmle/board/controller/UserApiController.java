package com.exapmle.board.controller;

import com.exapmle.board.domain.User;
import com.exapmle.board.dto.AddUser;
import com.exapmle.board.dto.UpdateUser;
import com.exapmle.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService service;

    @GetMapping("/api/user/{userid}/{password}")
    public ResponseEntity<User> login(@PathVariable String userid, @PathVariable String password, HttpSession session) {

        User loginUser = service.findByUseridAndPassword(userid, password);

        if (loginUser != null) {

            session.setAttribute("loginUser", loginUser);
            return ResponseEntity.ok(loginUser);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/api/user/{userid}")
    public ResponseEntity<Boolean> duplicateId(@PathVariable String userid) {

        Boolean duplicateId = service.existsByUserid(userid);
        return ResponseEntity.ok(duplicateId);
    }

    @PostMapping("/api/user")
    public ResponseEntity<User> join(@RequestBody AddUser user) {

        User saveUser = service.save(user);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saveUser);
        /**
         * 아래와 같은 결과가 나오게 됨
         * HTTP/1.1 201 Created
         * Content-Type: application/json
         *
         * {
         *   "userId": "123",
         *   "username": "exampleUser",
         *   "email": "user@example.com",
         *   // ... 기타 필드들
         * }
         * */
    }

    @DeleteMapping("/api/user/{userid}")
    public ResponseEntity<User> delete(@PathVariable String userid) {

        service.deleteByUserId(userid);

        // 단순히 작업 처리를 했을 때의 ok 상태 코드만 받게 된다.
        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UpdateUser user) {

        User updateUser = service.update(id, user);

        return ResponseEntity.ok()
                .body(updateUser);
    }

}
