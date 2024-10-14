package com.comucomu.comu.Service;

import com.comucomu.comu.DTO.Board.BoardAddRequest;
import com.comucomu.comu.DTO.Board.BoardDetailResponse;
import com.comucomu.comu.DTO.Board.BoardFileAddRequest;
import com.comucomu.comu.DTO.Board.BoardResponse;
import com.comucomu.comu.Repository.BoardRepository;
import com.comucomu.comu.Repository.BoardRepositoryCustom;
import com.comucomu.comu.Repository.FileRepository;
import com.comucomu.comu.Util.FileUtil;
import com.comucomu.comu.entity.Board;
import com.comucomu.comu.entity.File;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardRepositoryCustom boardRepositoryCustom;
    private final FileUtil fileUtil;
    private final FileRepository fileRepository;

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
    @Transactional
    public int addBoard(BoardAddRequest request, List<MultipartFile> fileList) throws Exception {

        // 게시글 저장
        int boardNo = boardRepository.save(request.toEntity()).getNo();

        // 파일 저장 로직
        List<BoardFileAddRequest> boardFileAddRequestList = fileUtil.storeFile(fileList, boardNo);

        fileRepository.saveAll(boardFileAddRequestList.stream()
                                                        .map(boardFileAddRequest -> File.builder()
                                                                                        .path(boardFileAddRequest.getPath())
                                                                                        .originalName(boardFileAddRequest.getOriginalName())
                                                                                        .boardNo(boardFileAddRequest.getBoardNo())
                                                                                        .size(boardFileAddRequest.getSize())
                                                                                        .build())
                                                        .collect(Collectors.toList()));

        return boardNo;
    }

    // board_no 기반 게시글 조회
    public BoardDetailResponse findBoardDetailByBoardno(int boardNo){
        return boardRepository.findById(boardNo)
                .map(BoardDetailResponse::new)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. boardNo = " + boardNo));
    }

}
