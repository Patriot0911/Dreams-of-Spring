package org.dreamsofspring.tasks;

import org.dreamsofspring.tasks.entity.Task;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean(name = "createTask")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Task createTask() {
        return new Task(null, null, null, 0, null, false);
    }
}
