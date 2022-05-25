package by.whatson.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Source implements Serializable {
    private String id;
    private String name;
}
