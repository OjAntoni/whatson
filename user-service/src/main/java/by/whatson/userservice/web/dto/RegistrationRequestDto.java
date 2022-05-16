package by.whatson.userservice.web.dto;

import by.whatson.userservice.util.validation.RegRequestMainGroup;
import by.whatson.userservice.util.validation.RegRequestSettingsGroup;
import by.whatson.userservice.util.validation.UserValidationMessages;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class RegistrationRequestDto {
    @NotBlank(groups = {RegRequestMainGroup.class})
    @NotEmpty(groups = {RegRequestMainGroup.class})
    @NotNull(groups = {RegRequestMainGroup.class})
    @Length(min = 3, max = 20, groups = {RegRequestMainGroup.class, RegRequestSettingsGroup.class})
    private String name;
    @NotBlank(groups = {RegRequestMainGroup.class})
    @NotEmpty(groups = {RegRequestMainGroup.class})
    @NotNull(groups = {RegRequestMainGroup.class})
    @Length(min = 6, max = 15, groups = {RegRequestMainGroup.class, RegRequestSettingsGroup.class})
    @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$",
            groups = {RegRequestMainGroup.class, RegRequestSettingsGroup.class},
            message = UserValidationMessages.FOR_USERNAME)
    private String username;
    @NotNull(groups = {RegRequestMainGroup.class})
    @Email(groups = {RegRequestMainGroup.class, RegRequestSettingsGroup.class})
    private String email;
    @Pattern(regexp = "(?:\\(\\d{3}\\)|\\d{3}[-]*)\\d{3}[-]*\\d{4}",
            groups = {RegRequestMainGroup.class, RegRequestSettingsGroup.class},
            message = UserValidationMessages.FOR_PHONE)
    private String phone;
}
