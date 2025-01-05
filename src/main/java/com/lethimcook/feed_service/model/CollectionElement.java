package com.lethimcook.feed_service.model;

public class CollectionElement extends FeedElement {
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {

        return "collection";
    }
}
