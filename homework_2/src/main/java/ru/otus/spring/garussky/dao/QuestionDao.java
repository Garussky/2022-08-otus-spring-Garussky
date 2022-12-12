package ru.otus.spring.garussky.dao;

import ru.otus.spring.garussky.domain.Question;

import java.util.Set;

public interface QuestionDao {
    Set<Question> findAll();
}
