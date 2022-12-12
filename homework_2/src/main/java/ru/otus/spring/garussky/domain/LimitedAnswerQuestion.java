package ru.otus.spring.garussky.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@ToString
@Getter
public class LimitedAnswerQuestion extends Question {
    private final Answer answer;
    private final Set<Answer> possibleAnswers;

    public LimitedAnswerQuestion(int id, String text, Answer answer, Set<Answer> possibleAnswers) {
        super(id, text);
        this.answer = answer;
        this.possibleAnswers = possibleAnswers;
    }
}
