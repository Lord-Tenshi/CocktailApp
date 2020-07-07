package org.htw_berlin.fb4.cocktailapp.view.recyclerViewSelection;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

import org.htw_berlin.fb4.cocktailapp.model.recipe.Recipe;
import org.htw_berlin.fb4.cocktailapp.view.recipeView.RecipeListAdapter;

public class RecipeItemDetailsLookup extends ItemDetailsLookup<Recipe> {

    private final RecyclerView mRecyclerView;

    public RecipeItemDetailsLookup(RecyclerView recyclerView){
        this.mRecyclerView = recyclerView;
    }

    @Nullable
    @Override
    public ItemDetails getItemDetails(@NonNull MotionEvent e) {
        View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
        if(view != null){
            RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(view);
            if (holder instanceof RecipeListAdapter.RecipeViewHolder){
                return ((RecipeListAdapter.RecipeViewHolder) holder).getItemDetails();
            }
        }
        return null;
    }
}
