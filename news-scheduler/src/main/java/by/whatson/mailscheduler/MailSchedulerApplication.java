package by.whatson.mailscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "by.whatson.*")
public class MailSchedulerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MailSchedulerApplication.class, args);

    }

}
