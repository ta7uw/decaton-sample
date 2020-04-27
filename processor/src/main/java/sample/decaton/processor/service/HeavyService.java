package sample.decaton.processor.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HeavyService {

    public void execute() throws InterruptedException {
        TimeUnit.SECONDS.sleep(20);
    }
}
