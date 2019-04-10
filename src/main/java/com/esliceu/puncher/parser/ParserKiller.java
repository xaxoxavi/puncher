package com.esliceu.puncher.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class ParserKiller {

    private TaskExecutor taskExecutor;

    @Autowired
    public ParserKiller(TaskExecutor taskExecutor){
        this.taskExecutor = taskExecutor;
    }

    @PreDestroy
    public void onEnd(){

        ((ThreadPoolTaskExecutor) taskExecutor).shutdown();

    }
}
