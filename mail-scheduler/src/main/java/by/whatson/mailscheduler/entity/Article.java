package by.whatson.mailscheduler.entity;

import lombok.Data;
import org.springframework.core.io.UrlResource;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;
import java.time.LocalDateTime;

@Data
@Document("articles")
public class Article {
    private Source source;
    private String author;
    private String title;
    private String description;
    private URL url;
    private UrlResource urlToImage;
    private LocalDateTime publishedAt;
    private String content;
}
