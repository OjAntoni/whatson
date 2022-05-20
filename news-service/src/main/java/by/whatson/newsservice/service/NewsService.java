package by.whatson.newsservice.service;

import by.whatson.domain.Article;
import by.whatson.newscommon.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsService {
    private final ArticleRepository articleRepository;

    public NewsService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles(LocalDateTime from){
        return null;
    }

    public List<Article> getAllArticles(LocalDateTime from, LocalDateTime to){
        if(to.isBefore(from)){
            return List.of();
        }
        return null;
    }

    public List<Article> getAllArticles(String language, int page){
        return null;
    }

    public List<Article> getAllArticles(String language, int page, LocalDateTime from){
        return null;
    }

    public List<Article> getAllArticles(int page){
        return null;
    }
}
