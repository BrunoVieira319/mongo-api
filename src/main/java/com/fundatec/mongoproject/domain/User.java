package com.fundatec.mongoproject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.Document;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String username;
    private String name;
    private String city;

    public Document getDocument() {
        return new Document("username", this.username)
                .append("name", this.name)
                .append("city", this.city);
    }
}
