package org.example.lab2;

import org.example.lab2.service.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean()
    public static String hello() {
        return "Hello World!";
    }

    @Bean(name = "TaskService")
    public TaskService taskService() {
        return new TaskService();
    }
}
