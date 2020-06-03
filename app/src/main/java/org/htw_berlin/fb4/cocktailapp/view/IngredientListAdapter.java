package org.htw_berlin.fb4.cocktailapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.htw_berlin.fb4.cocktailapp.R;
import org.htw_berlin.fb4.cocktailapp.model.ingredient.Ingredient;

import java.util.List;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder> {



    class IngredientViewHolder extends RecyclerView.ViewHolder {
        private final TextView ingredientItemView;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;

    private List<Ingredient> mIngredient; // Cached copy of recipes

    IngredientListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item_ingredient, parent, false);
        return new IngredientViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull IngredientListAdapter.IngredientViewHolder holder, int position) {

        if (mIngredient != null) {
            Ingredient current = mIngredient.get(position);
            holder.ingredientItemView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.
            holder.ingredientItemView.setText("No Ingredients");
        }
    }

    public void setIngredients(List<Ingredient> ingredient) {
        mIngredient = ingredient;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mRecipes has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mIngredient != null)
            return mIngredient.size();
        else return 0;
    }
}
