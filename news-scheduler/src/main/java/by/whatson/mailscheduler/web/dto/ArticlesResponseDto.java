package by.whatson.mailscheduler.web.dto;


import by.whatson.domain.Article;
import lombok.Data;

import java.util.List;

@Data
public class ArticlesResponseDto {
    private String status;
    private int totalResults;
    private List<Article> articles;
}
