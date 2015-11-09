package pl.java.scalatech.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.beans.SimpleBean;

@Configuration
@Slf4j
@PropertySource("file:/home/przodownik/settings/mail2.properties")
public class TestConfig {
    @Value("${mail.port}")
    private int port;
    
    @Value("${mail.host}")
    private String host;
    
    @Value("${mail.userName}")
    private String userName;
    
    @Value("${mail.password}")
    private String password;
    
    @Value("${date}")
    private LocalDate date;

    @Autowired
    private Environment env;

    @Bean
    @DependsOn(value = "converterService")
    public SimpleBean simple() {
        return new SimpleBean(port, host, userName, password, LocalDate.now());
    }
    
    
    @Bean(name = "converterService")
    public ConversionService conversionService() {
        log.info("+++++++++++++ conversionService");
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new LocalDateToStringConverter());
        conversionService.addConverter(new StringToLocalDateConverter());
        return conversionService;
    }   

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Slf4j
    @Component
    public static class StringToLocalDateConverter implements Converter<String, LocalDate> {
        @PostConstruct
        public void init(){
            log.info("+++ init StringToLocalDateTimeConverter");
        }
        @Override
        public LocalDate convert( final String source) {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            //return LocalDateTime.parse(source, DateTimeFormatter.ISO_DATE);
        }
    }
    @Slf4j
    @Component
    public static class LocalDateToStringConverter implements Converter< LocalDate, String> {
        @PostConstruct
        public void init(){
            log.info("+++ init LocalDateTimeToStringConverter");
        }
        @Override
        public String convert( final LocalDate source) {
            return source.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }
    /*
    @Bean
    public CustomConversionServiceFactoryBean conversionService() {
        return new CustomConversionServiceFactoryBean();
    }*/

}