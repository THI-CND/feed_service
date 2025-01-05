package com.bieggerm.feedservice.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class FeedElement {

    private String name;
    private String description;

    public abstract String getType();

}
