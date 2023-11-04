package com.study.board.dto;


import com.study.board.domain.Board;

public record BoardResponse(

        int id,
        String title,
        String content
) {

    public static BoardResponse from(Board entity) {
        return new BoardResponse(entity.getId(), entity.getTitle(), entity.getContent());
    }
}
