package com.bieggerm.feedservice.domain.service;

import com.bieggerm.feedservice.domain.model.FeedElement;

import java.util.Collection;

public interface FeedService {

    public Collection<FeedElement> getFeed(String userId, int page);
}
