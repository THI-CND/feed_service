package com.lethimcook.feed_service.controller.api;

import com.fasterxml.jackson.core.JsonEncoding;
import com.lethimcook.feed_service.model.CollectionElement;
import com.lethimcook.feed_service.model.FeedElement;
import com.lethimcook.feed_service.service.FeedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class FeedController {

    private final FeedService feedService;

    public FeedController(FeedService feedservice) {
        this.feedService = feedservice;
    }

    @GetMapping(value = "feed/{userid}/{page}")
    public ResponseEntity<Collection<FeedElement>> getFeed(@PathVariable(value = "userid") String userId, @PathVariable(value = "page") int page) {
        // returns id and page
        Collection<FeedElement> feedElements = feedService.getFeed(userId, page);

        return ResponseEntity.ok(feedElements);
    }

}
