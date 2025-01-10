package com.bieggerm.feedservice.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CollectionElement extends FeedElement {

    private String author;
    private String type = "collection";

    @Override
    public String getType() {
        return "collection";
    }

}
