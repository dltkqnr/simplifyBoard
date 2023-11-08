package com.study.board.service;

import com.study.board.domain.Board;
import com.study.board.dto.BoardRequest;
import com.study.board.dto.BoardResponse;
import com.study.board.dto.BoardSimpleResponse;
import com.study.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void create(BoardRequest request) {
        Board board = request.toEntity();
        boardRepository.save(board);
    }

    public List<BoardSimpleResponse> getBoards() {
        return boardRepository.findAll()
                .stream()
                .map(BoardSimpleResponse::from)
                .toList();
    }

    public BoardResponse getBoard(int id) {
        return boardRepository.findById(id)
                .map(BoardResponse::from)
                .orElseThrow(()-> new IllegalArgumentException("게시글을 찾을 수 없습니다: " + id));
    }

    @Transactional
    public void update(int id, BoardRequest request) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("게시글을 찾을 수 없습니다: " + id));
        board.update(request.title(), request.content());
    }

    @Transactional
    public void delete(int id) {
        boardRepository.deleteById(id);
    }


    public Page<BoardResponse> getPageList(Pageable pageable){
        Page<Board> pageList = boardRepository.findAll(pageable);
        return pageList.map(BoardResponse::from);
    }
}

