# backend_learn_app

git・Docker・Java(Spring Boot)・Linux・AWS を**クイズ形式**で学べる、学習用のバックエンドアプリです。

## 技術スタック

- Java 21
- Spring Boot 3
- Maven

## ディレクトリ構成

```
src/main/java/com/example/backendlearn/
├── BackendLearnAppApplication.java   起動クラス
└── quiz/
    ├── Quiz.java             クイズ1問分のデータ(record)
    ├── QuizCategory.java     カテゴリ(GIT/DOCKER/JAVA/LINUX/AWS)
    ├── QuizData.java         問題データ本体(今はJavaコード内に固定で保持)
    ├── QuizService.java      カテゴリで絞り込むロジック
    └── QuizController.java   REST APIのエンドポイント
src/main/resources/
├── application.yml           アプリの設定(ポート番号など)
└── static/                   フロントエンド(HTML/CSS/JS、Spring Bootが自動配信)
    ├── index.html             画面の構造
    ├── css/style.css          見た目のスタイル
    └── js/app.js              クイズの動作ロジック
```

各ファイルの役割や「なぜそう書くか」は `CLAUDE.md` のルールに従って
コード内のコメントで日本語で説明しています。

## 起動方法

Java 21 がインストールされていることを確認してください。

```bash
java -version
```

Maven Wrapper を使うので、Mavenを別途インストールする必要はありません。

```bash
./mvnw spring-boot:run
```

起動後、ブラウザで `http://localhost:8080` を開くとクイズ画面が表示されます。

## APIの動作確認

```bash
# gitカテゴリの問題一覧を取得
curl "http://localhost:8080/api/quizzes?category=git"

# categoryを省略すると全カテゴリの問題を返す
curl "http://localhost:8080/api/quizzes"
```

## テストの実行

```bash
./mvnw test
```

## 対応カテゴリ

- `git`
- `docker`
- `java`
- `linux`
- `aws`
