package by.whatson.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @Builder
public class AdvancedUserSettings {
    private boolean mailing;
    List<String> newsAgencies;
}
