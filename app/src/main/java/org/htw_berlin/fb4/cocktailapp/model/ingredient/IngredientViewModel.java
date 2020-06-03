package org.htw_berlin.fb4.cocktailapp.model.ingredient;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class IngredientViewModel extends AndroidViewModel {

    private IngredientRepository mRepository;
    // Using LiveData and caching what getAllIngredients() returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<Ingredient>> mAllIngredients;

    public IngredientViewModel(Application application) {
        super(application);
        mRepository = new IngredientRepository(application);
        mAllIngredients = mRepository.getIngredients();
    }

    public LiveData<List<Ingredient>> getAllIngredients() {
        return mAllIngredients;
    }

    public void insert(Ingredient ingredient) {
        mRepository.insert(ingredient);
    }
}

