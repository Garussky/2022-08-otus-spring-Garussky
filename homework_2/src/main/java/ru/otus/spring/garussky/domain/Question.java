package ru.otus.spring.garussky.domain;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @CsvBindByName(required = true)
    private String question;

    @CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = "\\,")
    private List<String> answers;

    @CsvBindByName(required = true)
    private int rightAnswerIndex;

    public void mixAnswers() {
        String rightAnswer = answers.get(rightAnswerIndex);
        Collections.shuffle(answers);
        rightAnswerIndex = answers.indexOf(rightAnswer);
    }
}
