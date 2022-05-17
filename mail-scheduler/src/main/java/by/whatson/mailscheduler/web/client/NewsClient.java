package by.whatson.mailscheduler.web.client;

import by.whatson.mailscheduler.entity.Agency;
import by.whatson.mailscheduler.repository.AgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class NewsClient {
    private AgencyRepository agencyRepository;

    @Autowired
    public void setAgencyRepository(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    private final String URL="https://newsapi.org";
    private final String AGENCIES_ENDPOINT="/v2/top-headlines/sources";
    private final String API_KEY="a0556e8158344ab38db6c9176e92c159";

    public List<Agency> getAllAvailableAgencies(){
        return agencyRepository.findAll();
    }
}
