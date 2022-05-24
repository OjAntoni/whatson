package by.whatson.userservice.util.mapper;

import by.whatson.domain.User;
import by.whatson.userservice.web.dto.RegistrationRequestDto;
import by.whatson.userservice.web.dto.UserResponseDto;
import by.whatson.userservice.web.dto.UserSettingsRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapRegistrationReqDtoToUser(RegistrationRequestDto dto);
    @Mappings({@Mapping(source = "mailing", target = "settings.mailing"),
               @Mapping(source = "languages", target = "settings.languages")})
    User mapUserSettingsReqDtoToUser(UserSettingsRequestDto dto);
    @Mappings({@Mapping(source = "settings.languages", target = "languages")})
    UserResponseDto mapUserToUserRespDto(User user);
}
