package org.htw_berlin.fb4.cocktailapp.view.recyclerViewSelection;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemKeyProvider;

import java.util.List;

public class RecipeKeyProvider<Recipe> extends ItemKeyProvider<Recipe> {

    private List<Recipe> items;

    public RecipeKeyProvider(int scope, List<Recipe> items){
        super(scope);
        this.items = items;
    }
    @Nullable
    @Override
    public Recipe getKey(int position) {
        return items.get(position);
    }

    @Override
    public int getPosition(@NonNull Recipe key) {
        return items.indexOf(key);
    }
}
