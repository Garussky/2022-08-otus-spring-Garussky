package ru.otus.spring.garussky.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class User {

    private final String firstName;

    private final String lastName;
}
