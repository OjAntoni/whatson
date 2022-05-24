package by.whatson.userservice.service;

import by.whatson.domain.User;
import by.whatson.userservice.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserApiService {
    private final UserRepository userRepository;

    public UserApiService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(int page, int size){
        return (page>=0 && size>0) ? userRepository.findAll(PageRequest.of(page, size)).getContent() : List.of();
    }
}
