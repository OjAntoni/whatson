package by.whatson.userservice.web.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String token;
}
