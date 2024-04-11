package com.comucomu.comu.Controller;

import com.comucomu.comu.DTO.BoardListViewResponse;
import com.comucomu.comu.DTO.BoardViewResponse;
import com.comucomu.comu.Service.BoardService;
import com.comucomu.comu.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardViewController {

    private final BoardService boardService;

    // 게시글 전체 조회
    @GetMapping("/boards")
    public String getBoards(Model model){
        List<BoardListViewResponse> boards = boardService.findAll().stream()
                .map(BoardListViewResponse::new)
                .toList();

        model.addAttribute("boards", boards);

        return "boardList";
    }
    
    // 게시글 단건 조회
    @GetMapping("/board/{no}")
    public String getBoard(@PathVariable int no, Model model){
        Board board = boardService.finadById(no);

        model.addAttribute("board", new BoardViewResponse(board));

        return "board";
    }

    // 게시글 수정
    @GetMapping("/newboard")
    public String newBoard(@RequestParam(required = false) Integer no, Model model){
        if(no == null){
            model.addAttribute("board", new BoardViewResponse());
        }else{
            Board board = boardService.finadById(no);
            model.addAttribute("board", new BoardViewResponse(board));
        }

        return "newBoard";
    }

}
