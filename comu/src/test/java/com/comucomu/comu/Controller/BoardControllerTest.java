package com.comucomu.comu.Controller;

import com.comucomu.comu.DTO.AddBoardRequest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

}