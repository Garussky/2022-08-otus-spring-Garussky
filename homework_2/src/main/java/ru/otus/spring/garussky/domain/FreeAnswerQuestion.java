package ru.otus.spring.garussky.domain;

import lombok.ToString;

@ToString
public class FreeAnswerQuestion extends Question {

    public FreeAnswerQuestion() {
        super();
    }

    public FreeAnswerQuestion(int id, String text) {
        super(id, text);
    }
}
