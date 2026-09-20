// クイズ画面の動きを担当するスクリプト。
// フレームワークは使わず素のJavaScriptで書いている。
// 理由: このアプリは「バックエンドを学ぶ」ことが目的で、
// フロントエンドの学習が主目的ではないため、
// ビルド手順(npmなど)を増やさずシンプルに保つ。

// カテゴリの表示情報。
// value はAPIの category パラメータに渡す値(バックエンドのenum名と対応)。
// nullは「すべて」を意味し、その場合はcategoryパラメータを付けずにリクエストする。
const CATEGORIES = [
    { value: null, label: "すべて", icon: "📚" },
    { value: "git", label: "Git", icon: "🔧" },
    { value: "docker", label: "Docker", icon: "🐳" },
    { value: "java", label: "Java", icon: "☕" },
    { value: "linux", label: "Linux", icon: "🐧" },
    { value: "aws", label: "AWS", icon: "☁️" },
];

// 今どのカテゴリで、何問目で、何問正解したかをまとめて持っておく状態。
// グローバル変数1つにまとめることで、画面のどの部分からでも
// 「今の状態」を参照・更新できるようにしている(単純なアプリなので状態管理ライブラリは使わない)。
const state = {
    category: null,
    quizzes: [],
    currentIndex: 0,
    correctCount: 0,
    answered: false,
};

const screens = {
    category: document.getElementById("category-screen"),
    quiz: document.getElementById("quiz-screen"),
    result: document.getElementById("result-screen"),
};

function showScreen(name) {
    for (const key of Object.keys(screens)) {
        screens[key].hidden = key !== name;
    }
}

function renderCategoryScreen() {
    const list = document.getElementById("category-list");
    list.innerHTML = "";
    for (const category of CATEGORIES) {
        const card = document.createElement("button");
        card.type = "button";
        card.className = "category-card";
        card.innerHTML = `
            <span class="category-card__icon">${category.icon}</span>
            <span class="category-card__label">${category.label}</span>
        `;
        card.addEventListener("click", () => startQuiz(category));
        list.appendChild(card);
    }
}

async function startQuiz(category) {
    const url = category.value
        ? `/api/quizzes?category=${encodeURIComponent(category.value)}`
        : "/api/quizzes";

    const response = await fetch(url);
    const quizzes = await response.json();

    state.category = category;
    state.quizzes = quizzes;
    state.currentIndex = 0;
    state.correctCount = 0;

    showScreen("quiz");
    renderQuestion();
}

function renderQuestion() {
    state.answered = false;

    const quiz = state.quizzes[state.currentIndex];
    const total = state.quizzes.length;

    document.getElementById("quiz-category-badge").textContent = state.category.label;
    document.getElementById("quiz-progress").textContent = `${state.currentIndex + 1} / ${total}`;
    document.getElementById("progress-bar-fill").style.width =
        `${((state.currentIndex + 1) / total) * 100}%`;

    document.getElementById("question-text").textContent = quiz.question;

    const feedback = document.getElementById("feedback-text");
    feedback.hidden = true;
    feedback.className = "feedback-text";

    document.getElementById("explanation-box").hidden = true;

    const nextButton = document.getElementById("next-button");
    nextButton.hidden = true;

    const choiceList = document.getElementById("choice-list");
    choiceList.innerHTML = "";
    quiz.choices.forEach((choiceText, index) => {
        const button = document.createElement("button");
        button.type = "button";
        button.className = "choice-button";
        button.textContent = choiceText;
        button.addEventListener("click", () => handleChoiceClick(index));
        choiceList.appendChild(button);
    });
}

function handleChoiceClick(selectedIndex) {
    if (state.answered) {
        return;
    }
    state.answered = true;

    const quiz = state.quizzes[state.currentIndex];
    const isCorrect = selectedIndex === quiz.answerIndex;
    if (isCorrect) {
        state.correctCount += 1;
    }

    const buttons = document.querySelectorAll(".choice-button");
    buttons.forEach((button, index) => {
        button.disabled = true;
        if (index === quiz.answerIndex) {
            button.classList.add("is-correct");
        } else if (index === selectedIndex) {
            button.classList.add("is-wrong");
        }
    });

    const feedback = document.getElementById("feedback-text");
    feedback.hidden = false;
    feedback.textContent = isCorrect ? "正解です!" : "残念、不正解です";
    feedback.classList.add(isCorrect ? "is-correct" : "is-wrong");

    // 正誤だけで終わらせず、なぜその答えなのかという理由まで見てもらうことで
    // 実務で使える理解につなげる。
    document.getElementById("explanation-text").textContent = quiz.explanation;
    document.getElementById("explanation-box").hidden = false;

    document.getElementById("next-button").hidden = false;
}

function goToNextQuestion() {
    const isLastQuestion = state.currentIndex >= state.quizzes.length - 1;
    if (isLastQuestion) {
        showResult();
        return;
    }
    state.currentIndex += 1;
    renderQuestion();
}

function showResult() {
    const total = state.quizzes.length;
    const correct = state.correctCount;
    const rate = total === 0 ? 0 : Math.round((correct / total) * 100);

    let emoji = "🙂";
    let message = "お疲れさまでした!";
    if (rate === 100) {
        emoji = "🎉";
        message = "全問正解、すばらしいです!";
    } else if (rate >= 60) {
        emoji = "😊";
        message = "いい調子です!";
    } else {
        emoji = "📖";
        message = "もう一度復習してみましょう";
    }

    document.getElementById("result-emoji").textContent = emoji;
    document.getElementById("result-score").textContent = `${total}問中 ${correct}問正解(${rate}%)`;
    document.getElementById("result-message").textContent = message;

    showScreen("result");
}

document.getElementById("next-button").addEventListener("click", goToNextQuestion);
document.getElementById("retry-button").addEventListener("click", () => startQuiz(state.category));
document.getElementById("back-button").addEventListener("click", () => {
    showScreen("category");
});

renderCategoryScreen();
showScreen("category");
