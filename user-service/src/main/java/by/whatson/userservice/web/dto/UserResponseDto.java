package by.whatson.userservice.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {
    private String name;
    private String email;
    private List<String> languages;
}
