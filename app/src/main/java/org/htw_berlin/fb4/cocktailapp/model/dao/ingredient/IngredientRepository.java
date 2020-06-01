package org.htw_berlin.fb4.cocktailapp.model.dao.ingredient;

import android.app.Application;

import androidx.lifecycle.LiveData;

import org.htw_berlin.fb4.cocktailapp.model.CocktailRoomDatabase;

import java.util.List;

public class IngredientRepository {
    private IngredientDao mIngredientDao;
    private LiveData<List<Ingredient>> mAllIngredients;

    IngredientRepository(Application application){
        CocktailRoomDatabase db = CocktailRoomDatabase.getDatabase(application);
        mIngredientDao = db.ingredientDao();
        mAllIngredients = mIngredientDao.getIngredients();
    }
}
