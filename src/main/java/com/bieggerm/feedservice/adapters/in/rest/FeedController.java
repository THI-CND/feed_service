package com.bieggerm.feedservice.adapters.in.rest;

import com.bieggerm.feedservice.domain.model.FeedElement;
import com.bieggerm.feedservice.app.FeedServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/feed")
public class FeedController {

    private final FeedServiceImpl feedService;

    public FeedController(FeedServiceImpl feedService) {
        this.feedService = feedService;
    }

    @GetMapping(value = "/{userid}/{page}")
    public ResponseEntity<Collection<FeedElement>> getFeed(@PathVariable(value = "userid") String userId, @PathVariable(value = "page") int page) {
        // returns id and page

        Collection<FeedElement> feedElements = feedService.getFeed(userId, page);
        return ResponseEntity.ok(feedElements);
    }

}
