package pl.java.scalatech.config;

import java.io.File;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import lombok.SneakyThrows;
import pl.java.scalatech.beans.EmailService;

@Configuration
@PropertySource("file:/home/przodownik/settings/mail.properties")
@ComponentScan(basePackageClasses = EmailService.class)
public class MailConfig {
    @Value("${mail.port}")
    private int port;
    @Value("${mail.host}")
    private String host;
    @Value("${mail.userName}")
    private String userName;
    @Value("${mail.password}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Bean
    VelocityEngineFactoryBean velocityEngine() {
        VelocityEngineFactoryBean velocityEngine = new VelocityEngineFactoryBean();
        velocityEngine.setResourceLoaderPath("classpath:/mail/templates");
        return velocityEngine;
    }

    @Bean(name = "mailWithAttachemnt")
    @SneakyThrows
    JavaMailSender emailWithAttachent(String attachementName, File attachement) {
        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
        emailSender.setHost(host);
        emailSender.setPort(port);
        emailSender.setUsername(userName);
        emailSender.setPassword(password);
        Properties mailProperties = new Properties();
        mailProperties.setProperty("mail.smtp.auth", "true");
        mailProperties.setProperty("mail.smtp.socketFactory.port", "465");
        mailProperties.setProperty("mail.smtp.host", "smtp.gmail.com");
        mailProperties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailProperties.setProperty("mail.smtp.port", "465");
        emailSender.setJavaMailProperties(mailProperties);
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        FileSystemResource file = new FileSystemResource(attachement);
        helper.addAttachment(attachementName, file);
        return emailSender;
    }

    @Bean(name = "simpleMail")
    @SneakyThrows
    JavaMailSender email() {
        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
        emailSender.setHost(host);
        emailSender.setPort(port);
        emailSender.setUsername(userName);
        emailSender.setPassword(password);
        Properties mailProperties = new Properties();
        mailProperties.setProperty("mail.smtp.auth", "true");
        mailProperties.setProperty("mail.smtp.socketFactory.port", "465");
        mailProperties.setProperty("mail.smtp.host", "smtp.gmail.com");
        mailProperties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailProperties.setProperty("mail.smtp.port", "465");
        emailSender.setJavaMailProperties(mailProperties);
        return emailSender;
    }

}
