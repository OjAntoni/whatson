package by.whatson.web.dto;

import by.whatson.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class NewsResponseDto {
    private int results;
    List<Article> articles;
}
