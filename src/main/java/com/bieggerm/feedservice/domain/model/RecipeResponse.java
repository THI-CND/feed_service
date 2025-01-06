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
@RedisHash("RecipeResponse")
public class RecipeResponse extends FeedElement implements Serializable {

    // Getters and setters
    private String id;
    private String type = "recipe";

    @Override
    public String getType() {
        return "recipe";
    }
}
