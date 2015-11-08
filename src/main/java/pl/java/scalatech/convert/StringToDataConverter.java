package pl.java.scalatech.convert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class StringToDataConverter implements Converter<LocalDate,String>{

    @Override
    public String convert(LocalDate source) {
        return source.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


}
