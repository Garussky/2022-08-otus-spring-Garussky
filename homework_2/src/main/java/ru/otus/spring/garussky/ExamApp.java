package ru.otus.spring.garussky;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.garussky.service.TestingService;

@ComponentScan
@PropertySource("classpath:application.properties")
public class ExamApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExamApp.class);
        TestingService testingService = context.getBean(TestingService.class);
        testingService.startTest();
        context.close();
    }
}
