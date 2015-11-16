package pl.java.scalatech.future_a;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SimpleTask {
    @Async
    public Future<Boolean> send(String... numbers) {
        log.info("Selecting task .... ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

            e.printStackTrace();
            return new AsyncResult<>(false);
        }
        log.info("Async  send task is Complete!!!");
        return new AsyncResult<>(true);
    }
}