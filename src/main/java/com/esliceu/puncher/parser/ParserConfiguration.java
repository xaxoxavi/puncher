package com.esliceu.puncher.parser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PreDestroy;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ParserConfiguration {

    @Bean
    public TaskExecutor asyncExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("parser_");
        executor.initialize();

        return executor;
    }


   /* @PreDestroy
    public void onExit(TaskExecutor asyncExecutor){



    }*/



}
