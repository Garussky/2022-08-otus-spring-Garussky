package ru.otus.spring.garussky.dao;

import ru.otus.spring.garussky.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> findAll();
}
