package pl.java.scalatech;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.beans.SimpleBean;
import pl.java.scalatech.config.TestConfig;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestConfig.class)
public class SimpleBeanPropertyTest {



    @Autowired
    private SimpleBean simple;

    @Test
    public void shouldPropertySet() {
        log.info("___ +++  {}",simple);
    }
}
