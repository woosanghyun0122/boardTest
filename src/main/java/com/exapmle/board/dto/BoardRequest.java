package com.exapmle.board.dto;

<<<<<<< HEAD
import com.exapmle.board.domain.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardRequest {

    private final String title;
    private final String content;


    public BoardRequest(Board board) {

        this.title = board.getTitle();
        this.content = board.getContent();
    }

=======
import lombok.Getter;

@Getter
public class BoardRequest {
>>>>>>> origin/master
}
