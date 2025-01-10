package com.bieggerm.feedservice.adapters.out.rest;

import com.bieggerm.feedservice.adapters.out.rest.dto.RecipeElementDto;
import com.bieggerm.feedservice.app.ports.outgoing.RecipeProvider;
import com.bieggerm.feedservice.domain.model.RecipeElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecipeProviderImpl implements RecipeProvider {

    @Value("${recipe.service.url}")
    private String recipeServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public RecipeElement[] getRecipes() {
        RecipeElementDto[] recipeElementDtos = restTemplate.getForObject(recipeServiceUrl, RecipeElementDto[].class);
        assert recipeElementDtos != null;
        RecipeElement[] recipeElements = new RecipeElement[recipeElementDtos.length];
        for (int i = 0; i < recipeElementDtos.length; i++) {
            recipeElements[i] = (RecipeElement) recipeElementDtos[i].fromDtoToElement(recipeElementDtos[i]);
        }
        return recipeElements;
    }
}
