package com.comucomu.comu.Service;

import com.comucomu.comu.DTO.AddBoardRequest;
import com.comucomu.comu.DTO.UpdateBoardReqeust;
import com.comucomu.comu.Repository.BoardRepository;
import com.comucomu.comu.entity.Board;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 추가
    public Board save(AddBoardRequest request){
        return boardRepository.save(request.toEntity());
    }

    // 게시글 전체 조회
    public List<Board> findAll(){
        return boardRepository.findAll();
    }

    // 게시글 단건 조회
    public Board finadById(long no){
        return boardRepository.findById(no)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + no));
    }

    // 게시글 삭제
    public void delete(long no){
        boardRepository.deleteById(no);
    }

    // 게시글 수정
    @Transactional
    public Board update(long no, UpdateBoardReqeust request){
        Board board = boardRepository.findById(no)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + no));

        board.update(request.getTitle(), request.getContent());

        return board;
    }
}
