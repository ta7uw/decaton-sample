package sample.decaton.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.linecorp.decaton.client.DecatonClient;
import com.linecorp.decaton.protobuf.ProtocolBuffersSerializer;

import lombok.RequiredArgsConstructor;
import sample.decaton.client.properties.DecatonSampleClientProperties;
import sample.decaton.client.properties.KafkaProperties;
import sample.decaton.protobuf.Event;

@Configuration
@RequiredArgsConstructor
public class DecatonClientConfig {

    private final KafkaProperties kafkaProperties;
    private final DecatonSampleClientProperties sampleClientProperties;

    @Bean
    public DecatonClient<Event.SampleTask> decatonSampleClient() {
        final var properties = sampleClientProperties.getProperties();
        return DecatonClient.producing(sampleClientProperties.getTopic(),
                new ProtocolBuffersSerializer<Event.SampleTask>())
                .applicationId(sampleClientProperties.getApplicationId())
                .producerConfig(properties)
                .build();
    }
}
