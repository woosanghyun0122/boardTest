package com.exapmle.board.dto;

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

}
