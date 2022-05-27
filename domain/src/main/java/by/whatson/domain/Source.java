package by.whatson.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
public class Source implements Serializable {
    private String id;
    private String name;
}
