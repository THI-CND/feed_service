package com.bieggerm.feedservice.adapters.out.rest.dto;

import com.bieggerm.feedservice.domain.model.CollectionElement;
import com.bieggerm.feedservice.domain.model.FeedElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Data
public class CollectionElementDto implements Serializable {
    private String author;
    private final String type = "collection";
    private String id;
    private String name;
    private String description;

    public static CollectionElementDto fromElementToDto(CollectionElement collectionElement) {
        CollectionElementDto entity = new CollectionElementDto();
        entity.setName(collectionElement.getName());
        entity.setDescription(collectionElement.getDescription());
        entity.setAuthor(collectionElement.getAuthor());
        entity.setId(collectionElement.getId());
        return entity;
    }

    public FeedElement fromDtoToElement(CollectionElementDto collectionElementDto) {
        CollectionElement collectionElement = new CollectionElement();
        collectionElement.setName(collectionElementDto.getName());
        collectionElement.setDescription(collectionElementDto.getDescription());
        collectionElement.setAuthor(collectionElementDto.getAuthor());
        collectionElement.setId(collectionElementDto.getId());
        return collectionElement;
    }
}
