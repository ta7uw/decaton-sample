package sample.decaton.client.properties;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Value
@ConstructorBinding
@ConfigurationProperties("service.kafka")
public class KafkaProperties {
    private String bootstrapServers;
}
