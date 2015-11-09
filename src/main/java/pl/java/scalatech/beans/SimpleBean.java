package pl.java.scalatech.beans;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleBean {

    private int port;

    private String host;

    private String userName;

    private String password;

    private LocalDate date;

}
