package ru.otus.spring.garussky.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.garussky.domain.Answer;
import ru.otus.spring.garussky.domain.Exam;
import ru.otus.spring.garussky.domain.Question;
import ru.otus.spring.garussky.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Тест комуникации с пользователем")
@ExtendWith(MockitoExtension.class)
class ConsoleCommunicationServiceTest {

    @Mock
    private IOService ioService;
    private CommunicationService communicationService;

    @BeforeEach
    void init() {
        communicationService = spy(new ConsoleCommunicationService(ioService));
    }

    @Test
    @DisplayName("Тест получиня фамилии и имени пользователя")
    void userInfoTest() {
        when(ioService.readAnswer()).thenReturn("Dmitriy", "Belov");
        User user = communicationService.getUser();
        assertEquals("Dmitriy", user.getFirstName(), "Неверное имя");
        assertEquals("Belov", user.getLastName(), "Неверная фамилия");
    }

    @Test
    @DisplayName("Тест перемешивания ответов")
    void shuffleTest() {
        boolean isShuffled = false;
        for (int i = 0; i < 100; i++) {
            Question question = new Question("Question 1", Arrays.asList("a", "b", "c"), 1);
            communicationService.askQuestion(question, true);
            assertEquals("b", question.getAnswers().get(question.getRightAnswerIndex()), "Неверный ответ");
            if (question.getRightAnswerIndex() != 1) {
                isShuffled = true;
            }
        }
        assertTrue(isShuffled, "Ответы не перемешались");
    }

    @Test
    @DisplayName("Тест на то что пока пользователь не введет валидный ответ, от него не отстанут")
    void parseIntTest() {
        when(ioService.readAnswerIndex()).thenReturn(-1, 3, 5, 1);
        Answer answer = communicationService.getAnswer(new Question("Question 1", Arrays.asList("a", "b", "c"), 1), 1);
        verify(ioService, times(4)).readAnswerIndex();
        assertEquals(1, answer.getAnswer(), "Неверный ответ");
    }

    @DisplayName("Тест на то что пользователю предложат несколько попыток ответить")
    @ParameterizedTest(name = "Попыток ответа: {0}")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void attemptsTest(int attempts) {
        communicationService.getAnswer(new Question("Question 1", Arrays.asList("a", "b", "c", "d", "e", "f", "g"), 5), attempts);
        verify(communicationService, times(attempts)).getAnswer(any(), anyInt());
    }

    @DisplayName("Тест вывод результата")
    @ParameterizedTest(name = "Попыток ответа: {0}")
    @CsvSource({
            "1, false",
            "2, false",
            "3, true",
            "4, true",
            "5, true",
    })
    void printResultTest(int rightAnswer, boolean isPassed) {
        Exam exam = new Exam(3, false, 1);
        exam.setUser(new User("sdf", "ggg"));
        exam.setQuestions(new ArrayList<>());
        IntStream.range(0, 5).forEach(index -> {
            Question question = new Question("Question", List.of(), 3);
            Answer answer = new Answer(question, index < rightAnswer ? 3 : 2);
            exam.getQuestions().add(question);
            exam.addAnswer(answer);
        });
        communicationService.printResult(exam);
        verify(ioService, times(isPassed ? 0 : 1)).printError(anyString());
        verify(ioService, times(isPassed ? 3 : 2)).print(anyString());
        assertEquals(rightAnswer, exam.getRightAnswersCount(), "Неверное количество правильных ответов");
        assertEquals(5, exam.getQuestions().size(), "Неверное количество вопросов");
    }
}