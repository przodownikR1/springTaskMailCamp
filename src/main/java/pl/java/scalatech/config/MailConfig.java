package pl.java.scalatech.config;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import lombok.SneakyThrows;
import pl.java.scalatech.beans.EmailService;

@Configuration
@PropertySource("file:/home/przodownik/settings/mail.properties")
@ComponentScan(basePackageClasses = EmailService.class)
public class MailConfig  implements ApplicationContextAware{
    @Value("${mail.port}")
    private int port;
    @Value("${mail.host}")
    private String host;
    @Value("${mail.userName}")
    private String userName;
    @Value("${mail.password}")
    private String password;

    private ApplicationContext applicationContext;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Bean
    VelocityEngineFactoryBean velocityEngine() {
        VelocityEngineFactoryBean velocityEngine = new VelocityEngineFactoryBean();
        velocityEngine.setResourceLoaderPath("classpath:/templates");
        return velocityEngine;
    }

  /*  @Bean(name = "mailWithAttachemnt")
    @SneakyThrows
    MailSender emailWithAttachent(String attachementName, File attachement) {
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
    }*/

    @Bean(name = "simpleMail")
    @SneakyThrows
    MailSender email() {
        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
        emailSender.setHost(host);
        emailSender.setPort(port);
        emailSender.setUsername(userName);
        emailSender.setPassword(password);
        Properties mailProperties = new Properties();
        mailProperties.setProperty("mail.smtp.auth", "true");
        mailProperties.setProperty("mail.smtp.socketFactory.port", port+"");
        mailProperties.put("mail.smtp.starttls.enable", "true");
        mailProperties.setProperty("mail.smtp.host", "smtp.gmail.com");
        mailProperties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailProperties.setProperty("mail.smtp.port", port+"");
        emailSender.setJavaMailProperties(mailProperties);
        return emailSender;
    }
    @Bean
    public TemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(emailTemplateResolver());
        return engine;
    }



    private ClassLoaderTemplateResolver emailTemplateResolver(){
        ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
        emailTemplateResolver.setPrefix("templates/");
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setTemplateMode("HTML5");
        emailTemplateResolver.setCharacterEncoding("UTF-8");
        emailTemplateResolver.setOrder(1);

        return emailTemplateResolver;
    }

    @Bean(name = AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(false);
        messageSource.setCacheSeconds(20);
        return messageSource;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }

}
