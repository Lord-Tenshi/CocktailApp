package org.htw_berlin.fb4.cocktailapp.view.recipeView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import org.htw_berlin.fb4.cocktailapp.R;
import org.htw_berlin.fb4.cocktailapp.model.recipe.Recipe;
import org.htw_berlin.fb4.cocktailapp.view.recyclerViewSelection.RecipeItemDetails;

import java.util.List;


public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    private SelectionTracker mSelectionTracker;

    public void setSelectionTracker(SelectionTracker selectionTracker){
        this.mSelectionTracker = selectionTracker;
    }

    private final LayoutInflater mInflater;
    private List<Recipe> mRecipes; // Cached copy of recipes

    RecipeListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item_recipe, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        if (mRecipes != null) {
            Recipe current = mRecipes.get(position);
            holder.recipeItemView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.
            holder.recipeItemView.setText("No recipes");
        }
    }

    void setRecipes(List<Recipe> recipes) {
        mRecipes = recipes;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mRecipes has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mRecipes != null)
            return mRecipes.size();
        else return 0;
    }
    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        private final TextView recipeItemView;

        private RecipeViewHolder(View itemView) {
            super(itemView);
            recipeItemView = itemView.findViewById(R.id.textView);
        }

        void bind(boolean isSelected){
            recipeItemView.setActivated((isSelected));
        }

        public RecipeItemDetails getItemDetails(){
            return new RecipeItemDetails(getAdapterPosition(), mRecipes.get(getAdapterPosition()));
        }
    }

}


