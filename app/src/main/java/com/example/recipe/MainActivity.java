package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.recipe.Listeners.RecipeListener;
import com.example.recipe.Listeners.ResponseListeners;
import com.example.recipe.Models.RandomRecipeApiResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog;
    RequestClass manager;
    RecipeAdapter adapter;
    RecyclerView recycler;
    Spinner spinner;
    List<String>tags= new ArrayList<>();
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Please Wait...");
        searchView=findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRandomRecipe(responseListeners,tags);
                dialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        spinner= findViewById(R.id.spinner);

        ArrayAdapter arrayAdapter= ArrayAdapter.createFromResource(
                this,
                R.array.tags,
                R.layout.spinner_txt
        );
        arrayAdapter.setDropDownViewResource(R.layout.spinner_input);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(spinnerSelector);
        manager= new RequestClass(this);
       // manager.getRandomRecipe(responseListeners);
      //  dialog.show();
    }

    private final ResponseListeners responseListeners=new ResponseListeners() {
        @Override
        public void Fetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
            recycler= findViewById(R.id.recycler_random);
            recycler.setHasFixedSize(true);
          //  recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
            recycler.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
            adapter= new RecipeAdapter(MainActivity.this,response.recipes,recipeClickedListener);
            recycler.setAdapter(adapter);

        }

        @Override
        public void Error(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
    private final AdapterView.OnItemSelectedListener spinnerSelector= new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            tags.clear();
            tags.add(parent.getSelectedItem().toString());
            manager.getRandomRecipe( responseListeners,tags);
            dialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private final RecipeListener recipeClickedListener= new RecipeListener() {
        @Override
        public void onRecipeClicked(String id) {
            Toast.makeText(MainActivity.this, id, Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(MainActivity.this,RecipeDetailActivity2.class);
                intent.putExtra("id",id);
                startActivity(intent);
        }
    };
}