package by.whatson.newscommon.repository;

import by.whatson.domain.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface ArticleRepository extends MongoRepository<Article, String> {
    void deleteAllByPublishedAtBefore(LocalDateTime localDateTime);
}
