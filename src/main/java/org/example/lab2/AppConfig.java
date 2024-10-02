package org.example.lab2;

import org.example.lab2.entity.Task;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean(name="createTask")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    Task createTask() {
        return new Task(null, null, null, 0, null, false);
    };
}
