package com.example.backendlearn.quiz;

// クイズのカテゴリ一覧。
// 文字列(String)ではなくenumにすることで、
// "git" と "Git" のような表記ゆれや、存在しないカテゴリ名を
// コンパイルの段階/変換の段階で防げる。
public enum QuizCategory {
    GIT,
    DOCKER,
    JAVA,
    LINUX,
    AWS
}
