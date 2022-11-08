package ru.otus.spring.garussky;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.garussky.service.ExamService;

/**
 * Приложение проведения тестирования пользователя
 */
public class ExamApp {

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml")) {
            ExamService service = context.getBean(ExamService.class);
            service.printQuestions();
        }
    }
}
