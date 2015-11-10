package pl.java.scalatech.eums;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.java.scalatech.eums.Coffee.ESPRESSO;
import static pl.java.scalatech.eums.Coffee.LATTE;
import static pl.java.scalatech.eums.Coffee.MOCHA;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:enum2.xml")
public class EnumTest {

    private @Autowired Coffee latte;
    private @Autowired Coffee mocha;
    private @Autowired CoffeeMachine coffeeMachine;
    private @Autowired Coffee espresso;

    @Test
    public void shouldEnumBind() {
        assertThat(latte).isSameAs(LATTE);
        assertThat(mocha).isSameAs(MOCHA);
        assertThat(coffeeMachine.getCoffee()).isSameAs(MOCHA);
        assertThat(espresso).isSameAs(ESPRESSO);
    }

}
