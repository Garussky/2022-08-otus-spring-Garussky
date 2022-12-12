package ru.otus.spring.garussky.service;

import ru.otus.spring.garussky.domain.Question;

import java.util.Set;

public interface QuestionService {
    Set<Question> getQuestions();

    void printQuestions();
}
