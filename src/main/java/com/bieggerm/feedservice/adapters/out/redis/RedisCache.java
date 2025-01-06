package com.bieggerm.feedservice.adapters.out.redis;

import com.bieggerm.feedservice.domain.model.CollectionResponse;
import com.bieggerm.feedservice.domain.model.FeedElement;
import com.bieggerm.feedservice.domain.model.RecipeResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Repository
public class RedisCache implements FeedCache, Serializable {

    private final RedisTemplate<String, Object> template;

    public RedisCache(RedisTemplate<String, Object> template) {
        this.template = template;
    }

    public void save(FeedElement feedElement) {
        template.opsForZSet().add(feedElement.getType()+"zset", feedElement.getName(), System.currentTimeMillis());
        template.opsForHash().put(feedElement.getType()+"hash", feedElement.getName(), feedElement);
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
        template.opsForHash().put(userId, feedElement.getName(), true);
    }

}
