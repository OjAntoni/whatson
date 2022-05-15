package by.whatson.userservice.web.dto;

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
    private String username;
    @NotNull(groups = {RegRequestMainGroup.class})
    @Email(groups = {RegRequestMainGroup.class, RegRequestSettingsGroup.class})
    private String email;
    @Pattern(regexp = "(?:\\(\\d{3}\\)|\\d{3}[-]*)\\d{3}[-]*\\d{4}",
            groups = {RegRequestMainGroup.class, RegRequestSettingsGroup.class})
    private String phone;
}
