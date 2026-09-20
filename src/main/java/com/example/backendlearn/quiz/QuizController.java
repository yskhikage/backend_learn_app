package com.example.backendlearn.quiz;

import java.util.List;
import java.util.Locale;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// @RestController は @Controller + @ResponseBody を合わせたアノテーション。
// メソッドの戻り値をそのままJSONに変換してレスポンスにしてくれる
// (画面(HTML)を返すのではなく、データだけを返すAPI向け)。
@RestController
public class QuizController {

    private final QuizService quizService;

    // コンストラクタでQuizServiceを受け取る(コンストラクタインジェクション)。
    // Springが自動でQuizServiceのインスタンスを渡してくれるため、
    // newで自分で生成する必要がない。
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // 例: GET /api/quizzes?category=git
    // categoryは省略可能。省略した場合は全カテゴリの問題を返す。
    @GetMapping("/api/quizzes")
    public List<Quiz> getQuizzes(@RequestParam(required = false) String category) {
        QuizCategory parsedCategory = parseCategory(category);
        return quizService.findQuizzes(parsedCategory);
    }

    private QuizCategory parseCategory(String category) {
        if (category == null || category.isBlank()) {
            return null;
        }
        try {
            return QuizCategory.valueOf(category.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("不明なカテゴリです: " + category);
        }
    }

    // categoryに存在しない値(例: category=foo)が渡されたときに、
    // サーバーエラー(500)ではなく、意味の分かる400エラーを返すためのハンドラ。
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleInvalidCategory(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
