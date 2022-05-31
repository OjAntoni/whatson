package com.example.mailingservice.web.client;

import by.whatson.domain.Article;
import by.whatson.util.helper.DiscoveryHelper;
import by.whatson.web.dto.NewsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class NewsApiClient {

    private DiscoveryHelper discoveryHelper;

    @Autowired
    public void setDiscoveryHelper(DiscoveryHelper discoveryHelper) {
        this.discoveryHelper = discoveryHelper;
    }

    public List<Article> getDailyMail(List<String> languages, int size) {
        if (size <= 0 || discoveryHelper.serviceUrl("news-service").isEmpty()) {
            return List.of();
        }
        return WebClient.create(discoveryHelper.serviceUrl("news-service").get().toString())
                .get()
                .uri(u -> u.path("news/all/daily")
                        .queryParam("lang", parseLanguages(languages))
                        .queryParam("items", size)
                        .build())
                .retrieve()
                .bodyToMono(NewsResponseDto.class)
                .block().getArticles();
    }

    public List<Article> getAllDailyMail(List<String> languages) {
        return WebClient.create()
                .get()
                .uri("http://localhost:8084/news/all/daily")
                .attribute("lang", parseLanguages(languages))
                .retrieve()
                .bodyToMono(NewsResponseDto.class)
                .block().getArticles();
    }

    private String parseLanguages(List<String> languages) {
        StringBuilder sb = new StringBuilder();
        languages.forEach(l -> sb.append(l).append(","));
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
