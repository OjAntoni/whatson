package by.whatson.mailscheduler.web.scheduler;


import by.whatson.domain.Article;
import by.whatson.mailscheduler.repository.ArticleRepository;
import by.whatson.mailscheduler.web.client.NewsClient;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsGetter {

    private final MongoTemplate mongoTemplate;
    private final NewsClient newsClient;
    private final ArticleRepository articleRepository;

    public NewsGetter(MongoTemplate mongoTemplate, NewsClient newsClient, ArticleRepository articleRepository) {
        this.mongoTemplate = mongoTemplate;
        this.newsClient = newsClient;
        this.articleRepository = articleRepository;
    }

    @Scheduled(cron = "0 0 * * * *")
    private void getTopHeadlines(){
        List<Article> topArticles = new ArrayList<>();
        boolean isForDailyMail = isTimeProperToSaveForDailyMail();
        try {
            topArticles = newsClient.getTopArticles("en");
            topArticles.addAll(newsClient.getTopArticles("ru"));
            topArticles.addAll(newsClient.getTopArticles("pl"));

            articleRepository.saveAll(topArticles);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(isForDailyMail){
            mongoTemplate.save(topArticles, "daily-mail");
        }
    }

    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        System.out.println(now.getHour() == 20 && now.getMinute() <= 2);
        System.out.println();
    }

    @Scheduled(cron = "0 0 0 * * *")
    private void deleteOldArticles(){
        articleRepository.deleteAllByPublishedAtBefore(LocalDateTime.now().minusDays(2));
    }

    private boolean isTimeProperToSaveForDailyMail(){
        LocalTime now = LocalTime.now();
        return now.getHour()==8 && now.getMinute()<=2;
    }
}
