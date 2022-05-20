package by.whatson.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;
import java.time.LocalDateTime;

@Data
@Document("articles")
public class Article {
    private Source source;
    private String author;
    @Id
    private String title;
    private String description;
    private URL url;
    private String urlToImage;
    private LocalDateTime publishedAt;
    private String content;
}
