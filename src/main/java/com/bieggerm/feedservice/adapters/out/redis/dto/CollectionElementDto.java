package com.bieggerm.feedservice.adapters.out.redis.dto;

import com.bieggerm.feedservice.domain.model.CollectionElement;
import com.bieggerm.feedservice.domain.model.FeedElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Setter
@Getter
@Data
@RedisHash("CollectionResponse")
@EqualsAndHashCode(callSuper = true)
public class CollectionElementDto extends FeedElementDto implements Serializable {

    private final String type = "collection";

    public static CollectionElementDto fromElementToDto(CollectionElement collectionElement) {
        CollectionElementDto entity = new CollectionElementDto();
        entity.setName(collectionElement.getName());
        entity.setDescription(collectionElement.getDescription());
        entity.setAuthor(collectionElement.getAuthor());
        entity.setId(collectionElement.getId());
        return entity;
    }

    public FeedElement fromDtoToElement(CollectionElementDto feedElementDto) {
        CollectionElement collectionElement = new CollectionElement();
        collectionElement.setName(feedElementDto.getName());
        collectionElement.setDescription(feedElementDto.getDescription());
        collectionElement.setAuthor(feedElementDto.getAuthor());
        collectionElement.setId(feedElementDto.getId());
        return collectionElement;
    }
}
