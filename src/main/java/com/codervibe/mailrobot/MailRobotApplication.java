package com.codervibe.mailrobot;

import com.codervibe.mailrobot.utils.GetSystemInformationUtils;
import com.codervibe.mailrobot.utils.RedisUtils;
import com.codervibe.mailrobot.utils.WeatherUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Administrator
 */
@EnableScheduling
@EnableCaching
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MailRobotApplication {


  public static void main(String[] args) {
    SpringApplication.run(MailRobotApplication.class, args);
  }


}
