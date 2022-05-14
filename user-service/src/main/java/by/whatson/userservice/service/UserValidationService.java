package by.whatson.userservice.service;

import by.whatson.userservice.repository.UserRepository;
import by.whatson.userservice.web.dto.RegistrationRequestDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserValidationService {

    private final UserRepository userRepository;


    public UserValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> validateRegistrationData(RegistrationRequestDto dto){
        String username = dto.getUsername();
        String email = dto.getEmail();
        List<String> errors = new ArrayList<>();

        if(userRepository.existsByUsername(username)){
            errors.add("User with such username already exists");
        }
        if (userRepository.existsByEmail(email)){
            errors.add("User with such email already exists");
        }

        return errors;
    }

    public boolean isTokenPresent(UUID tokenValue){
        return userRepository.getUserByToken_Value(tokenValue.toString()) != null;
    }
}
