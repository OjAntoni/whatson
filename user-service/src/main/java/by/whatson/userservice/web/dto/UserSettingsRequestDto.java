package by.whatson.userservice.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserSettingsRequestDto extends RegistrationRequestDto{
    private boolean mailing;
    List<String> newsAgencies;
}
