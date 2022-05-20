package by.whatson.mailscheduler.web.client;


import by.whatson.domain.Agency;
import by.whatson.domain.Article;
import by.whatson.mailscheduler.repository.AgencyRepository;
import by.whatson.mailscheduler.web.dto.ArticlesResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

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

    private String HTTP_REQUEST_FOR_NEWS="https://newsapi.org/v2/top-headlines?language=%s&pageSize=100&apiKey=%s";

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
