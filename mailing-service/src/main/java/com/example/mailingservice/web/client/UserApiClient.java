package com.example.mailingservice.web.client;

import by.whatson.util.helper.DiscoveryHelper;
import by.whatson.web.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Component
public class UserApiClient {
    private final int pageSize = 10;

    private DiscoveryHelper discoveryHelper;

    @Autowired
    public void setDiscoveryHelper(DiscoveryHelper discoveryHelper) {
        this.discoveryHelper = discoveryHelper;
    }

    public List<UserResponseDto> getAllMailedUsers(int page) {
        return sendRequest(page, true);
    }

    public List<UserResponseDto> getAllUnMailedUsers(int page) {
        return sendRequest(page, false);
    }

    private List<UserResponseDto> sendRequest(int page, boolean mailed) {
        Optional<URI> uri = discoveryHelper.serviceUrl("user-service");
        if (uri.isEmpty()) {
            return List.of();
        }
        return WebClient.create(uri.get().toString())
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
