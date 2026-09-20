package com.example.backendlearn.quiz;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;

// クイズ1問分のデータを表すJPAエンティティ。
// このクラス1つが「quizzesテーブルの1行」に対応する。
// 以前はrecordで固定データを表していたが、
// DBから読み書きする(=あとから値を変える可能性がある)ため、
// JPAが要求する通常のクラス(可変・引数なしコンストラクタが必要)に変更した。
@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuizCategory category;

    @Column(nullable = false, length = 1000)
    private String question;

    // 選択肢は「1問に対して複数」の関係になるため、
    // 専用のquiz_choicesテーブルに分けて保存する(正規化)。
    // @OrderColumnで並び順(choice_order)も一緒に保存し、
    // 取得したときに選択肢の順番が入れ替わらないようにしている。
    // data.sqlでの初期データ投入を再起動のたびに重複させないため、
    // (quiz_id, choice_order)の組み合わせに一意制約を付けている。
    @ElementCollection
    @CollectionTable(
            name = "quiz_choices",
            joinColumns = @JoinColumn(name = "quiz_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"quiz_id", "choice_order"})
    )
    @OrderColumn(name = "choice_order")
    @Column(name = "choice_text", nullable = false)
    private List<String> choices = new ArrayList<>();

    @Column(name = "answer_index", nullable = false)
    private int answerIndex;

    // JPAがDBから読み込んだデータをインスタンス化する際に必要な引数なしコンストラクタ。
    // アプリのコードから直接使うことは想定していないためprotectedにしている。
    protected Quiz() {
    }

    public Quiz(QuizCategory category, String question, List<String> choices, int answerIndex) {
        this.category = category;
        this.question = question;
        this.choices = choices;
        this.answerIndex = answerIndex;
    }

    public Long getId() {
        return id;
    }

    public QuizCategory getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getChoices() {
        return choices;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }
}
