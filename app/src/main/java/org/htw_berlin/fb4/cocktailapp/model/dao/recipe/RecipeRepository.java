package org.htw_berlin.fb4.cocktailapp.model.dao.recipe;

import android.app.Application;

import androidx.lifecycle.LiveData;

import org.htw_berlin.fb4.cocktailapp.model.CocktailRoomDatabase;

import java.util.List;

public class RecipeRepository {
    private RecipeDao mRecipeDao;
    private LiveData<List<Recipe>> mAllRecipes;

    public RecipeRepository(Application application){
        CocktailRoomDatabase db = CocktailRoomDatabase.getDatabase(application);
        mRecipeDao = db.recipeDao();
        mAllRecipes = mRecipeDao.getRecipes();
    }
    LiveData<List<Recipe>> getRecipes() {
        return mAllRecipes;
    }

    void insert(Recipe recipe){
        CocktailRoomDatabase.databaseWriteExecutor.execute(() -> {
            mRecipeDao.insert(recipe);
        });
    }
}
