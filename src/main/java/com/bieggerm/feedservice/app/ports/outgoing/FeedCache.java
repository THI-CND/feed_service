package com.bieggerm.feedservice.app.ports.outgoing;

import com.bieggerm.feedservice.domain.model.FeedElement;

import java.util.List;

public interface FeedCache {
    public List<FeedElement> findAll(int page);
    public void flush();
    public void save(FeedElement feedElement);
    public void markAsRead(String userId, FeedElement feedElement);
}
