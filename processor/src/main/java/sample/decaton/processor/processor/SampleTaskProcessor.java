package sample.decaton.processor.processor;

import org.springframework.stereotype.Component;

import com.linecorp.decaton.processor.DecatonProcessor;
import com.linecorp.decaton.processor.ProcessingContext;

import lombok.extern.slf4j.Slf4j;
import sample.decaton.protobuf.Event;

@Slf4j
@Component
public class SampleTaskProcessor implements DecatonProcessor<Event.SampleTask> {

    @Override
    public void process(ProcessingContext<Event.SampleTask> context, Event.SampleTask task)
            throws InterruptedException {
        log.info(String.format("task: %s", task));
    }

    @Override
    public void close() {
        log.error(String.format("%s close", getClass().getName()));
    }
}
