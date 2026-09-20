package com.example.backendlearn.quiz;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// DB(quizzesテーブル)へのアクセスを担当するインターフェース。
// JpaRepositoryを継承するだけで、findAll()やsave()などの基本的な処理は
// Spring Data JPAが実装を自動生成してくれる。
// findByCategoryのようなメソッド名も、命名規則に沿って書くだけで
// 「categoryカラムで絞り込むSELECT文」を自動生成してくれる。
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByCategory(QuizCategory category);
}
