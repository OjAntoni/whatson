package com.example.mailingservice.scheduler;

import by.whatson.domain.Article;
import com.example.mailingservice.web.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class NewsForUserMessage implements Serializable {
    private UserResponseDto user;
    private Map<String, List<Article>> articles;
}
