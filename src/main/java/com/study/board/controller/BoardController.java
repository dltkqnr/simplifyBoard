package com.study.board.controller;

import com.study.board.dto.BoardRequest;
import com.study.board.dto.BoardResponse;
import com.study.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final int PAGE_LENGTH = 3;

    @GetMapping("/")
    public String home(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)Pageable pageable, Model model){

        Page<BoardResponse> pages = boardService.getPageList(pageable);

        int nowPage = pages.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - PAGE_LENGTH,1);
        int endPage = Math.min(nowPage + PAGE_LENGTH, pages.getTotalPages());

        model.addAttribute("pageList", pages);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

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
