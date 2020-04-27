package sample.decaton.client.properties;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Value;

@Value
@ConstructorBinding
@ConfigurationProperties("service.decaton.sample-client")
public class DecatonSampleClientProperties {
    private String topic;
    private String applicationId;
    private Properties properties;
}
