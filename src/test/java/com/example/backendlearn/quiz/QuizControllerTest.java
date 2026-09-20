package com.example.backendlearn.quiz;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

// @SpringBootTest + MockMvc で、実際にHTTPリクエストを送ったのと同じ形で
// コントローラーの動作を確認できる(サーバーは起動せずにテストできる)。
//
// DBを使うようになったため、本番用のdata.sql(PostgreSQL専用SQL)には頼らず、
// 各テストの実行前にQuizRepositoryで直接テスト用データを用意している。
// こうすることで、本番の問題データが増減してもテストが影響を受けない。
@SpringBootTest
@AutoConfigureMockMvc
class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuizRepository quizRepository;

    @BeforeEach
    void setUp() {
        quizRepository.deleteAll();
        quizRepository.saveAll(List.of(
                new Quiz(QuizCategory.GIT, "質問1", List.of("A", "B"), 0, "解説1"),
                new Quiz(QuizCategory.GIT, "質問2", List.of("A", "B"), 1, "解説2"),
                new Quiz(QuizCategory.DOCKER, "質問3", List.of("A", "B"), 0, "解説3")
        ));
    }

    @Test
    void categoryを指定するとそのカテゴリの問題だけが返る() throws Exception {
        mockMvc.perform(get("/api/quizzes").param("category", "git"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[*].category").value(everyItem(is("GIT"))));
    }

    @Test
    void 不正なcategoryを指定すると400が返る() throws Exception {
        mockMvc.perform(get("/api/quizzes").param("category", "foo"))
                .andExpect(status().isBadRequest());
    }
}
