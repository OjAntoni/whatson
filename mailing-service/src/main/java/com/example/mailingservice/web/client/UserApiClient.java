package com.example.mailingservice.web.client;

import by.whatson.web.dto.UserResponseDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class UserApiClient {
    private final int pageSize = 10;

//    public UserApiClient(@Autowired(required = false) int pageSize) {
//        this.pageSize = pageSize;
//    }

    public List<UserResponseDto> getAllMailedUsers(int page){
        return sendRequest(page, true);
    }

    public List<UserResponseDto> getAllUnMailedUsers(int page){
        return sendRequest(page, false);
    }

    private List<UserResponseDto> sendRequest(int page, boolean mailed){
        return WebClient.create("http://localhost:8081")
                .get()
                .uri(u -> u.path("/user/all")
                        .queryParam("size", pageSize)
                        .queryParam("page", page)
                        .queryParam("mailedOnly", mailed)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<UserResponseDto>>() {
                }).block();
    }
}
