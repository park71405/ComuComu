package com.comucomu.comu.Service;

import com.comucomu.comu.Repository.BoardRepository;
import com.comucomu.comu.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // 특정 카테고리의 게시글 전체 조회
    public List<Board> findAllByCategory(int no){
        //return boardRepository.findByCategoryNo(no);
        return null;
    }
}
