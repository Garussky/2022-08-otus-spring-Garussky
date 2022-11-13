package ru.otus.spring.garussky.service;

import ru.otus.spring.garussky.domain.Answer;
import ru.otus.spring.garussky.domain.Exam;
import ru.otus.spring.garussky.domain.Question;
import ru.otus.spring.garussky.domain.User;

public interface CommunicationService {
    User getUser();
    void askQuestion(Question question, boolean mixAnswers);
    Answer getAnswer(Question question, int attempts);
    void printResult(Exam exam);
}
