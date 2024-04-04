package com.comucomu.comu.Controller;

import com.comucomu.comu.DTO.AddBoardRequest;
import com.comucomu.comu.DTO.UpdateBoardReqeust;
import com.comucomu.comu.Repository.BoardRepository;
import com.comucomu.comu.entity.Board;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;    // 직렬화, 역직렬화를 위한 클래스

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BoardRepository boardRepository;

    @BeforeEach
    public void mockMvcSet(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();

        boardRepository.deleteAll();
    }

    @DisplayName("글 저장에 성공한다.")
    @Test
    public void addBoard() throws Exception {
        //given
        final String url = "/board/save";
        final String title = "새글";
        final String content = "새로운 내용";

        final AddBoardRequest userRequest = new AddBoardRequest(title, content);

        //객체 JSON으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        //when
        // 요청 전송
        ResultActions resultActions = mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then
        resultActions.andExpect(status().isCreated());

        List<Board> boardList = boardRepository.findAll();

        assertEquals(boardList.size(), 1L);
        assertEquals(boardList.get(0).getTitle(), title);
        assertEquals(boardList.get(0).getContent(), content);
    }

    @DisplayName("글 목록 전체 조회에 성공한다.")
    @Test
    public void findAllBoards() throws Exception {
        // given
        final String url = "/board/searchAll";
        final String title = "title";
        final String content = "content";

        boardRepository.save(Board.builder()
                .title(title)
                .content(content)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));

    }

    @DisplayName("글 단건 조회")
    @Test
    public void findBoard() throws Exception{
        // given
        final String url = "/board/search/{no}";
        final String title = "title";
        final String content = "content";

        Board board = boardRepository.save(Board.builder()
                .title(title)
                .content(content)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url, board.getNo()));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));
    }

    @DisplayName("게시글 삭제")
    @Test
    public void deleteBoard() throws Exception{
        // given
        final String url = "/board/delete/{no}";
        final String title = "title";
        final String content = "content";

        Board board = boardRepository.save(Board.builder()
                .title(title)
                .content(content)
                .build());

        // when
        mockMvc.perform(delete(url, board.getNo()))
                .andExpect(status().isOk());

        // then
        List<Board> boardList = boardRepository.findAll();

        Assertions.assertEquals(boardList.size(), 0);
    }

    @DisplayName("게시글 수정")
    @Test
    public void updateBoard() throws Exception{
        // given
        final String url = "/board/update/{no}";
        final String title = "title";
        final String content = "content";

        Board board = boardRepository.save(Board.builder()
                .title(title)
                .content(content)
                .build());

        final String newTitle = "new title";
        final String newContent = "new content";

        UpdateBoardReqeust updateBoardReqeust = new UpdateBoardReqeust(newTitle, newContent);

        // when
        ResultActions resultActions = mockMvc.perform(put(url, board.getNo())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(updateBoardReqeust)));

        // then
        resultActions.andExpect(status().isOk());

        Board findBoard = boardRepository.findById(board.getNo()).get();

        assertEquals(findBoard.getTitle(), newTitle);
        assertEquals(findBoard.getContent(), newContent);
    }

}