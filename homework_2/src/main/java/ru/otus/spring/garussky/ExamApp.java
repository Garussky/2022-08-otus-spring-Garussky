package ru.otus.spring.garussky;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.garussky.config.ExamAppConfig;
import ru.otus.spring.garussky.service.ExamService;

public class ExamApp {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(ExamAppConfig.class)) {
            ExamService service = context.getBean(ExamService.class);
            service.startExam();
        }
    }
}
