package com.study.board.dto;


import com.study.board.domain.Board;

public record BoardRequest(
        String title,
        String content
) {

    public Board toEntity() {
        return new Board(title, content);
    }
}
