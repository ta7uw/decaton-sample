package sample.decaton;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import com.linecorp.decaton.client.DecatonClient;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import sample.decaton.protobuf.Event;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@ConfigurationPropertiesScan
public class ClientApplication {

    private final DecatonClient<Event.SampleTask> decatonSampleClient;

    public static void main(String[] args) {
        var ctx = SpringApplication.run(ClientApplication.class);
        var app = ctx.getBean(ClientApplication.class);
        app.run();
    }

    @SneakyThrows
    public void run() {
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            Event.SampleTask sampleTask = Event.SampleTask.newBuilder()
                    .setName("hello")
                    .setAge(25)
                    .setId(UUID.randomUUID().toString())
                    .build();
            var res = decatonSampleClient.put(sampleTask.getId(), sampleTask).join();
            log.info(res.id());
        }
    }
}
