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

    @Override
    public CollectionElementDto fromElement(FeedElement feedElement) {
        CollectionElement collectionElement = (CollectionElement) feedElement;
        this.setName(collectionElement.getName());
        this.setDescription(collectionElement.getDescription());
        this.setId(collectionElement.getId());
        this.setAuthor(collectionElement.getAuthor());
        return this;
    }

    @Override
    public CollectionElement toElement() {
        CollectionElement collectionElement = new CollectionElement();
        collectionElement.setName(this.getName());
        collectionElement.setDescription(this.getDescription());
        collectionElement.setId(this.getId());
        collectionElement.setAuthor(this.getAuthor());
        return collectionElement;
    }
}
