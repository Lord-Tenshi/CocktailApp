package org.htw_berlin.fb4.cocktailapp;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import android.content.Context;

import org.htw_berlin.fb4.cocktailapp.model.CocktailRoomDatabase;
import org.htw_berlin.fb4.cocktailapp.model.recipe.Recipe;
import org.htw_berlin.fb4.cocktailapp.model.recipe.RecipeDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

public class RecipeDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RecipeDao mRecipeDao;
    private CocktailRoomDatabase mDb;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        mDb = Room.inMemoryDatabaseBuilder(context, CocktailRoomDatabase.class)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build();
        mRecipeDao = mDb.recipeDao();
    }

    @After
    public void closeDb() {
        mDb.close();
    }

    @Test
    public void insertRecipe() throws Exception {
        Recipe recipe = new Recipe("Bumble bee", "none", "do this and that", "good stuff", "path somewhere");
        mRecipeDao.insert(recipe);
        List<Recipe> allRecipes = LiveDataTestUtil.getValue(mRecipeDao.getRecipes());
        assertEquals(allRecipes.get(0).getName(), recipe.getName());
    }

    @Test
    public void getAllRecipes() throws Exception {
        Recipe recipe = new Recipe("Bumble bee", "none", "do this and that", "good stuff", "path somewhere");
        mRecipeDao.insert(recipe);
        Recipe recipe2 = new Recipe("White Russian", "none", "do this and that", "good stuff", "path somewhere");
        mRecipeDao.insert(recipe2);
        List<Recipe> allRecipes = LiveDataTestUtil.getValue(mRecipeDao.getRecipes());
        assertEquals(allRecipes.get(0).getName(), recipe.getName());
        assertEquals(allRecipes.get(1).getName(), recipe2.getName());
    }

    @Test
    public void deleteAll() throws Exception {
        Recipe recipe = new Recipe("Bumble bee", "none", "do this and that", "good stuff", "path somewhere");
        mRecipeDao.insert(recipe);
        Recipe recipe2 = new Recipe("White Russian", "none", "do this and that", "good stuff", "path somewhere");
        mRecipeDao.insert(recipe2);
        mRecipeDao.deleteAll();
        List<Recipe> allRecipes = LiveDataTestUtil.getValue(mRecipeDao.getRecipes());
        assertTrue(allRecipes.isEmpty());
    }
}
