package ru.otus.spring.garussky.domain;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Question {
    private int id;

    private String text;
}
