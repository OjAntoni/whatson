package com.example.mailingservice.web.dto;

import by.whatson.domain.Article;
import lombok.Data;

import java.util.List;

@Data
public class UserMailRespDto {
    String name;
    String email;
    List<List<Article>> articles;
}
