package com.bieggerm.feedservice.app.ports.outgoing;

import com.bieggerm.feedservice.domain.model.RecipeElement;

public interface RecipeProvider {
    public RecipeElement[] getRecipes();
}
