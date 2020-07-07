package org.htw_berlin.fb4.cocktailapp.model.recipe;

import android.app.Application;

import androidx.lifecycle.LiveData;

import org.htw_berlin.fb4.cocktailapp.model.CocktailRoomDatabase;

import java.util.List;


public class RecipeRepository {
    private RecipeDao mRecipeDao;
    private LiveData<List<Recipe>> mAllLiveRecipes;
    private List<Recipe> mAllRecipes;

    public RecipeRepository(Application application){
        CocktailRoomDatabase db = CocktailRoomDatabase.getDatabase(application);
        mRecipeDao = db.recipeDao();
        mAllRecipes = mRecipeDao.getAllRecipes();
        mAllLiveRecipes = mRecipeDao.getAllLiveRecipes();
    }

    public List<Recipe> getRecipes(){return mAllRecipes;}
    public LiveData<List<Recipe>> getLiveRecipes() {
        return mAllLiveRecipes;
    }

    public void insert(Recipe recipe){
        CocktailRoomDatabase.databaseWriteExecutor.execute(() -> {
            mRecipeDao.insert(recipe);
        });
    }
}
