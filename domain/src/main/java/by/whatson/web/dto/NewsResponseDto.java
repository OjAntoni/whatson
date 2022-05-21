package by.whatson.web.dto;

import by.whatson.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NewsResponseDto {
    private int results;
    List<Article> articles;
}
