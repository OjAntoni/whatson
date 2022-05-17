package by.whatson.mailscheduler.repository;

import by.whatson.mailscheduler.entity.Agency;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AgencyRepository extends MongoRepository<Agency, String> {
}
