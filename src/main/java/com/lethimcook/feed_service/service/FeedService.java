package com.lethimcook.feed_service.service;

import com.lethimcook.feed_service.model.CollectionElement;
import com.lethimcook.feed_service.model.FeedElement;
import com.lethimcook.feed_service.model.RecipeResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class FeedService {

    public FeedService() {
        this.restTemplate = new RestTemplate();
    }
    private final RestTemplate restTemplate;

    public Collection<FeedElement> getFeed(String userId, int page) {
        //get Recipes
        String url = "http://localhost:8088/api/v1/recipe";
        RecipeResponse[] recipeResponses = restTemplate.getForObject(url, RecipeResponse[].class);
        //get Collections
        String url2 = "http://localhost:8000/collections";
        CollectionElement[] collections = restTemplate.getForObject(url2, CollectionElement[].class);
        List<FeedElement> feedElements = new ArrayList<>();
        if (recipeResponses != null) {
            feedElements.addAll(Arrays.asList(recipeResponses));
        }
        if (collections != null) {
            feedElements.addAll(Arrays.asList(collections));
        }
        //randomize list
        Collections.shuffle(feedElements);
        return feedElements;
    }
}
