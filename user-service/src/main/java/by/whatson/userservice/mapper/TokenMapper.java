package by.whatson.userservice.mapper;

import by.whatson.domain.Token;
import by.whatson.userservice.web.dto.TokenResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface TokenMapper {
    @Mappings(@Mapping(source = "token.value", target = "token"))
    TokenResponseDto mapTokenToTokenResponseDto(Token token);
}
