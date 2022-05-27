package by.whatson.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data @NoArgsConstructor
public class UserResponseDto implements Serializable {
    private String name;
    private String email;
    private List<String> languages;
}
