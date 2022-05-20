package by.whatson.newscommon.repository;


import by.whatson.domain.Agency;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AgencyRepository extends MongoRepository<Agency, String> {
}
