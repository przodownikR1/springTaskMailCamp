package pl.java.scalatech.beans;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.ui.velocity.VelocityEngineUtils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailService {

    @Autowired
    @Qualifier("simpleMail")
    private MailSender simpleMail;

    @Autowired private VelocityEngineFactoryBean velocityEngineFactoryBean;

    public void send(String from, String to, String subject, Map<String, Object> hTemplateVariables) {
        sendEmail(from, to, subject, hTemplateVariables);
    }
    @SneakyThrows
    private void sendEmail(String from, String to, String subject, Map<String, Object> hTemplateVariables) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        String body = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngineFactoryBean.createVelocityEngine(),"mailTemplate.vm","UTF-8", hTemplateVariables);
        log.info("++++ body : {}",body);
        mailMessage.setText(body);
        simpleMail.send(mailMessage);
    }

}
