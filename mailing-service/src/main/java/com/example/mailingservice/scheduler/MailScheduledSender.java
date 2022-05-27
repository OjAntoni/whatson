package com.example.mailingservice.scheduler;

import by.whatson.domain.Article;
import by.whatson.web.dto.NewsForUserMessage;
import com.example.mailingservice.web.client.NewsApiClient;
import com.example.mailingservice.web.client.UserApiClient;
import by.whatson.web.dto.UserResponseDto;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MailScheduledSender{
    private final NewsApiClient newsApiClient;
    private final UserApiClient userApiClient;
    private final JmsTemplate jmsTemplate;
    Map<String, List<Article>> articlesMap = new HashMap<>();

    public MailScheduledSender(NewsApiClient newsApiClient, UserApiClient userApiClient, JmsTemplate jmsTemplate) {
        this.newsApiClient = newsApiClient;
        this.userApiClient = userApiClient;
        this.jmsTemplate = jmsTemplate;
    }

    @Scheduled(cron = "0 5 20 * * *")
    public void sendNewsForUserToQueue(){
        articlesMap.put("ru", newsApiClient.getDailyMail(List.of("ru"), 5));
        articlesMap.put("en", newsApiClient.getDailyMail(List.of("en"), 5));
        articlesMap.put("pl", newsApiClient.getDailyMail(List.of("pl"), 5));

        List<UserResponseDto> users;
        int page = 0;
        users=userApiClient.getAllMailedUsers(page);
        while (users.size()!=0){
            System.out.println(users);
            users.forEach(u -> {
                Map<String, List<Article>> map = formArticlesListForUser(u);
                jmsTemplate.convertAndSend("mail-info-queue", new NewsForUserMessage(u, map));
            });
            page++;
            users=userApiClient.getAllMailedUsers(page);
        }
    }

    private Map<String, List<Article>> formArticlesListForUser(UserResponseDto u){
        Map<String, List<Article>> map = new HashMap<>();
        u.getLanguages().forEach(l -> {
            if(articlesMap.get(l)!=null)
                map.put(l, articlesMap.get(l));
        } );
        return map;
    }

    @SendTo(value = "mail-info-queue")
    private NewsForUserMessage send(UserResponseDto userResponseDto, Map<String, List<Article>> map){
        return new NewsForUserMessage(userResponseDto, map);
    }
}
