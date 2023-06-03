package com.example.recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe.Listeners.RecipeListener;
import com.example.recipe.Models.Recipe;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    Context context;
    List<Recipe>list;
    RecipeListener listener;

    public RecipeAdapter(Context context, List<Recipe> list,RecipeListener listener) {
        this.context = context;
        this.list = list;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_random_item,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_title.setText(list.get(position).title);
        holder.txt_title.setSelected(true);
        holder.txt_likes.setText(list.get(position).aggregateLikes+ "Likes");
        holder.txt_servings.setText(list.get(position).servings+"serving");
        holder.txt_times.setText(list.get(position).readyInMinutes+ "minutes");
        Picasso.get().load(list.get(position).image).into(holder.img_food);
        holder.random_list_contains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView random_list_contains;
        TextView txt_title,txt_servings,txt_likes,txt_times;
        ImageView img_food;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            random_list_contains= itemView.findViewById(R.id.random_list_contains);
            txt_title=itemView.findViewById(R.id.txt_title);
            txt_servings=itemView.findViewById(R.id.txt_serving);
            txt_likes=itemView.findViewById(R.id.txt_likes);
            txt_times=itemView.findViewById(R.id.txt_time);
            img_food=itemView.findViewById(R.id.img_food);
        }
    }
}
