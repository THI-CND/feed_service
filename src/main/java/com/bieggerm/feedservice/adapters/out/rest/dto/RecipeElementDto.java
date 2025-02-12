package com.bieggerm.feedservice.adapters.out.rest.dto;

import com.bieggerm.feedservice.domain.model.FeedElement;
import com.bieggerm.feedservice.domain.model.RecipeElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Data
public class RecipeElementDto implements Serializable {
    private String id;
    private final String type = "recipe";
    private String name;
    private String description;
    private String author;

    public static RecipeElementDto fromElementToDto(RecipeElement recipeElement) {
        RecipeElementDto entity = new RecipeElementDto();
        entity.setName(recipeElement.getName());
        entity.setDescription(recipeElement.getDescription());
        entity.setId(recipeElement.getId());
        return entity;
    }

    public FeedElement fromDtoToElement(RecipeElementDto recipeElementDto) {
        RecipeElement recipeElement = new RecipeElement();
        recipeElement.setName(recipeElementDto.getName());
        recipeElement.setDescription(recipeElementDto.getDescription());
        recipeElement.setId(recipeElementDto.getId());
        recipeElement.setAuthor(recipeElementDto.getAuthor());
        return recipeElement;
    }
}
