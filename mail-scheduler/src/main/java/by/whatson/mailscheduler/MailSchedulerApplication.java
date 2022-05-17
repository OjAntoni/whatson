package by.whatson.mailscheduler;

import by.whatson.mailscheduler.repository.AgencyRepository;
import by.whatson.mailscheduler.web.client.NewsClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MailSchedulerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MailSchedulerApplication.class, args);
//        NewsClient newsClient = new NewsClient();
//        newsClient.setAgencyRepository(run.getBean(AgencyRepository.class));
//        System.out.println(newsClient.getAllAvailableAgencies());
    }

}
