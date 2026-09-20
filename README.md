# backend_learn_app

git・Docker・Java(Spring Boot)・Linux・AWS を**クイズ形式**で学べる、学習用のバックエンドアプリです。

## 技術スタック

- Java 21
- Spring Boot 3(Spring MVC / Spring Data JPA)
- PostgreSQL(Docker Composeで起動)
- Maven

## ディレクトリ構成

```
src/main/java/com/example/backendlearn/
├── BackendLearnAppApplication.java   起動クラス
└── quiz/
    ├── Quiz.java             クイズ1問分のデータ(JPAエンティティ、quizzesテーブルに対応)
    ├── QuizCategory.java     カテゴリ(GIT/DOCKER/JAVA/LINUX/AWS)
    ├── QuizRepository.java   DB(quizzesテーブル)へのアクセスを担当
    ├── QuizService.java      カテゴリで絞り込むロジック
    └── QuizController.java   REST APIのエンドポイント
src/main/resources/
├── application.yml           アプリの設定(ポート番号、DB接続情報など)
├── data.sql                  起動時にDBへ投入する初期データ(問題データ)
└── static/                   フロントエンド(HTML/CSS/JS、Spring Bootが自動配信)
    ├── index.html             画面の構造
    ├── css/style.css          見た目のスタイル
    └── js/app.js              クイズの動作ロジック
docker-compose.yml             ローカル開発用PostgreSQLの起動設定
```

各ファイルの役割や「なぜそう書くか」は `CLAUDE.md` のルールに従って
コード内のコメントで日本語で説明しています。

## 起動方法

Java 21 と Docker がインストールされていることを確認してください。

```bash
java -version
docker --version
```

### 1. PostgreSQLを起動する

```bash
docker compose up -d
```

### 2. アプリを起動する

Maven Wrapper を使うので、Mavenを別途インストールする必要はありません。

```bash
./mvnw spring-boot:run
```

起動時に `data.sql` が実行され、クイズの問題データがPostgreSQLに自動で投入されます。
(再起動しても重複して増えることはありません)

起動後、ブラウザで `http://localhost:8080` を開くとクイズ画面が表示されます。

### 停止する

```bash
docker compose down
```

DBのデータごと削除したい場合は `docker compose down -v` を実行してください。

## APIの動作確認

```bash
# gitカテゴリの問題一覧を取得
curl "http://localhost:8080/api/quizzes?category=git"

# categoryを省略すると全カテゴリの問題を返す
curl "http://localhost:8080/api/quizzes"
```

## テストの実行

テストはPostgreSQLを使わず、インメモリDB(H2)に自動で差し替えて実行されるため、
`docker compose up` していなくてもそのまま実行できます。

```bash
./mvnw test
```

## 対応カテゴリ

- `git`
- `docker`
- `java`
- `linux`
- `aws`
