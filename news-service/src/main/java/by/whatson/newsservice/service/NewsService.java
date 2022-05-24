package by.whatson.newsservice.service;


import by.whatson.domain.Agency;
import by.whatson.domain.Article;
import by.whatson.domain.Source;
import by.whatson.newsservice.repository.AgencyRepository;
import by.whatson.newsservice.repository.ArticleRepository;
import by.whatson.newsservice.repository.DailyMailRepository;
import by.whatson.newsservice.util.mapper.AgencyMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final ArticleRepository articleRepository;
    private final AgencyRepository agencyRepository;
    private final AgencyMapper agencyMapper;
    private final DailyMailRepository dailyMailRepository;

    public NewsService(ArticleRepository articleRepository, AgencyRepository agencyRepository, AgencyMapper mapper, DailyMailRepository dailyMailRepository) {
        this.articleRepository = articleRepository;
        this.agencyRepository = agencyRepository;
        this.agencyMapper = mapper;
        this.dailyMailRepository = dailyMailRepository;
    }

    public List<Article> getAllArticles(LocalDateTime from, int pageSize, int page){
        int numberOfArticles = articleRepository.countArticleByPublishedAtAfter(from);
        page = validPage(numberOfArticles, pageSize, page);
        if(numberOfArticles>100){
            return articleRepository.getAllByPublishedAtAfter(from, PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("publishedAt"))));
        } else {
            return articleRepository.getAllByPublishedAtAfter(from, Pageable.unpaged());
        }
    }

    public List<Article> getAllArticles(LocalDateTime from, LocalDateTime to, int pageSize, int page) {
        if (to.isBefore(from)) {
            return List.of();
        }
        int n = articleRepository.countAllByPublishedAtBetween(from, to);
        page = validPage(n, pageSize, page);
        return articleRepository.getAllByPublishedAtBetween(from, to, PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("publishedAt"))));
    }

    public List<Article> getAllArticles(String language, int pageSize, int page){
        int n = articleRepository.countAllByLanguage(language);
        page = validPage(n, pageSize, page);
        return articleRepository.getAllByLanguage(language, PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("publishedAt"))));
    }

    public List<Article> getAllArticles(String language, LocalDateTime from, int pageSize, int page){

        int n = articleRepository.countAllByLanguageAndPublishedAtAfter(language, from);
        page = validPage(n,pageSize, page);
        return articleRepository.getAllByLanguageAndPublishedAtAfter(language, from, PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("publishedAt"))));
    }

    public List<Article> getAllArticles(int pageSize, int page){
        long n = articleRepository.count();
        page = validPage((int)n, pageSize, page);
        articleRepository.findAll(PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("publishedAt"))));
        return null;
    }

    public int count(LocalDateTime from){
        return articleRepository.countArticleByPublishedAtAfter(from);
    }

    public int count(LocalDateTime from, LocalDateTime to){
        return articleRepository.countAllByPublishedAtBetween(from, to);
    }

    public int count(String language, LocalDateTime from){
        return articleRepository.countAllByLanguageAndPublishedAtAfter(language, from);
    }

    public int count(String language){
        return articleRepository.countAllByLanguage(language);
    }

    private int validPage(int results, int pageSize, int page){
        return Math.min(page, results / pageSize);
    }

    public List<Source> getSources(String language){
        List<Agency> agencies = agencyRepository.getAllByLanguage(language);
        return agencies.stream().map(agencyMapper::mapAgencyToSource).collect(Collectors.toList());
    }

    public List<Article> getAllForDailyMail(String language){
        return  dailyMailRepository.getAll(language);
    }

    public List<Article> getRandomForDailyMail(String language, int items){
        List<Article> all = dailyMailRepository.getAll(language);
        List<Article> toReturn = new ArrayList<>(items);
        Random r = new Random();
        items = Math.min(items, all.size());
        for (int i = 0; i < items; i++) {
            toReturn.add(all.remove(r.nextInt(all.size())));
        }
        return toReturn;
    }
}
