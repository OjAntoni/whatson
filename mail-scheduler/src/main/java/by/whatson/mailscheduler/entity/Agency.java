package by.whatson.mailscheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.core.io.UrlResource;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.net.URL;

@Data
@AllArgsConstructor
@ToString
@Document("agencies")
public class Agency {
    @Id
    private String id;
    private String name;
    private String description;
    @Field("url.url")
    private URL url;
    private String category;
    private String language;
    private String country;
}
