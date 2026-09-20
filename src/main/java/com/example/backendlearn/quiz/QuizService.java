package com.example.backendlearn.quiz;

import java.util.List;
import org.springframework.stereotype.Service;

// クイズの取得ロジックを担当するクラス。
// 「カテゴリで絞り込む」というルールをControllerから切り離しておくことで、
// データの取得元(以前は固定リスト、今はDB)が変わっても、Controller側は変更せずに済む。
@Service
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> findQuizzes(QuizCategory category) {
        if (category == null) {
            return quizRepository.findAll();
        }
        return quizRepository.findByCategory(category);
    }
}
