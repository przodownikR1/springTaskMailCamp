package pl.java.scalatech.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.beans.SimpleBean;

@Configuration
//@ComponentScan(basePackageClasses = { DataConverter.class, StringToDataConverter.class })
@Slf4j
//@PropertySource("file:${user.home}/settings/mail2.properties")
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
    private LocalDateTime date;

    @Autowired
    private Environment env;

    @Bean(name = "converterService")
    public ConversionService conversionService() {
        log.info("+++++++++++++");
        DefaultConversionService conversionService = new DefaultConversionService();
       // conversionService.addConverter(new DataConverter());
        //conversionService.addConverter(new StringToDataConverter());
        conversionService.addConverter(new LocalDateTimeToStringConverter());
        conversionService.addConverter(new StringToLocalDateTimeConverter());
        return conversionService;
    }
    public static class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

        @Override
        public LocalDateTime convert( final String source) {
            return LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            //return LocalDateTime.parse(source, DateTimeFormatter.ISO_DATE);
        }
    }

    public static class LocalDateTimeToStringConverter implements Converter< LocalDateTime, String> {

        @Override
        public String convert( final LocalDateTime source) {
            return source.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }
/*
    @Bean
    public CustomConversionServiceFactoryBean conversionService() {
        return new CustomConversionServiceFactoryBean();
    }*/

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @DependsOn(value = "converterService")
    public SimpleBean simple() {

        return new SimpleBean(port, host, userName, password, date);
    }

}