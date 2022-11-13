package ru.otus.spring.garussky.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.garussky.dao.QuestionDao;
import ru.otus.spring.garussky.domain.Exam;

@Service
public class ExamServiceImpl implements ExamService {

    private final QuestionDao questionDao;
    private final CommunicationService communicationService;
    private final Exam exam;

    public ExamServiceImpl(QuestionDao questionDao,
                           CommunicationService communicationService,
                           @Value("${examApp.requiredRightAnswers}") int requiredRightAnswers,
                           @Value("${examApp.shuffleAnswers}") boolean shuffleAnswers,
                           @Value("${examApp.questionAttempts}") int questionAttempts) {
        this.questionDao = questionDao;
        this.communicationService = communicationService;
        this.exam = new Exam(requiredRightAnswers, shuffleAnswers, questionAttempts);
    }

    @Override
    public void startExam() {
        exam.setQuestions(questionDao.findAll());
        exam.setUser(communicationService.getUser());
        exam.getQuestions().stream()
                .map(question -> {
                    communicationService.askQuestion(question, exam.isMixAnswers());
                    return communicationService.getAnswer(question, exam.getQuestionAttempts());
                })
                .forEach(exam::addAnswer);
        communicationService.printResult(exam);
    }
}
