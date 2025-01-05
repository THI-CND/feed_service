package com.bieggerm.feedservice.domain.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Setter
@Getter
@Data
@RedisHash("RecipeResponse")
public class RecipeResponse extends FeedElement implements Serializable {

    // Getters and setters
    private String id;
    private final String type = "recipe";

    @Override
    public String getType() {
        return "recipe";
    }
}
