package com.bieggerm.feedservice.domain.model;


import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class RecipeElement extends FeedElement {

    // Getters and setters
    private String type = "recipe";

    @Override
    public String getType() {
        return "recipe";
    }

}
