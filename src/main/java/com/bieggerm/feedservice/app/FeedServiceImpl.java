package com.bieggerm.feedservice.app;

import com.bieggerm.feedservice.app.ports.outgoing.*;
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
    private final MessageBroker messageBroker;
    private final RemoteLogger LOG;


    public FeedServiceImpl(FeedCache feedCache, CollectionProvider collectionProvider, RecipeProvider recipeProvider, MessageBroker messageBroker, RemoteLogger LOG) {
        this.feedCache = feedCache;
        this.collectionProvider = collectionProvider;
        this.recipeProvider = recipeProvider;
        this.messageBroker = messageBroker;
        this.LOG = LOG;
    }

    public Collection<FeedElement> getFeed(String userId, int page) {
        if (page == 1) {
            refreshCache();
            LOG.log("cache-refreshed", new HashMap<String, Object>( Map.of("user", userId )));
        }
        List<FeedElement> feedElements = new ArrayList<>(feedCache.findAll(page));
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("user", userId );
        LOG.log("feedprovided", data);
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
        messageBroker.sendMessage("Cache refreshed", "feed");
    }
}
