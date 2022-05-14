package by.whatson.userservice.service;

import by.whatson.domain.Token;
import by.whatson.domain.User;
import by.whatson.userservice.mapper.UserMapper;
import by.whatson.userservice.repository.UserRepository;
import by.whatson.userservice.web.dto.RegistrationRequestDto;
import by.whatson.util.helper.AttrCopy;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Token createAndSaveNewUser(RegistrationRequestDto dto){
        User user = userMapper.mapRegistrationReqDtoToUser(dto);
        Token token = new Token();
        user.setToken(token);
        userRepository.save(user);
        return token;
    }

    public boolean updateUser(UUID uuid, User user){
        User userByToken_value = userRepository.getUserByToken_Value(uuid.toString());
        try {
            new AttrCopy().copyAttributes(user, userByToken_value);
            return true;
        } catch (IllegalAccessException e) {
            return false;
        }
    }
}
