package com.comucomu.comu.Controller;

import com.comucomu.comu.Repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper; // 직렬화, 역직렬화를 위한 클래스

    @Autowired
    private WebApplicationContext context;

    @Autowired
    CategoryRepository categoryRepository;

    @DisplayName("카테고리 목록 전체 조회에 성공한다.")
    @Test
    void findAllCategory() throws Exception {
        // given
        final String url = "/cate/searchAll";

        // when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk());

    }

    @DisplayName("카테고리 권한별 조회에 성공한다.")
    @Test
    void postFindAllCategory() throws Exception {

        // given
        final String url = "/cate/searchAll";

        // when
        final ResultActions resultActions = mockMvc.perform(get(url))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk());
    }

}