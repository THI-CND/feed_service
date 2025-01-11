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

    public static RecipeElementDto fromElementToDto(RecipeElement recipeElement) {
        RecipeElementDto entity = new RecipeElementDto();
        entity.setName(recipeElement.getName());
        entity.setDescription(recipeElement.getDescription());
        entity.setId(recipeElement.getId());
        entity.setAuthor(recipeElement.getAuthor());
        return entity;
    }

    public FeedElement fromDtoToElement(RecipeElementDto feedElementDto) {
        RecipeElement recipeElement = new RecipeElement();
        recipeElement.setName(feedElementDto.getName());
        recipeElement.setDescription(feedElementDto.getDescription());
        recipeElement.setId(feedElementDto.getId());
        recipeElement.setAuthor(feedElementDto.getAuthor());
        return recipeElement;
    }
}
