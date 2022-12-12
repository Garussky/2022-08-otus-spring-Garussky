package ru.otus.spring.garussky.service;

import java.util.List;

public interface FileReaderService {
    List<String> getLines(String filePath);
}
