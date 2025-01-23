package com.bieggerm.feedservice.adapters.out.redis;

import com.bieggerm.feedservice.adapters.out.redis.dto.FeedElementDto;
import com.bieggerm.feedservice.app.ports.outgoing.FeedCache;
import com.bieggerm.feedservice.domain.model.FeedElement;
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
            FeedElementDto feedElementDto = FeedElementDto.fromElementToDto(feedElement);
            template.opsForZSet().add("feedZset", feedElementDto.getId(), System.currentTimeMillis());
            template.opsForHash().put("feedHash", feedElementDto.getId(), feedElementDto);
    }

    public List<FeedElement> findAll(int page) {
        Set<Object> hashFields = template.opsForZSet().range("feedZset", page*5L-5L, page * 5L);
        List<FeedElement> results = new ArrayList<>();
        assert hashFields != null;
        for (Object hashField : hashFields) {
            FeedElementDto feedElementDto = (FeedElementDto) template.opsForHash().get("feedHash", hashField);
            results.add(FeedElementDto.fromDtoToElement(feedElementDto));
        }
        return results;
    }

    public void markAsRead(String userId, FeedElement feedElement) {
        FeedElementDto feedElementDto = FeedElementDto.fromElementToDto(feedElement);
        template.opsForHash().put(userId, feedElementDto.getName(), true);
    }

    public void flush() {
        template.delete("feedZset");
        template.delete("feedHash");
    }

}
