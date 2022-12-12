package ru.otus.spring.garussky.domain;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Answer {
    private int id;

    private String text;

    public Answer() {
        super();
    }

    public Answer(int id, String text) {
        this.id = id;
        this.text = text;
    }
}
