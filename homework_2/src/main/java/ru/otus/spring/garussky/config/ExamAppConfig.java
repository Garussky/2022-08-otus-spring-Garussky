package ru.otus.spring.garussky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.garussky.service.IOService;
import ru.otus.spring.garussky.service.IOServiceImpl;

@Configuration
@ComponentScan("ru.otus.spring.garussky.*")
@PropertySource("classpath:application.properties")
public class ExamAppConfig {

    @Bean
    public IOService getIoService() {
        return new IOServiceImpl(System.out, System.err, System.in);
    }
}
