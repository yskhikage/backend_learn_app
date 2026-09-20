package com.example.backendlearn.quiz;

import java.util.List;

// クイズの問題データ本体。
// 本来はデータベースに保存するのが一般的だが、
// 最初のステップではDB接続の複雑さを持ち込まず、
// 「APIがJSONを返す」ことに集中するため、まずはJavaコード内に固定で持たせる。
// (DB連携は後のタスクで追加する想定)
public final class QuizData {

    // インスタンス化させないためのprivateコンストラクタ。
    // このクラスは定数(QUIZZES)を持つためだけに存在する。
    private QuizData() {
    }

    public static final List<Quiz> QUIZZES = List.of(
            new Quiz(
                    1,
                    QuizCategory.GIT,
                    "変更内容をコミット対象(ステージング)に追加するコマンドはどれ?",
                    List.of("git add", "git commit", "git push", "git fetch"),
                    0
            ),
            new Quiz(
                    2,
                    QuizCategory.GIT,
                    "リモートリポジトリの変更を取得し、かつ現在のブランチに取り込むコマンドはどれ?",
                    List.of("git fetch", "git pull", "git clone", "git status"),
                    1
            ),
            new Quiz(
                    3,
                    QuizCategory.GIT,
                    "作業中のブランチを切り替えるコマンドはどれ?",
                    List.of("git branch", "git merge", "git switch", "git log"),
                    2
            ),
            new Quiz(
                    4,
                    QuizCategory.DOCKER,
                    "Dockerイメージからコンテナを起動するコマンドはどれ?",
                    List.of("docker build", "docker run", "docker pull", "docker images"),
                    1
            ),
            new Quiz(
                    5,
                    QuizCategory.DOCKER,
                    "Dockerfileに書かれた手順を元にイメージを作成するコマンドはどれ?",
                    List.of("docker build", "docker run", "docker exec", "docker ps"),
                    0
            ),
            new Quiz(
                    6,
                    QuizCategory.DOCKER,
                    "実行中のコンテナ一覧を表示するコマンドはどれ?",
                    List.of("docker ps", "docker images", "docker logs", "docker stop"),
                    0
            ),
            new Quiz(
                    7,
                    QuizCategory.JAVA,
                    "Spring Bootでコントローラークラスに付けるアノテーションはどれ?",
                    List.of("@Entity", "@Repository", "@RestController", "@Configuration"),
                    2
            ),
            new Quiz(
                    8,
                    QuizCategory.JAVA,
                    "Javaでイミュータブルなデータクラスを簡潔に定義できるキーワードはどれ?",
                    List.of("class", "record", "interface", "enum"),
                    1
            ),
            new Quiz(
                    9,
                    QuizCategory.JAVA,
                    "Mavenでビルドやテストを実行する際に使う設定ファイルはどれ?",
                    List.of("build.gradle", "package.json", "pom.xml", "requirements.txt"),
                    2
            ),
            new Quiz(
                    10,
                    QuizCategory.LINUX,
                    "現在のディレクトリにあるファイル一覧を表示するコマンドはどれ?",
                    List.of("ls", "cd", "pwd", "cat"),
                    0
            ),
            new Quiz(
                    11,
                    QuizCategory.LINUX,
                    "ファイルの内容をターミナルに表示するコマンドはどれ?",
                    List.of("mkdir", "cat", "rm", "touch"),
                    1
            ),
            new Quiz(
                    12,
                    QuizCategory.LINUX,
                    "ファイルやディレクトリのアクセス権限を変更するコマンドはどれ?",
                    List.of("chmod", "chown", "grep", "find"),
                    0
            ),
            new Quiz(
                    13,
                    QuizCategory.AWS,
                    "静的なファイル(画像やHTMLなど)を保存するAWSのストレージサービスはどれ?",
                    List.of("EC2", "S3", "RDS", "Lambda"),
                    1
            ),
            new Quiz(
                    14,
                    QuizCategory.AWS,
                    "サーバーを構築せずにコードを実行できるAWSのサービスはどれ?",
                    List.of("Lambda", "EC2", "VPC", "IAM"),
                    0
            ),
            new Quiz(
                    15,
                    QuizCategory.AWS,
                    "AWS上で仮想サーバーを起動できるサービスはどれ?",
                    List.of("S3", "IAM", "EC2", "CloudFront"),
                    2
            )
    );
}
