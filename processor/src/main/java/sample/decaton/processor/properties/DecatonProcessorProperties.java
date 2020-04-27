package sample.decaton.processor.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Value;

@Value
@ConstructorBinding
@ConfigurationProperties("service.decaton.processor")
public class DecatonProcessorProperties {
	private ProcessorDefinition sampleTask;
	private ProcessorDefinition heavyTask;
}
