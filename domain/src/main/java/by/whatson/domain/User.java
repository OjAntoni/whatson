package by.whatson.domain;

import by.whatson.util.annotation.AttrCopyExclude;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data @Builder
@Document(collection = "users")
public class User {
    private String name;
    private String username;
    private String phone;
    private String email;
    @CreatedDate
    @AttrCopyExclude
    private LocalDateTime registrationTime;
    @AttrCopyExclude
    private LocalDateTime lastActiveTime;
    @AttrCopyExclude
    private Token token;
    private AdvancedUserSettings settings;
}
