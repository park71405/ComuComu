package com.comucomu.comu.Controller;

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


}