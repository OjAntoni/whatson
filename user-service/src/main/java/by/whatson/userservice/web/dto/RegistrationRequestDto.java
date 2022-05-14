package by.whatson.userservice.web.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class RegistrationRequestDto {
    @NotBlank @NotEmpty @NotNull @Length(min = 3, max = 20)
    private String name;
    @NotBlank @NotEmpty @NotNull @Length(min = 6, max = 15)
    private String username;
    @Email
    private String email;
    @Pattern(regexp = "/+[1-9]*")
    private String phone;
}
