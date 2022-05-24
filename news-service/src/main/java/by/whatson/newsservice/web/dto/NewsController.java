package by.whatson.newsservice.web.dto;

import by.whatson.domain.Article;
import by.whatson.newsservice.service.NewsService;
import by.whatson.newsservice.web.controller.LanguageParams;
import by.whatson.web.ErrorResponse;
import by.whatson.web.dto.NewsResponseDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/all/get")
    public ResponseEntity<?> getAllArticlesByArticles(@RequestParam(required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
                                                                    @RequestParam(required = false, defaultValue = "100") int pageSize,
                                                                    @RequestParam(required = false, defaultValue = "0") int page,
                                                                    @RequestParam(required = false) String language) {

        List<Article> articles;
        int count;
        pageSize = pageSize>100 || pageSize<1 ? 100 : pageSize;
        if(language==null){
            if(from==null){
                return new ResponseEntity<>(new ErrorResponse(List.of("\"from\" parameter is required")), HttpStatus.NO_CONTENT);
            }
            if(to == null){
                articles=newsService.getAllArticles(from, pageSize, page);
                count = newsService.count(from);
            } else {
                articles=newsService.getAllArticles(from, to, pageSize, page);
                count = newsService.count(from, to);
            }
        } else {
            if(from==null){
                articles = newsService.getAllArticles(language, pageSize, page);
                count = newsService.count(language);
            } else {
                articles = newsService.getAllArticles(language, from, pageSize, page);
                count = newsService.count(language, from);
            }
        }

        return new ResponseEntity<>(new NewsResponseDto(count, articles), HttpStatus.OK);
    }

    @GetMapping("/all/daily")
    public ResponseEntity<?> getDailyMail(@RequestParam(name = "lang") String languages, @RequestParam(required = false) Integer items){
        LanguageParams languageParams = new LanguageParams(languages);
        List<Article> toReturn = new ArrayList<>();
        languageParams.getLanguages().forEach(
                l -> toReturn.addAll(items==null ? newsService.getAllForDailyMail(l) : newsService.getRandomForDailyMail(l, items))
        );
        return new ResponseEntity<>(new NewsResponseDto(toReturn.size(), toReturn), HttpStatus.OK);
    }

}

