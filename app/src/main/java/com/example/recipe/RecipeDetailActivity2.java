package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe.Listeners.RecipeDetailsListener;
import com.example.recipe.Models.ExtendedIngredient;
import com.example.recipe.Models.RecipeDetailsResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailActivity2 extends AppCompatActivity {
    int id;
    TextView txt_meal_name,txt_meal_source,txt_meal_summary;
    ImageView img_meal_meal;
    RecyclerView recyle_meal_ingredient;
    ProgressDialog dialog;
    IngredientAdap madapter;
    RequestClass manager;
    List<ExtendedIngredient> extendedIngredients= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail2);
        initial();
        id=Integer.parseInt(getIntent().getStringExtra("id"));
       manager= new RequestClass(this);
       dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching...");
        dialog.show();
        manager.getRecipeDetails(recipeDetailsListener,id);



    }
   private void  initial(){
        txt_meal_name= findViewById(R.id.txt_meal_name);
        txt_meal_source=findViewById(R.id.txt_meal_source);
        txt_meal_summary=findViewById(R.id.txt_meal_summary);
        img_meal_meal=findViewById(R.id.img_meal_meal);
        recyle_meal_ingredient=findViewById(R.id.recyle_meal_ingredient);
   }
   private  final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
       @Override
       public void fetch(RecipeDetailsResponse response, String message) {
           dialog.dismiss();
          // txt_meal_name.setText(response.title);
         //  txt_meal_source.setText(response.sourceName);
         //  txt_meal_summary.setText(response.summary);
       //    Picasso.get().load(response.image).into(img_meal_meal);
           recyle_meal_ingredient.setHasFixedSize(true);
           recyle_meal_ingredient.setLayoutManager(new LinearLayoutManager(RecipeDetailActivity2.this,LinearLayoutManager.HORIZONTAL,false));
                madapter= new IngredientAdap(RecipeDetailActivity2.this,response.extendedIngredients);
                recyle_meal_ingredient.setAdapter(madapter);
       }

       @Override
       public void error(String message) {
           Toast.makeText(RecipeDetailActivity2.this, message, Toast.LENGTH_SHORT).show();
       }
   };
}