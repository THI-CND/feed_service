package com.bieggerm.feedservice.adapters.out.redis;

import com.bieggerm.feedservice.domain.model.CollectionResponse;
import com.bieggerm.feedservice.domain.model.FeedElement;

import java.util.List;
import java.util.Set;

public interface FeedCache {

    public void save(FeedElement feedElement);
    public List<FeedElement> findAll(String name, int page);
}
