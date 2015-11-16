package pl.java.scalatech.future_a;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@ComponentScan(basePackageClasses={SimpleJob.class,SimpleTask.class})
public class FutureConfig {

}
