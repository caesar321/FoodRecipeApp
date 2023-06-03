package com.example.recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe.Listeners.RecipeDetailsListener;
import com.example.recipe.Models.ExtendedIngredient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientAdap extends RecyclerView.Adapter<IngredientAdap.ViewHolder> {
    Context context;
    List<ExtendedIngredient> list;
    RecipeDetailsListener listener;

    public IngredientAdap(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }
/*public IngredientAdap(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.list_ingredient_item,parent,false);
      return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_ingredient.setText(list.get(position).name);
        holder.txt_ingredient_name.setText(list.get(position).original);
        holder.txt_ingredient.setSelected(true);
        holder.txt_ingredient_name.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100"+list.get(position).image).into(holder.img_ingredient);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_ingredient,txt_ingredient_name;
        ImageView img_ingredient;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txt_ingredient= itemView.findViewById(R.id.txt_ingredient);
            txt_ingredient_name=itemView.findViewById(R.id.txt_ingredient_name);
            img_ingredient=itemView.findViewById(R.id.img_ingredient);
        }
    }
}





