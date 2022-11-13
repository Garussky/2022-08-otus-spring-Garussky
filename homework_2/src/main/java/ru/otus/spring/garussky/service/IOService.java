package ru.otus.spring.garussky.service;

public interface IOService {
    void print(String message);
    void printDelimiter();
    void printError(String message);
    String readAnswer();
    int readAnswerIndex();
}
