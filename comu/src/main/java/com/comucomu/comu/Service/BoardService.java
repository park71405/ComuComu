package com.comucomu.comu.Service;

import com.comucomu.comu.DTO.Board.BoardAddRequest;
import com.comucomu.comu.DTO.Board.BoardResponse;
import com.comucomu.comu.Repository.BoardRepository;
import com.comucomu.comu.Repository.BoardRepositoryCustom;
import com.comucomu.comu.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardRepositoryCustom boardRepositoryCustom;

    // 특정 카테고리의 게시글 전체 조회
    public List<BoardResponse> findAllByCategory(int no, int page){

        Pageable pageable = PageRequest.of(page-1, 5, Sort.by(Sort.Direction.DESC, "reg_date"));

        return boardRepositoryCustom.findByCategoryNo(no, pageable);
    }

    // 특정 카테고리의 게시글 개수 조회
    public int getTotalPage(int category_no){

        return boardRepositoryCustom.getTotalPage(category_no);
    }

    // 특정 카테고리의 게시글 추가
    public int addBoard(BoardAddRequest request){
        return boardRepository.save(request.toEntity()).getNo();
    }

}
