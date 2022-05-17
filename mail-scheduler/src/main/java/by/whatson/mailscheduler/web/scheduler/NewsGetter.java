package by.whatson.mailscheduler.web.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NewsGetter {

    @Scheduled(cron = "0 0/5 * * * *")
    private void getTopHeadlines(){
        System.out.println("Ahahahaha" + LocalDateTime.now());
    }
}
