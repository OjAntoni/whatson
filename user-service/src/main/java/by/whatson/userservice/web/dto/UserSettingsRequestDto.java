package by.whatson.userservice.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserSettingsRequestDto extends RegistrationRequestDto{
    private boolean mailing;
    List<@Pattern(regexp = "en|ru|pl", message = "en, ru, pl") String> languages;
}
