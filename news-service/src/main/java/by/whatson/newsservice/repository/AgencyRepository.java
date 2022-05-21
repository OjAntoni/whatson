package by.whatson.newsservice.repository;

import by.whatson.domain.Agency;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AgencyRepository extends MongoRepository<Agency, String> {
    List<Agency> getAllByLanguage(String language);
}
