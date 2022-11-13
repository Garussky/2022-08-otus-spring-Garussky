package ru.otus.spring.garussky.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Exam {

    private final int requiredRightAnswers;

    private final boolean mixAnswers;

    private final int questionAttempts;

    private User user;

    private List<Question> questions;

    private final List<Answer> answers = new ArrayList<>();

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public long getRightAnswersCount() {
        return answers.stream()
                .filter(Answer::isCorrectAnswer)
                .count();
    }

    public boolean isExamPassed() {
        return requiredRightAnswers <= getRightAnswersCount();
    }
}
