package com.exapmle.board.dto;

import com.exapmle.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddBoard {

    private String title;
    private String content;
    private String writer;

    public Board toEntity() {

        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }
}
