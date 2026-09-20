package com.example.backendlearn.quiz;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

// @SpringBootTest + MockMvc で、実際にHTTPリクエストを送ったのと同じ形で
// コントローラーの動作を確認できる(サーバーは起動せずにテストできる)。
@SpringBootTest
@AutoConfigureMockMvc
class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void categoryを指定するとそのカテゴリの問題だけが返る() throws Exception {
        mockMvc.perform(get("/api/quizzes").param("category", "git"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)))
                .andExpect(jsonPath("$[*].category").value(everyItem(org.hamcrest.Matchers.is("GIT"))));
    }

    @Test
    void 不正なcategoryを指定すると400が返る() throws Exception {
        mockMvc.perform(get("/api/quizzes").param("category", "foo"))
                .andExpect(status().isBadRequest());
    }
}
