package com.bieggerm.feedservice.domain.service;

import com.bieggerm.feedservice.adapters.out.redis.FeedCache;
import com.bieggerm.feedservice.domain.model.CollectionResponse;
import com.bieggerm.feedservice.domain.model.FeedElement;
import com.bieggerm.feedservice.domain.model.RecipeResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class FeedServiceImpl implements FeedService {

    public FeedServiceImpl(FeedCache feedCache) {
        this.feedCache = feedCache;
        this.restTemplate = new RestTemplate();
    }
    private final RestTemplate restTemplate;
    private final FeedCache feedCache;

    public Collection<FeedElement> getFeed(String userId, int page) {
        if (page == 1) {
            refreshCache();
        }
        List<FeedElement> feedElements = new ArrayList<>(feedCache.findAll("recipe", page));
        feedElements.addAll(feedCache.findAll("collection", page));

        markAsRead(userId, feedElements);
        Collections.shuffle(feedElements);
        return feedElements;
    }

    private void saveInCache(List<FeedElement> feedElements) {
        for (FeedElement feedElement : feedElements) {
            feedCache.save(feedElement);
        }
    }

    private void markAsRead(String userId, List<FeedElement> feedElements) {
        feedElements.forEach(feedElement -> feedCache.markAsRead(userId, feedElement));
    }

    private void refreshCache() {
        String url = "http://localhost:8088/api/v1/recipe";
        RecipeResponse[] recipeResponses = restTemplate.getForObject(url, RecipeResponse[].class);

        //get Collections
        String url2 = "http://localhost:8000/collections";
        CollectionResponse[] collections = restTemplate.getForObject(url2, CollectionResponse[].class);

        List<FeedElement> feedElements = new ArrayList<>();
        if (recipeResponses != null) {
            feedElements.addAll(Arrays.asList(recipeResponses));
        }
        if (collections != null) {
            feedElements.addAll(Arrays.asList(collections));
        }
        saveInCache(feedElements);
    }
}
