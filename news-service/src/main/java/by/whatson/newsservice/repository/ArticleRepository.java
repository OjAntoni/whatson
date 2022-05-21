package by.whatson.newsservice.repository;



import by.whatson.domain.Article;
import by.whatson.domain.Source;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ArticleRepository extends MongoRepository<Article, String>{
    int countArticleByPublishedAtAfter(LocalDateTime localDateTime);
    int countAllByPublishedAtBetween(LocalDateTime localDateTime, LocalDateTime before);
    List<Article> getAllByPublishedAtAfter(LocalDateTime after, Pageable pageable);
    List<Article> getAllByPublishedAtBetween(LocalDateTime localDateTime, LocalDateTime before, Pageable pageable);
    List<Article> getAllByLanguage(String language, Pageable pageable);
    List<Article> getAllByLanguageAndPublishedAtAfter(String language, LocalDateTime after, Pageable pageable);
    int countAllByLanguage(String language);
    int countAllByLanguageAndPublishedAtAfter(String language, LocalDateTime from);
    List<Article> getAllBySource_Name(String name);
}
