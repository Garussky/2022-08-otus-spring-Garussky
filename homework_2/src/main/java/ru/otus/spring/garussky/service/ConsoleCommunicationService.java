package ru.otus.spring.garussky.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.garussky.domain.Answer;
import ru.otus.spring.garussky.domain.Exam;
import ru.otus.spring.garussky.domain.Question;
import ru.otus.spring.garussky.domain.User;

import static java.text.MessageFormat.format;

@Service
@RequiredArgsConstructor
public class ConsoleCommunicationService implements CommunicationService {
    private final IOService ioService;

    @Override
    public User getUser() {
        ioService.print("Enter your name - ");
        String firstName = ioService.readAnswer();
        ioService.print("Enter your last name - ");
        String lastName = ioService.readAnswer();
        return new User(firstName, lastName);
    }

    @Override
    public void askQuestion(Question question, boolean mixAnswers) {
        ioService.printDelimiter();
        ioService.print("Question - " + question.getQuestion());
        ioService.print("Answers : ");
        if (mixAnswers) {
            question.mixAnswers();
        }
        for (int i = 0; i < question.getAnswers().size(); i++) {
            String answer = question.getAnswers().get(i);
            ioService.print(format("{0} - {1}", i , answer));
        }
    }

    @Override
    public Answer getAnswer(Question question, int attempts) {
        int answer = readAnswer(question);
        if (question.getRightAnswerIndex() != answer && 1 < attempts) {
            attempts--;
            ioService.printError(format("Wrong. Remaining attempts: {0}. Try again.", attempts));
            return getAnswer(question, attempts);
        }
        return new Answer(question, answer);
    }

    private int readAnswer(Question question) {
        ioService.print("Your answer:");
        int answer = ioService.readAnswerIndex();
        int answersCount = question.getAnswers().size();
        if (answersCount - 1 < answer || answer < 0) {
            ioService.printError(format("The answer should be in the range from 0 to {0}. Try again", answersCount - 1));
            return readAnswer(question);
        }
        return answer;
    }

    @Override
    public void printResult(Exam exam) {
        ioService.printDelimiter();
        ioService.print(format("User: {0} {1}", exam.getUser().getFirstName(), exam.getUser().getLastName()));
        ioService.print(format("Right answers: {0}/{1}", exam.getRightAnswersCount(), exam.getQuestions().size()));
        if (exam.isExamPassed()) {
            ioService.print("PASSED");
        } else {
            ioService.printError("NOT PASSED");
        }
    }
}
