package org.htw_berlin.fb4.cocktailapp.model.ingredient;

import android.app.Application;

import androidx.lifecycle.LiveData;

import org.htw_berlin.fb4.cocktailapp.model.CocktailRoomDatabase;

import java.util.List;

public class IngredientRepository {
    private IngredientDao mIngredientDao;
    private LiveData<List<Ingredient>> mAllIngredients;

    public IngredientRepository(Application application){
        CocktailRoomDatabase db = CocktailRoomDatabase.getDatabase(application);
        mIngredientDao = db.ingredientDao();
        mAllIngredients = mIngredientDao.getIngredients();
    }

    public LiveData<List<Ingredient>> getIngredients() {
        return mAllIngredients;
    }

    public void insert(Ingredient ingredient){
        CocktailRoomDatabase.databaseWriteExecutor.execute(() -> {
            mIngredientDao.insert(ingredient);
        });
    }
}
