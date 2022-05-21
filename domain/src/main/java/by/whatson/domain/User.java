package by.whatson.domain;

import by.whatson.util.annotation.AttrCopyExclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data @Builder
@Document(collection = "users")
@ToString
public class User {
    @Id @AttrCopyExclude
    private String id;
    private String name;
    private String username;
    private String phone;
    private String email;
    @CreatedDate
    private LocalDateTime registrationTime;
    private LocalDateTime lastActiveTime;
    @AttrCopyExclude
    private Token token;
    private AdvancedUserSettings settings;
}
