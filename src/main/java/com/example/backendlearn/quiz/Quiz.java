package com.example.backendlearn.quiz;

import java.util.List;

// クイズ1問分のデータを表す入れ物(DTO)。
// recordを使うと、フィールド定義だけでgetterやコンストラクタが自動生成され、
// かつ値を後から書き換えられない(イミュータブル)ものになる。
// クイズの問題文のような「一度作ったら変わらないデータ」に向いている。
public record Quiz(
        long id,
        QuizCategory category,
        String question,
        List<String> choices,
        int answerIndex
) {
}
