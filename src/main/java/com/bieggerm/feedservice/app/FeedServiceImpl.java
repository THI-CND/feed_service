package com.bieggerm.feedservice.app;

import com.bieggerm.feedservice.app.ports.outgoing.CollectionProvider;
import com.bieggerm.feedservice.app.ports.outgoing.FeedCache;
import com.bieggerm.feedservice.app.ports.outgoing.RecipeProvider;
import com.bieggerm.feedservice.domain.model.CollectionElement;
import com.bieggerm.feedservice.domain.model.FeedElement;
import com.bieggerm.feedservice.domain.model.RecipeElement;
import com.bieggerm.feedservice.domain.service.FeedService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FeedServiceImpl implements FeedService {

    private final FeedCache feedCache;
    private final CollectionProvider collectionProvider;
    private final RecipeProvider recipeProvider;

    public FeedServiceImpl(FeedCache feedCache, CollectionProvider collectionProvider,  RecipeProvider recipeProvider) {
        this.feedCache = feedCache;
        this.collectionProvider = collectionProvider;
        this.recipeProvider = recipeProvider;
    }

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
        feedElements.forEach(feedCache::save);

    }

    private void markAsRead(String userId, List<FeedElement> feedElements) {
        feedElements.forEach(feedElement -> feedCache.markAsRead(userId, feedElement));
    }

    private void refreshCache() {
        RecipeElement[] recipes = recipeProvider.getRecipes();

        CollectionElement[] collections = collectionProvider.getCollections();

        List<FeedElement> feedElements = new ArrayList<>();

        if (recipes != null) {
            feedElements.addAll(Arrays.asList(recipes));
        }
        if (collections != null) {
            feedElements.addAll(Arrays.asList(collections));
        }
        saveInCache(feedElements);
    }
}
