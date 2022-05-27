package by.whatson.web.dto;

import by.whatson.domain.Article;
import by.whatson.web.dto.UserResponseDto;
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
