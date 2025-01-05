package com.lethimcook.feed_service.model;

public class RecipeResponse extends FeedElement{

    private String id;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return "recipe";
    }

}
