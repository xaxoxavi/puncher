package com.esliceu.puncher.parser;

import com.esliceu.puncher.data.BatchProcess;
import com.esliceu.puncher.parser.model.Center;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class ParserProcessor {

    private final BatchProcess batchProcess;
    private final TaskExecutor taskExecutor;
    private final XMLParser xmlParser;

    @Autowired
    public ParserProcessor(TaskExecutor taskExecutor,
                           XMLParser xmlParser,
                           BatchProcess batchProcess){
        this.taskExecutor = taskExecutor;
        this.xmlParser = xmlParser;
        this.batchProcess = batchProcess;
    }

   @Async("asyncExecutor")
   public Future<String> process(ByteContainer item){

            Center center = xmlParser.parse(item);

            if (center.getAlumnes().isEmpty()) return new AsyncResult<>("KO");

            batchProcess.process(center);

            return new AsyncResult<>("OK");
   }


}
