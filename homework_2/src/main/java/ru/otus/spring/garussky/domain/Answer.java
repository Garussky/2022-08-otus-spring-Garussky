package ru.otus.spring.garussky.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Answer {

    private final Question question;

    private final int answer;

    public boolean isCorrectAnswer() {
        return question.getRightAnswerIndex() == answer;
    }
}
