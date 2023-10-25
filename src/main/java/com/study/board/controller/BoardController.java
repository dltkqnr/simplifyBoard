package com.study.board.controller;

import com.study.board.dto.BoardRequest;
import com.study.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String home(@ModelAttribute("board") BoardRequest request) {
        return "index";
    }

    @PostMapping("/boards")
    public String create(@ModelAttribute BoardRequest request) {
        boardService.create(request);
        return "redirect:/";
    }
}
