package ru.otus.spring.garussky.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.garussky.domain.Question;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;

import static java.text.MessageFormat.format;

@Component
public class QuestionDaoCsv implements QuestionDao {

    private static final char CSV_SEPARATOR = ';';
    private final String scvResourceName;

    public QuestionDaoCsv(@Value("${examApp.filename}") String scvResourceName) {
        this.scvResourceName = scvResourceName;
    }

    @Override
    public List<Question> findAll() {
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(
                this.getClass().getResourceAsStream("/" + scvResourceName), "File not found."))) {
            return new CsvToBeanBuilder<Question>(reader)
                    .withType(Question.class)
                    .withSeparator(CSV_SEPARATOR)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new RuntimeException("Error read file - ", e);
        }
    }

    private void validateQuestions(List<Question> questions) {
        questions.forEach(question -> {
            if (question.getAnswers() == null || question.getAnswers().size() < 2) {
                throw new IllegalStateException(format("Question \"{0}\" there should be multiple answers.", question.getQuestion()));
            }
            if (question.getAnswers().size() - 1 < question.getRightAnswerIndex()) {
                throw new IllegalStateException(format("Question \"{0}\" has another answer.", question.getQuestion()));
            }
        });
    }
}
