package com.exapmle.board.controller;

import com.exapmle.board.domain.User;
import com.exapmle.board.dto.AddUser;
import com.exapmle.board.dto.UpdateUser;
import com.exapmle.board.repository.UserRepository;
import com.exapmle.board.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static java.nio.file.Paths.get;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserApiControllerTest {

    @Autowired
    UserService service;

    @Autowired
    UserRepository repository;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        repository.deleteAll();

    }

    @DisplayName("회원가입")
    @Test
    void save() throws Exception {

        String url = "/api/user";
        final AddUser user1 = new AddUser("test", "1234", "가나다", "01012345678", "abc@naver.com", "서울");
        final String requestBody = objectMapper.writeValueAsString(user1);


        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        result.andExpect(status().isCreated());

        List<User> user = repository.findAll();

        assertThat(user.get(0).getUserid()).isEqualTo("test");
        assertThat(user.get(0).getPassword()).isEqualTo("1234");
    }


    @DisplayName("로그인 로직")
    @Test
    void loginLogic() {

        String url = "/api/user";
        User user = repository.save(User.builder()
                .userid("test")
                .password("1234")
                .username("가나다")
                .phone("01012345678")
                .email("abc@naver.com")
                .address("seoul")
                .build()
        );
////        final String requestBody = objectMapper.writeValueAsString(user);
////
////        ResultActions result = mockMvc.perform(post(url)
////                .contentType(MediaType.APPLICATION_JSON_VALUE)
////                .content(requestBody));
//
//        result.andExpect(status().isCreated());

        User loginCheck = service.findByUseridAndPassword(user.getUserid(), user.getPassword());

        assertThat(loginCheck.getUserid()).isEqualTo("test");
        assertThat(loginCheck.getPassword()).isEqualTo("1234");

    }

    @DisplayName("회원정보수정")
    @Test
    void update() throws Exception {

        String url = "/api/user/{id}";
        User user = repository.save(User.builder()
                .userid("test")
                .password("1234")
                .username("가나다")
                .phone("01012345678")
                .email("abc@naver.com")
                .address("seoul")
                .build()
        );

        UpdateUser updateUser = new UpdateUser("test1", "1234", "dddd", "dd", "aa", "busan");

        ResultActions result = mockMvc.perform(put(url, user.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(updateUser))
        );

        result.andExpect(status().isOk());

        User check = repository.findById(user.getId()).get();

        assertThat(check.getUserid()).isEqualTo("test1");
        assertThat(check.getAddress()).isEqualTo("busan");
    }

    @DisplayName("아이디 중복 확인")
    @Test
    void duplicate() throws Exception {

        String url = "/api/user/{userid}";
        User user = repository.save(User.builder()
                .userid("test")
                .password("1234")
                .username("가나다")
                .phone("01012345678")
                .email("abc@naver.com")
                .address("seoul")
                .build()
        );

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(url, user.getUserid()))
                .andExpect(status().isOk());


    }
}
