package com.bieggerm.feedservice.adapters.out.redis;

import com.bieggerm.feedservice.domain.model.CollectionResponse;
import com.bieggerm.feedservice.domain.model.FeedElement;
import com.bieggerm.feedservice.domain.model.RecipeResponse;

import java.util.List;
import java.util.Set;

public interface FeedCache {

    public List<FeedElement> findAll(String name, int page);
    public void save(FeedElement feedElement);
    public void markAsRead(String userId, FeedElement feedElement);
}
