package com.bieggerm.feedservice.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Data
@RedisHash("CollectionResponse")
public class CollectionResponse extends FeedElement implements Serializable {
    private String author;
    private String type = "collection";

    @Override
    public String getType() {
        return "collection";
    }
}
