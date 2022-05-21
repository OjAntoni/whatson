package by.whatson.newsservice.util.mapper;

import by.whatson.domain.Agency;
import by.whatson.domain.Source;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgencyMapper {
    Source mapAgencyToSource(Agency agency);
}
