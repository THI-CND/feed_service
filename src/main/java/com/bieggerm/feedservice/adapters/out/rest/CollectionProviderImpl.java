package com.bieggerm.feedservice.adapters.out.rest;

import com.bieggerm.feedservice.adapters.out.rest.dto.CollectionElementDto;
import com.bieggerm.feedservice.app.ports.outgoing.CollectionProvider;
import com.bieggerm.feedservice.domain.model.CollectionElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CollectionProviderImpl implements CollectionProvider {

    @Value("${collection.service.url}")
    private String collectionServiceUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public CollectionElement[] getCollections() {
        CollectionElementDto[] collectionElementDto = restTemplate.getForObject(collectionServiceUrl, CollectionElementDto[].class);
        assert collectionElementDto != null;
        CollectionElement[] collectionElements = new CollectionElement[collectionElementDto.length];
        for (int i = 0; i < collectionElementDto.length; i++) {
            collectionElements[i] = (CollectionElement) collectionElementDto[i].fromDtoToElement(collectionElementDto[i]);
        }
        return collectionElements;
    }
}
