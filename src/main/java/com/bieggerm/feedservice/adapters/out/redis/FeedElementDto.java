package com.bieggerm.feedservice.adapters.out.redis;

import com.bieggerm.feedservice.domain.model.CollectionResponse;
import com.bieggerm.feedservice.domain.model.FeedElement;
import com.bieggerm.feedservice.domain.model.RecipeResponse;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class FeedElementDto {

    private String name;
    private String description;
    private String type;
    private String author;
    private String id;

    public static FeedElementDto fromFeedElement(FeedElement feedElement) {
        FeedElementDto entity = new FeedElementDto();
        entity.setName(feedElement.getName());
        entity.setDescription(feedElement.getDescription());
        entity.setType(feedElement.getType());
        if (feedElement instanceof RecipeResponse) {
            entity.setId(((RecipeResponse) feedElement).getId());
        } else if (feedElement instanceof CollectionResponse) {
            entity.setAuthor(((CollectionResponse) feedElement).getAuthor());
        }
        return entity;
    }

}
