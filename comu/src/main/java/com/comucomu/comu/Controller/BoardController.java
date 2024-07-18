package com.comucomu.comu.Controller;

import com.comucomu.comu.DTO.BoardResponse;
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
    public ResponseEntity<List<BoardResponse>> findAllByCate(@RequestParam(value="no") String no){
        List<BoardResponse> boards = boardService.findAllByCategory(Integer.parseInt(no))
                .stream()
                .map(BoardResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(boards);
    }

}
