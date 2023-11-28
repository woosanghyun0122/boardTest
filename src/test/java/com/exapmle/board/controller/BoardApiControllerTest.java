package com.exapmle.board.controller;

import com.exapmle.board.domain.Board;
import com.exapmle.board.dto.AddBoard;
import com.exapmle.board.repository.BoardRepository;
import com.exapmle.board.service.BoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.thymeleaf.context.IContext;

import java.net.URI;
import java.nio.file.Paths;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BoardApiControllerTest {

    @Autowired
    BoardService service;

    @Autowired
    BoardRepository repository;

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

    @Test
    @DisplayName("신규 글 작성")
    void save() throws Exception {
        String url = "/api/board";
        final AddBoard board1 = new AddBoard("title", "content", "test1");

        final String requestBody = objectMapper.writeValueAsString(board1);

        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );
        result.andExpect(status().isCreated()); // 상태코드를 통해서 검증

        List<Board> list = repository.findAll();
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0).getTitle()).isEqualTo("title");

    }

    @Test
    @DisplayName("검색")
    void findByTitleLike() throws Exception {

        String url = "/api/board?title=${title}";

        repository.save(Board.builder()
                .title("title")
                .content("content")
                .writer("test1")
                .build());

        repository.save(Board.builder()
                .title("taaaaaa")
                .content("content")
                .writer("test")
                .build());


        ResultActions result = mockMvc.perform(get(url, "tit ")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("title"));

    }

    @Test
    @DisplayName("전체 글 조회")
    void findAll() throws Exception {

        //given : 데이터 조회하기 위해서 save
      String url = "/api/board";

      repository.save(Board.builder()
              .title("title")
              .content("content")
              .writer("test1")
              .build());

      // when : API 엔드포인트에 get 요청을 수행
      ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("title"))
                .andExpect(jsonPath("$[0].writer").value("test1"));

    }





}
