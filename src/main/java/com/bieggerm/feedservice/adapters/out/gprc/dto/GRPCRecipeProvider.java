package com.bieggerm.feedservice.adapters.out.gprc.dto;

import com.bieggerm.feedservice.app.ports.outgoing.RecipeProvider;
import com.bieggerm.feedservice.domain.model.RecipeElement;
import de.benedikt_schwering.thicnd.stubs.Null;
import de.benedikt_schwering.thicnd.stubs.RecipeRequest;
import de.benedikt_schwering.thicnd.stubs.RecipeServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GRPCRecipeProvider implements RecipeProvider {
    @GrpcClient("recipe-service")
    private RecipeServiceGrpc.RecipeServiceBlockingStub recipeServiceBlockingStub;

    public RecipeElement[] getRecipes() {
        var response = recipeServiceBlockingStub.getRecipes(Null.newBuilder().build());
        RecipeElement[] recipeElements = null;
        if (response != null) {
            recipeElements = new RecipeElement[response.getRecipesCount()];
            for (int i = 0; i < response.getRecipesCount(); i++) {
                recipeElements[i] = new RecipeElement();
                recipeElements[i].setId(response.getRecipes(i).getId());
                recipeElements[i].setName(response.getRecipes(i).getName());
                recipeElements[i].setDescription(response.getRecipes(i).getDescription());
                recipeElements[i].setAuthor(response.getRecipes(i).getAuthor());
            }
        } else {
            System.out.println("No recipes found");
        }
        return recipeElements;
    }
}
