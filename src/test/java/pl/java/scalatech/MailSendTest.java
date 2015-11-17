package pl.java.scalatech;

import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.beans.EmailService;
import pl.java.scalatech.config.MailConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MailConfig.class)
@Slf4j
public class MailSendTest {
    @Autowired
    private  EmailService emailSender;
    @Test
    public void shouldSendMail() {
         Map<String,Object> variables = Maps.newHashMap();
         variables.put("name", "kura");
        // emailSender.send("przodownikR1@gmail.com", "przodownikR1@gmail.com", "test spring velocity mail", variables);
    }
    @Autowired
    TemplateEngine templateEngine;
    @Test
    public void shouldThymeleafWork() {
        final Context ctx = new Context(Locale.getDefault());
        ctx.setVariable("name", "slawek");
        final String htmlContent = this.templateEngine.process("mail", ctx);
         log.info("{}",htmlContent);
    }
}
