package pl.java.scalatech.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:enum1.xml")
@Slf4j
public class MeasureTest {

    
    @Autowired
    private Measure measure;
    @Test
    public void shouldMeasureInjectEnum(){
        log.info("+++ {}",measure);
    }
}
