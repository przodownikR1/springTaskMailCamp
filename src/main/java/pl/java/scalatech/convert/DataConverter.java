package pl.java.scalatech.convert;

import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class DataConverter implements Converter<String, LocalDate> {
    @PostConstruct
    public void init() {
        log.info("+++ converter init");
    }
    @Override
    public LocalDate convert(String source) {
        log.info("converter ... {}",source);
        return parse(source, ofPattern("yyyy-MM-dd"));
    }

}
