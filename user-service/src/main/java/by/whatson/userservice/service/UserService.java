package by.whatson.userservice.service;

import by.whatson.domain.Token;
import by.whatson.domain.User;
import by.whatson.userservice.util.mapper.UserMapper;
import by.whatson.userservice.repository.UserRepository;
import by.whatson.userservice.web.dto.RegistrationRequestDto;
import by.whatson.util.helper.AttrCopy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public boolean updateUser(UUID uuid, User user){
        User userByToken_value = userRepository.getUserByToken_Value(uuid.toString());
        if(user.getUsername()!=null && userRepository.existsByUsername(user.getUsername())){
            return false;
        }
        try {
            new AttrCopy().copyAttributes(user, userByToken_value);
            userRepository.save(userByToken_value);
            return true;
        } catch (IllegalAccessException e) {
            return false;
        }
    }

    public boolean userExists(UUID token){
        return userRepository.getUserByToken_Value(token.toString()) != null;
    }

    public boolean userExists(String username){
        return userRepository.existsByUsername(username);
    }
}
