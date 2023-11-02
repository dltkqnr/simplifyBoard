package com.study.board.dto;


import com.study.board.domain.Board;

import java.time.LocalDateTime;


public record BoardSimpleResponse(

        int id,
        String title,
        String content,

        LocalDateTime createAt
) {

    public static BoardSimpleResponse from(Board entity) {
        return new BoardSimpleResponse(entity.getId(), entity.getTitle(), entity.getContent(), entity.getCreatedAt());
    }
}
