package by.whatson.web;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorResponse {
    private List<String> causes;
    @CreatedDate
    private LocalDateTime time;

    public ErrorResponse(List<String> causes) {
        this.causes = causes;
        time = LocalDateTime.now();
    }
}
