package com.bieggerm.feedservice.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
public abstract class FeedElement {
    private String id;
    private String name;
    private String description;


    public abstract String getType();
}
