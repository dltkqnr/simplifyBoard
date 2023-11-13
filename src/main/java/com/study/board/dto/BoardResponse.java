package com.study.board.dto;


import com.study.board.domain.Board;

import java.time.LocalDateTime;

public record BoardResponse(

        int id,
        String title,
        String content,
        int viewCount,
        LocalDateTime creatAt
) {

    public static BoardResponse from(Board entity) {
        return new BoardResponse(entity.getId(), entity.getTitle(), entity.getContent(), entity.getViewCount(), entity.getCreatedAt());
    }
}
