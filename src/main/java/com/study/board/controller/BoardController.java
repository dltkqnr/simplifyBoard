package com.study.board.controller;

import com.study.board.dto.BoardSimpleResponse;
import com.study.board.dto.BoardRequest;
import com.study.board.dto.BoardResponse;
import com.study.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String home(Model model){
        List<BoardSimpleResponse> responses = boardService.getBoards();
        model.addAttribute("boards", responses);
        return "home";
    }

    @GetMapping("/board-form")
    public String board(@ModelAttribute("board") BoardRequest request) {
        return "board-form";
    }

    @PostMapping("/boards")
    public String create(@ModelAttribute BoardRequest request) {
        boardService.create(request);
        return "redirect:/";
    }

    @GetMapping("/boards/{id}")
    public String getBoard(@PathVariable int id, Model model) {
        BoardResponse boardDetail = boardService.getBoard(id);
        model.addAttribute("boardDetail", boardDetail);
        return "detail";
    }
    @GetMapping("/boards/update/{id}")
    public String getUpdateBoard(@PathVariable int id, Model model){
        BoardResponse board = boardService.getBoard(id);
        model.addAttribute("boardInfo", board);
        return "board-update-form";
    }

    @PutMapping("/boards/update/{id}")
    public String update(@PathVariable int id, @ModelAttribute BoardRequest request){
        boardService.update(id,request);

        return "redirect:/boards/"+id;
    }

    @DeleteMapping("boards/delete/{id}")
    public String delete(@PathVariable int id){
        boardService.delete(id);
        return "redirect:/";
    }

}
