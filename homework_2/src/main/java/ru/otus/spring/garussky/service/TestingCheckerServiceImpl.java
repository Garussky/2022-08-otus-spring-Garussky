package ru.otus.spring.garussky.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.garussky.domain.Testing;

@Service
public class TestingCheckerServiceImpl implements TestingCheckerService {
    private final double successCoefficient;

    public TestingCheckerServiceImpl(@Value("${student.test.success.coefficient}") double successCoefficient) {
        this.successCoefficient = successCoefficient;
    }

    @Override
    public boolean checkTesting(Testing testing) {
        int size = testing.getTestingResults().size();
        return (testing.getTestingResults().entrySet().stream()
                .filter(entrySet -> entrySet.getKey().getText().equals(entrySet.getValue().getText()))
                .count() / (double) size) >= successCoefficient;
    }
}
