package by.whatson.userservice;

import by.whatson.domain.Token;
import by.whatson.domain.User;
import by.whatson.userservice.repository.UserRepository;
import by.whatson.userservice.service.UserService;
import by.whatson.userservice.util.mapper.UserMapper;
import by.whatson.userservice.web.dto.RegistrationRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
class UserServiceApplicationTests {

    @Container
    private static final MongoDBContainer mongoDB = new MongoDBContainer("mongo");

    static {
        mongoDB.start();
    }

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper mapper;

    static final String NAME = "testXX";
    static final String USERNAME = "testXX";
    static final String EMAIL = "testXX";

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry){
        registry.add("spring.data.mongodb.uri", () -> mongoDB.getReplicaSetUrl("users"));
    }

    @Test
    void contextLoads() throws InterruptedException {
        System.out.println("Context loads!");
    }

    @Test
    void saveUserTest(){
        RegistrationRequestDto dto = RegistrationRequestDto.builder()
                .name(NAME)
                .username(USERNAME)
                .email(EMAIL)
                .build();

        Token token = userService.createAndSaveNewUser(dto);
        User u = userRepository.getUserByToken_Value(token.getValue());

        Assertions.assertNotNull(u);
        Assertions.assertTrue(
                u.getName().equals(NAME) &&
                        u.getUsername().equals(USERNAME) &&
                        u.getEmail().equals(EMAIL) &&
                        u.getToken().getValue().equals(token.getValue())
        );
    }

}
