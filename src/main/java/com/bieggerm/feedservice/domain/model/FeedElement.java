package com.bieggerm.feedservice.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class FeedElement {
    private String id;
    private String name;
    private String description;


    public abstract String getType();
}
