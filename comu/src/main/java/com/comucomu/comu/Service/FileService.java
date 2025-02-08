package com.comucomu.comu.Service;

import com.comucomu.comu.DTO.Board.BoardResponse;
import com.comucomu.comu.DTO.File.FileResponse;
import com.comucomu.comu.Repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FileService {

    private final FileRepository fileRepository;

    // board_no를 바탕으로 파일 정보 탐색
    public List<FileResponse> findFileByBoardNo(int board_no){
        //fileRepository.findByBoardNo(board_no);
        return null;
    }

}
