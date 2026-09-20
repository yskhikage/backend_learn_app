package com.example.backendlearn.quiz;

import java.util.List;
import org.springframework.stereotype.Service;

// クイズの取得ロジックを担当するクラス。
// 「カテゴリで絞り込む」というルールをControllerから切り離しておくことで、
// 将来データの取得元がDBに変わっても、Controller側は変更せずに済む。
@Service
public class QuizService {

    public List<Quiz> findQuizzes(QuizCategory category) {
        if (category == null) {
            return QuizData.QUIZZES;
        }
        return QuizData.QUIZZES.stream()
                .filter(quiz -> quiz.category() == category)
                .toList();
    }
}
