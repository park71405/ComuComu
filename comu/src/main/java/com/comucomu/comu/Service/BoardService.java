package com.comucomu.comu.Service;

import com.comucomu.comu.DTO.AddBoardRequest;
import com.comucomu.comu.Repository.BoardRepository;
import com.comucomu.comu.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 추가
    public Board save(AddBoardRequest request){
        return boardRepository.save(request.toEntity());
    }

}
