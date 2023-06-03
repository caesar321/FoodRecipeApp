package com.example.recipe;

import android.content.Context;

import com.example.recipe.Listeners.RecipeDetailsListener;
import com.example.recipe.Listeners.ResponseListeners;
import com.example.recipe.Models.RandomRecipeApiResponse;
import com.example.recipe.Models.RecipeDetailsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestClass {

    Context context;
    Retrofit retrofit= new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public RequestClass(Context context) {
        this.context = context;
    }
    public void getRandomRecipe(ResponseListeners listener,List<String>tags){
     RandomRecipes callRandomRecipe = retrofit.create(RandomRecipes.class);
     Call<RandomRecipeApiResponse> call= callRandomRecipe.callRandomRecipe(context.getString(R.string.api_key),"10",tags);
     call.enqueue(new Callback<RandomRecipeApiResponse>() {
         @Override
         public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
             if(!response.isSuccessful())
             {
                 listener.Error(response.message());
                 return;
             }
             listener.Fetch(response.body(),response.message());
         }

         @Override
         public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
            listener.Error(t.getMessage());
         }
     });


    }
    public  void getRecipeDetails(RecipeDetailsListener listener,int id){
        CallRecipeDetails callRecipeDetails= retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsResponse> call = callRecipeDetails.callResponseDetails(id,context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if(!response.isSuccessful()){
                    listener.error(response.message());
                    return;
                }
                listener.fetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
    }

    private  interface RandomRecipes{
        @GET("recipes/random")

        Call<RandomRecipeApiResponse>callRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags")List<String> tags
                );
    }
   private interface  CallRecipeDetails{
        @GET("recipes/{id}information")
       Call<RecipeDetailsResponse>callResponseDetails(
               @Path("id") int id,
               @Query("apiKey") String apiKey
        );
   }

}
