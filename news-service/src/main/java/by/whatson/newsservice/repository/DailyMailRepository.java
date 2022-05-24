package by.whatson.newsservice.repository;

import by.whatson.domain.Article;

import java.util.List;

public interface DailyMailRepository {
    List<Article> getAll(String language);
}
