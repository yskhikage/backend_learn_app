-- アプリ起動時にDBへ投入する初期データ(クイズの問題)。
-- IDを固定で書いているのは、アプリを再起動するたびにこのSQLが実行されても
-- (ON CONFLICTで)同じ行が重複して増えていかないようにするため。
--
-- 本来はFlywayなどのマイグレーションツールで「変更履歴」として管理するのが望ましいが、
-- 学習アプリの現段階ではシンプルさを優先し、Spring Boot標準のdata.sql機能を使っている
-- (今後のタスクとしてFlyway化を検討する)。

INSERT INTO quizzes (id, category, question, answer_index) VALUES
    (1, 'GIT', '変更内容をコミット対象(ステージング)に追加するコマンドはどれ?', 0),
    (2, 'GIT', 'リモートリポジトリの変更を取得し、かつ現在のブランチに取り込むコマンドはどれ?', 1),
    (3, 'GIT', '作業中のブランチを切り替えるコマンドはどれ?', 2),
    (4, 'DOCKER', 'Dockerイメージからコンテナを起動するコマンドはどれ?', 1),
    (5, 'DOCKER', 'Dockerfileに書かれた手順を元にイメージを作成するコマンドはどれ?', 0),
    (6, 'DOCKER', '実行中のコンテナ一覧を表示するコマンドはどれ?', 0),
    (7, 'JAVA', 'Spring Bootでコントローラークラスに付けるアノテーションはどれ?', 2),
    (8, 'JAVA', 'Javaでイミュータブルなデータクラスを簡潔に定義できるキーワードはどれ?', 1),
    (9, 'JAVA', 'Mavenでビルドやテストを実行する際に使う設定ファイルはどれ?', 2),
    (10, 'LINUX', '現在のディレクトリにあるファイル一覧を表示するコマンドはどれ?', 0),
    (11, 'LINUX', 'ファイルの内容をターミナルに表示するコマンドはどれ?', 1),
    (12, 'LINUX', 'ファイルやディレクトリのアクセス権限を変更するコマンドはどれ?', 0),
    (13, 'AWS', '静的なファイル(画像やHTMLなど)を保存するAWSのストレージサービスはどれ?', 1),
    (14, 'AWS', 'サーバーを構築せずにコードを実行できるAWSのサービスはどれ?', 0),
    (15, 'AWS', 'AWS上で仮想サーバーを起動できるサービスはどれ?', 2)
ON CONFLICT (id) DO NOTHING;

INSERT INTO quiz_choices (quiz_id, choice_order, choice_text) VALUES
    (1, 0, 'git add'), (1, 1, 'git commit'), (1, 2, 'git push'), (1, 3, 'git fetch'),
    (2, 0, 'git fetch'), (2, 1, 'git pull'), (2, 2, 'git clone'), (2, 3, 'git status'),
    (3, 0, 'git branch'), (3, 1, 'git merge'), (3, 2, 'git switch'), (3, 3, 'git log'),
    (4, 0, 'docker build'), (4, 1, 'docker run'), (4, 2, 'docker pull'), (4, 3, 'docker images'),
    (5, 0, 'docker build'), (5, 1, 'docker run'), (5, 2, 'docker exec'), (5, 3, 'docker ps'),
    (6, 0, 'docker ps'), (6, 1, 'docker images'), (6, 2, 'docker logs'), (6, 3, 'docker stop'),
    (7, 0, '@Entity'), (7, 1, '@Repository'), (7, 2, '@RestController'), (7, 3, '@Configuration'),
    (8, 0, 'class'), (8, 1, 'record'), (8, 2, 'interface'), (8, 3, 'enum'),
    (9, 0, 'build.gradle'), (9, 1, 'package.json'), (9, 2, 'pom.xml'), (9, 3, 'requirements.txt'),
    (10, 0, 'ls'), (10, 1, 'cd'), (10, 2, 'pwd'), (10, 3, 'cat'),
    (11, 0, 'mkdir'), (11, 1, 'cat'), (11, 2, 'rm'), (11, 3, 'touch'),
    (12, 0, 'chmod'), (12, 1, 'chown'), (12, 2, 'grep'), (12, 3, 'find'),
    (13, 0, 'EC2'), (13, 1, 'S3'), (13, 2, 'RDS'), (13, 3, 'Lambda'),
    (14, 0, 'Lambda'), (14, 1, 'EC2'), (14, 2, 'VPC'), (14, 3, 'IAM'),
    (15, 0, 'S3'), (15, 1, 'IAM'), (15, 2, 'EC2'), (15, 3, 'CloudFront')
ON CONFLICT (quiz_id, choice_order) DO NOTHING;
