package org.htw_berlin.fb4.cocktailapp.view.recyclerViewSelection;

import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;

import org.htw_berlin.fb4.cocktailapp.model.recipe.Recipe;

public class RecipeItemDetails extends ItemDetailsLookup.ItemDetails<Recipe> {
    private int position;
    private Recipe item;

    public RecipeItemDetails(int position, Recipe item) {
        this.position = position;
        this.item = item;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Nullable
    @Override
    public Recipe getSelectionKey() {
        return item;
    }
}
