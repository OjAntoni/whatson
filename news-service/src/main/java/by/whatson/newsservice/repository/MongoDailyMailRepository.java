package by.whatson.newsservice.repository;

import by.whatson.domain.Article;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MongoDailyMailRepository implements DailyMailRepository{
    private final MongoTemplate mongoTemplate;

    public MongoDailyMailRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Article> getAll(String language) {
        Query q = new Query();
        q.addCriteria(Criteria.byExample(
                        Criteria.where("language").is(language)
                )
        );
        return mongoTemplate.findAll(Article.class, "daily-mail").stream().filter(a -> a.getLanguage().equals(language)).collect(Collectors.toList());
    }
}
