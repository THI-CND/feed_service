package com.bieggerm.feedservice.adapters.out.redis.dto;

import com.bieggerm.feedservice.domain.model.FeedElement;
import com.bieggerm.feedservice.domain.model.RecipeElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Setter
@Getter
@Data
@RedisHash("RecipeResponse")
@EqualsAndHashCode(callSuper = true)
public class RecipeElementDto extends FeedElementDto implements Serializable {

    private final String type = "recipe";

    @Override
    public RecipeElementDto fromElement(FeedElement feedElement) {
        RecipeElement recipeElement = (RecipeElement) feedElement;
        this.setName(recipeElement.getName());
        this.setDescription(recipeElement.getDescription());
        this.setId(recipeElement.getId());
        this.setAuthor(recipeElement.getAuthor());
        return this;
    }

    @Override
    public RecipeElement toElement() {
        RecipeElement recipeElement = new RecipeElement();
        recipeElement.setName(this.getName());
        recipeElement.setDescription(this.getDescription());
        recipeElement.setId(this.getId());
        recipeElement.setAuthor(this.getAuthor());
        return recipeElement;
    }
}
