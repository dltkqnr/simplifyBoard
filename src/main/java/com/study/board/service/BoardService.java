package com.study.board.service;

import com.study.board.domain.Board;
import com.study.board.dto.BoardRequest;
import com.study.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public void create(BoardRequest request) {
        Board board = request.toEntity();
        boardRepository.save(board);
    }
}
