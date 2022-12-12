package ru.otus.spring.garussky.dao;

import ru.otus.spring.garussky.domain.Question;

import java.util.List;

/**
 * DAO по работе с вопросами тестирования
 */
public interface QuestionDao {

    /**
     * Возвращает все вопросы для тестирования
     * @return все вопросы для тестирования
     */
    List<Question> findAll();
}
