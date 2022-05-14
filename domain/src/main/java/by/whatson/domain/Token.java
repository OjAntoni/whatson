package by.whatson.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Token {
    private String value;
    @CreatedDate
    private LocalDateTime creationTime;

    public Token() {
        this.value = UUID.randomUUID().toString();
        creationTime = LocalDateTime.now();
    }
}
