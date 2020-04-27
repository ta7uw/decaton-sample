package sample.decaton.processor.processor;

import org.springframework.stereotype.Component;

import com.linecorp.decaton.processor.DecatonProcessor;
import com.linecorp.decaton.processor.ProcessingContext;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sample.decaton.processor.service.HeavyService;
import sample.decaton.protobuf.Event;

@Slf4j
@Component
@RequiredArgsConstructor
public class HeavyTaskProcessor implements DecatonProcessor<Event.SampleTask> {

    private final HeavyService heavyService;

    @Override
    public void process(ProcessingContext<Event.SampleTask> context, Event.SampleTask task)
            throws InterruptedException {
        log.info(String.format("Start this heavy task %s", task.getId()));
        heavyService.execute();
        log.info(String.format("Finished this heavy task %s", task.getId()));
    }

    @Override
    public void close() {
        log.error(String.format("%s close", getClass().getName()));
    }
}
