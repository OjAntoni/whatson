package by.whatson.userservice.repository;

import by.whatson.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {

    User findUserByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User getUserByToken_Value(String uuid);
    List<User> getAllByToken_ValueOrderByRegistrationTimeDesc(String uuid);
    void deleteByToken_Value(String uuid);
}
