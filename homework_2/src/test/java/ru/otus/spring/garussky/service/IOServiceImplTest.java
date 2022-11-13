package ru.otus.spring.garussky.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Тест сервиса комуникации с пользователем")
class IOServiceImplTest {

    @Test
    @SneakyThrows
    @DisplayName("Тест считывания ответа пользователя из консоли")
    public void readAnswerTest() {
        String answers = "test input";
        try (ByteArrayInputStream bais = new ByteArrayInputStream(answers.getBytes())) {
            IOServiceImpl service = new IOServiceImpl(null, null, bais);
            String answer = service.readAnswer();
            assertEquals(answers, answer, "Ответы не совпадают");
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("Тест считывания числового ответа. Проверяет что если не цифра, заставляем считывать ещё раз")
    public void readIntAnswerTest() {
        String answers = "test\ninput\nt\nsd\ngg\n1";
        try (ByteArrayInputStream bais = new ByteArrayInputStream(answers.getBytes())) {
            IOServiceImpl service = spy(new IOServiceImpl(null, System.out, bais));
            int answer = service.readAnswerIndex();
            assertEquals(1, answer, "Ответы не совпадают");
            verify(service, times(6)).readAnswerIndex();
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("Тест вывода сообщения пользователю в консоль")
    public void printTest() {
        String message = "test message";
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(baos)) {
            IOServiceImpl service = new IOServiceImpl(printStream, null, System.in);
            service.print(message);
            assertEquals(message, baos.toString().trim());
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("Тест вывода сообщения пользователю в консоль")
    public void printErrorTest() {
        String message = "test message";
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(baos)) {
            IOServiceImpl service = new IOServiceImpl(null, printStream, System.in);
            service.printError(message);
            assertEquals(message, baos.toString().trim());
        }
    }
}