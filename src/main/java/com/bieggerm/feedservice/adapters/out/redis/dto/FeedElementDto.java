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

    private String id;
    private String name;
    private String description;
    private String author;

    public static FeedElementDto fromElementToDto(FeedElement feedElement) {
        if (feedElement instanceof RecipeElement) {
            return new RecipeElementDto().fromElement((RecipeElement) feedElement);
        } else if (feedElement instanceof CollectionElement) {
            return new CollectionElementDto().fromElement((CollectionElement) feedElement);
        }
        throw new IllegalArgumentException("Unknown FeedElement type");
    }

    public static FeedElement fromDtoToElement(FeedElementDto feedElementDto) {
        return feedElementDto.toElement();
    }

    public abstract FeedElementDto fromElement(FeedElement feedElement);
    public abstract FeedElement toElement();
}
