package org.htw_berlin.fb4.cocktailapp.model.recipe;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */

public class RecipeViewModel extends AndroidViewModel {

    private RecipeRepository mRepository;
    // Using LiveData and caching what getAllRecipes returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<Recipe>> mAllRecipes;

    public RecipeViewModel(Application application) {
        super(application);
        mRepository = new RecipeRepository(application);
        mAllRecipes = mRepository.getRecipes();
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return mAllRecipes;
    }

    public void insert(Recipe recipe) {
        mRepository.insert(recipe);
    }
}
