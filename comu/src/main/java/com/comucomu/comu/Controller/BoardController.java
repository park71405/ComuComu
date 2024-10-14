package com.comucomu.comu.Controller;

import com.comucomu.comu.DTO.Board.BoardAddRequest;
import com.comucomu.comu.DTO.Board.BoardDetailResponse;
import com.comucomu.comu.DTO.Board.BoardResponse;
import com.comucomu.comu.DTO.File.FileResponse;
import com.comucomu.comu.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;


    // 특정 카테고리의 게시글 전체 조회
    @GetMapping("/board/searchAllByCate")
    public ResponseEntity<List<BoardResponse>> findAllByCate(@RequestParam(value="no") String no, @RequestParam(value="page") String page){

        // 게시글 내역 조회
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

    // 특정 카테고리의 게시글 추가
    @PostMapping("/board")
    public int AddBoard(@RequestPart(value="boardForm") BoardAddRequest boardAddRequest
            , @RequestPart(value="fileList", required = false) List<MultipartFile> fileList) throws Exception {
        
        return boardService.addBoard(boardAddRequest, fileList);
    }

    // 특정 게시글이 가진 파일 정보 및 게시글 정보 불러오기
    @GetMapping("/searchBoardDetail")
    public ResponseEntity<List<BoardDetailResponse>> getBoardDetail(@RequestParam(value="no") String no){

        // 게시판 정보 불러오기
        BoardDetailResponse boardDetailResponse = boardService.findBoardDetailByBoardno(Integer.parseInt(no));
        
        // 파일 정보 불러오기
        List<FileResponse> fileResponseList = null;

        return ResponseEntity.ok()
                .body(null);
    }

}
