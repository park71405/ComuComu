package com.comucomu.comu.Controller;

import com.comucomu.comu.DTO.AddBoardRequest;
import com.comucomu.comu.DTO.BoardResponse;
import com.comucomu.comu.DTO.UpdateBoardReqeust;
import com.comucomu.comu.Service.BoardService;
import com.comucomu.comu.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    // 게시글 저장
    @PostMapping("/board/save")
    public ResponseEntity<Board> addBoard(@RequestBody AddBoardRequest request){
        Board savedBoard = boardService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedBoard);
    }

    // 게시글 전체 조회
    @GetMapping("/board/searchAll")
    public ResponseEntity<List<BoardResponse>> findAllBoard(){
        List<BoardResponse> boards = boardService.findAll()
                .stream()
                .map(BoardResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(boards);
    }

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

    // 게시글 단건 조회
    @GetMapping("/board/search/{no}")
    public ResponseEntity<BoardResponse> findBoard(@PathVariable int no){
        Board board = boardService.finadById(no);

        return ResponseEntity.ok()
                .body(new BoardResponse(board));
    }

    // 게시글 삭제
    @DeleteMapping("/board/delete/{no}")
    public ResponseEntity<Void> deleteBoard(@PathVariable int no){
        boardService.delete(no);

        return ResponseEntity.ok()
                .build();
    }

    // 게시글 수정
    @PutMapping("/board/update/{no}")
    public ResponseEntity<Board> updateBoard(@PathVariable int no, @RequestBody UpdateBoardReqeust request){
        Board board = boardService.update(no, request);

        return ResponseEntity.ok()
                .body(board);
    }

}
