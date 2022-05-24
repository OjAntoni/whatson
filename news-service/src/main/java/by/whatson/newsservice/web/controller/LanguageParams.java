package by.whatson.newsservice.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageParams {
    private final List<String> languages;

    public LanguageParams(String commaSeparatedString) {
        languages = Arrays.stream(commaSeparatedString.split(","))
                .collect(Collectors.toList());
    }

    public List<String> getLanguages() {
        return this.languages;
    }
}
