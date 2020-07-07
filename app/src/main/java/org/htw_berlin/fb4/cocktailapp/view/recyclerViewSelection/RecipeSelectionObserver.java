package org.htw_berlin.fb4.cocktailapp.view.recyclerViewSelection;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.SelectionTracker;

import org.htw_berlin.fb4.cocktailapp.model.recipe.Recipe;

public class RecipeSelectionObserver extends SelectionTracker.SelectionObserver<Recipe> {

    SelectionTracker mSelectionTracker;

    public RecipeSelectionObserver(SelectionTracker mSelectionTracker){
        this.mSelectionTracker = mSelectionTracker;
    }

    @Override
    public void onItemStateChanged(@NonNull Recipe key, boolean selected) {
        super.onItemStateChanged(key, selected);

    }

    @Override
    public void onSelectionRefresh() {
        super.onSelectionRefresh();
    }

    @Override
    public void onSelectionChanged() {
        super.onSelectionChanged();
        if (mSelectionTracker.hasSelection()) {
            Log.d("RecipeSelectionObserver", "onSelectionChanged: worked");
        }
    }

    @Override
    public void onSelectionRestored() {
        super.onSelectionRestored();
    }

    ;
}

