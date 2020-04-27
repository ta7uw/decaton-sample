package sample.decaton.processor.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.linecorp.decaton.processor.ProcessorProperties;
import com.linecorp.decaton.processor.ProcessorsBuilder;
import com.linecorp.decaton.processor.Property;
import com.linecorp.decaton.processor.StaticPropertySupplier;
import com.linecorp.decaton.processor.runtime.ProcessorSubscription;
import com.linecorp.decaton.processor.runtime.RetryConfig;
import com.linecorp.decaton.processor.runtime.SubscriptionBuilder;
import com.linecorp.decaton.protobuf.ProtocolBuffersDeserializer;

import lombok.RequiredArgsConstructor;
import sample.decaton.processor.processor.HeavyTaskProcessor;
import sample.decaton.processor.processor.SampleTaskProcessor;
import sample.decaton.processor.properties.DecatonProcessorProperties;
import sample.decaton.protobuf.Event;
import sample.decaton.protobuf.Event.SampleTask;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(DecatonProcessorProperties.class)
public class DecatonProcessorConfig {

    private final DecatonProcessorProperties properties;

    private final SampleTaskProcessor sampleTaskProcessor;
    private final HeavyTaskProcessor heavyTaskProcessor;

    @Bean
    public ProcessorSubscription sampleProcessorSubscription() {
        return SubscriptionBuilder.newBuilder("decaton-sample-subscription")
                .consumerConfig(properties.getSampleTask().getProperties())
                .enableRetry(RetryConfig.builder()
                                        .build())
                                  .properties(StaticPropertySupplier.of(
                                          Property.ofStatic(ProcessorProperties.CONFIG_PARTITION_CONCURRENCY,
                                                            10)
                                  ))
                                  .processorsBuilder(ProcessorsBuilder
                                                             .consuming(properties.getSampleTask().getTopic(),
                                                                        new ProtocolBuffersDeserializer<>(
                                                                                SampleTask.parser()))
                                                             .thenProcess(sampleTaskProcessor))
                                  .buildAndStart();
    }

    @Bean
    public ProcessorSubscription heavyProcessorSubscription() {
        return SubscriptionBuilder.newBuilder("decaton-heavy-subscription")
                                  .consumerConfig(properties.getHeavyTask().getProperties())
                                  .properties(StaticPropertySupplier.of(
                                          Property.ofStatic(ProcessorProperties.CONFIG_PARTITION_CONCURRENCY,
                                                            10)
                                  ))
                                  .processorsBuilder(ProcessorsBuilder
                                                             .consuming(properties.getHeavyTask().getTopic(),
                                                                        new ProtocolBuffersDeserializer<>(
                                                                                Event.SampleTask.parser()))
                                                             .thenProcess(heavyTaskProcessor))
                                  .buildAndStart();
    }
}
