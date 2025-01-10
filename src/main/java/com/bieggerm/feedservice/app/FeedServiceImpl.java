package com.bieggerm.feedservice.app;

import com.bieggerm.feedservice.app.ports.outgoing.FeedCache;
import com.bieggerm.feedservice.domain.model.CollectionElement;
import com.bieggerm.feedservice.domain.model.FeedElement;
import com.bieggerm.feedservice.domain.model.RecipeElement;
import com.bieggerm.feedservice.domain.service.FeedService;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${recipe.service.url}")
    private String recipeServiceUrl;
    @Value("${collection.service.url}")
    private String collectionServiceUrl;


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
        RecipeElement[] recipeRespons = restTemplate.getForObject(recipeServiceUrl, RecipeElement[].class);
        CollectionElement[] collections = restTemplate.getForObject(collectionServiceUrl, CollectionElement[].class);

        List<FeedElement> feedElements = new ArrayList<>();
        if (recipeRespons != null) {
            feedElements.addAll(Arrays.asList(recipeRespons));
        }
        if (collections != null) {
            feedElements.addAll(Arrays.asList(collections));
        }
        saveInCache(feedElements);
    }
}
