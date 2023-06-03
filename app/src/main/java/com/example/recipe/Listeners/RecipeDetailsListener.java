package com.example.recipe.Listeners;

import com.example.recipe.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void fetch(RecipeDetailsResponse response,String message);
    void error(String message);
}
