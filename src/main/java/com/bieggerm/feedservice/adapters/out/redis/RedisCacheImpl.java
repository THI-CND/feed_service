package com.bieggerm.feedservice.adapters.out.redis;

import com.bieggerm.feedservice.app.ports.outgoing.FeedCache;
import com.bieggerm.feedservice.domain.model.CollectionResponse;
import com.bieggerm.feedservice.domain.model.FeedElement;
import com.bieggerm.feedservice.domain.model.RecipeResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class RedisCacheImpl implements FeedCache, Serializable {

    private final RedisTemplate<String, Object> template;

    public RedisCacheImpl(RedisTemplate<String, Object> template) {
        this.template = template;
    }

    public void save(FeedElement feedElement) {
        FeedElementDto feedElementDto = FeedElementDto.fromFeedElement(feedElement);
        template.opsForZSet().add(feedElement.getType()+"zset", feedElementDto.getName(), System.currentTimeMillis());
        template.opsForHash().put(feedElement.getType()+"hash", feedElementDto.getName(), feedElement);
    }


    public List<FeedElement> findAll(String type, int page) {
        Set<Object> hashFields = template.opsForZSet().range(type+"zset", page*5L-5L, page * 5L);
        List<FeedElement> results = new ArrayList<>();
        assert hashFields != null;
        for (Object hashField : hashFields) {
            switch (type) {
                case "recipe":
                    results.add((RecipeResponse) template.opsForHash().get("recipehash", hashField));
                    break;
                case "collection":
                    results.add((CollectionResponse) template.opsForHash().get("collectionhash", hashField));
                    break;
            }
        }
        return results;
    }

    public void markAsRead(String userId, FeedElement feedElement) {
        FeedElementDto feedElementDto = FeedElementDto.fromFeedElement(feedElement);
        template.opsForHash().put(userId, feedElementDto.getName(), true);
    }

}
