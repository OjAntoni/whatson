package by.whatson.mailscheduler.web.dto;


import by.whatson.domain.Agency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Deprecated
@Data
public class AgenciesResponseDto {
    private String status;
    @JsonProperty("sources")
    private List<Agency> agencies;
}
