package sample.decaton.processor.properties;

import lombok.Value;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Properties;

@Value
@ConstructorBinding
public class ProcessorDefinition {
    private String topic;
    private Properties properties;
}
