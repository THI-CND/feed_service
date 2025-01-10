package com.bieggerm.feedservice.adapters.out.redis.dto;

import com.bieggerm.feedservice.domain.model.CollectionElement;
import com.bieggerm.feedservice.domain.model.FeedElement;
import com.bieggerm.feedservice.domain.model.RecipeElement;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Data
@RedisHash("FeedElement")
public abstract class FeedElementDto implements Serializable {

    private String name;
    private String description;

    public static FeedElementDto fromElementToDto(FeedElement feedElement) {
        if (feedElement instanceof RecipeElement) {
            return RecipeElementDto.fromElementToDto((RecipeElement) feedElement);
        } else if (feedElement instanceof CollectionElement) {
            return CollectionElementDto.fromElementToDto((CollectionElement) feedElement);
        }
        return null;
    }

    public static FeedElement fromDtoToElement(FeedElementDto feedElementDto) {
        if (feedElementDto instanceof RecipeElementDto) {
            return ((RecipeElementDto) feedElementDto).fromDtoToElement((RecipeElementDto) feedElementDto);
        } else if (feedElementDto instanceof CollectionElementDto) {
            return ((CollectionElementDto) feedElementDto).fromDtoToElement((CollectionElementDto) feedElementDto);
        }
        return null;
    }
}
