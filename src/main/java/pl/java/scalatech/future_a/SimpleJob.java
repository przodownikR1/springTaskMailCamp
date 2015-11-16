package pl.java.scalatech.future_a;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SimpleJob {
    @Autowired
    private SimpleTask task;

    public void process() throws InterruptedException, ExecutionException {
        log.info("Going to find defaulters... ");
        Future<Boolean> asyncResult = task.send("1", "2", "3");
        log.info("Defaulter Job Complete. Task will be sent to all defaulter");
        Boolean result = asyncResult.get();
        log.info("Was SMS sent? " + result);
    }
}