package com.example.recipe.Listeners;

import com.example.recipe.Models.RandomRecipeApiResponse;

public interface ResponseListeners {
     void Fetch(RandomRecipeApiResponse response, String message);
    void Error(String message);

}
