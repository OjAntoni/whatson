package by.whatson.mailsender.mail;


import by.whatson.domain.Article;
import by.whatson.web.dto.NewsForUserMessage;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MailHelper {
    private final Map<String, List<Article>> map;
    private final String name;

    public MailHelper(NewsForUserMessage message) {
        map = message.getArticles();
        name = message.getUser().getName();
    }

    public String generateTextForMail(){
        StringBuilder sb = new StringBuilder();
        sb.append("Good evening, ")
                .append(name)
                .append("\n\n")
                .append("Here is a good portion of news for you today:")
                .append("\n\n");
        for (Map.Entry<String, List<Article>> elem : map.entrySet()) {
            sb.append(elem.getKey().toUpperCase(Locale.ROOT))
                    .append(" news:")
                    .append("\n");
            elem.getValue().forEach(a -> {
                sb.append(a.getDescription())
                        .append("\n")
                        .append(a.getUrl())
                        .append("\n");
            });
            sb.append("\n\n");
        }
        return sb.toString();
    }
}
