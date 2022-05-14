package by.whatson.userservice.mapper;

import by.whatson.domain.User;
import by.whatson.userservice.web.dto.RegistrationRequestDto;
import by.whatson.userservice.web.dto.UserSettingsRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapRegistrationReqDtoToUser(RegistrationRequestDto dto);
    @Mappings({@Mapping(source = "mailing", target = "settings.mailing"),
               @Mapping(source = "newsAgencies", target = "settings.newsAgencies")})
    User mapUserSettingsReqDtoToUser(UserSettingsRequestDto dto);
}
