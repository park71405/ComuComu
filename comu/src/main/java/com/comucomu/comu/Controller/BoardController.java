package com.comucomu.comu.Controller;

import com.comucomu.comu.DTO.Board.BoardResponse;
import com.comucomu.comu.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    // 특정 카테고리의 게시글 전체 조회
    @GetMapping("/board/searchAllByCate")
    public ResponseEntity<List<BoardResponse>> findAllByCate(@RequestParam(value="no") String no, @RequestParam(value="page") String page){
        List<BoardResponse> boards = boardService.findAllByCategory(Integer.parseInt(no), Integer.parseInt(page))
                .stream()
                .toList();

        return ResponseEntity.ok()
                .body(boards);
    }

    // 특정 카테고리의 게시글 개수 조회
    @GetMapping("/board/getTotalPage")
    public int getTotalPage(@RequestParam(value="categoryNo") String categoryNo){

        return boardService.getTotalPage(Integer.parseInt(categoryNo));
    }

}
