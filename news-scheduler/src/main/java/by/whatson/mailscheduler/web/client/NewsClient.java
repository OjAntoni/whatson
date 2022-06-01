package by.whatson.mailscheduler.web.client;


import by.whatson.domain.Agency;
import by.whatson.domain.Article;
import by.whatson.mailscheduler.repository.AgencyRepository;
import by.whatson.mailscheduler.web.dto.ArticlesResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Setter @Getter
public class NewsClient {
    private AgencyRepository agencyRepository;

    @Autowired
    public void setAgencyRepository(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @Value("${url}")
    private String URL;
    @Value("${agencies.endpoint}")
    private String AGENCIES_ENDPOINT;
    @Value("${articles.endpoint}")
    private String ARTICLES_ENDPOINT;
    @Value("${api.key}")
    private String API_KEY;

    private String HTTP_REQUEST_FOR_NEWS=URL+ARTICLES_ENDPOINT+"?language=%s&pageSize=100&apiKey=%s";

    public List<Agency> getAllAvailableAgencies(){
        return agencyRepository.findAll();
    }

    public List<Article> getTopArticles(String language){
        Mono<ArticlesResponseDto> listMono = WebClient.create()
                .get()
                .uri(String.format(HTTP_REQUEST_FOR_NEWS, language, API_KEY))
                .retrieve()
                .bodyToMono(ArticlesResponseDto.class);
        ArticlesResponseDto block = listMono.block();
        return block!=null ? block.getArticles() : List.of();
    }
}
