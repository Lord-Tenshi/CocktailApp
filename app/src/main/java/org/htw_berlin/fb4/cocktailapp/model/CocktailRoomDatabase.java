package org.htw_berlin.fb4.cocktailapp.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.htw_berlin.fb4.cocktailapp.model.recipe.Recipe;
import org.htw_berlin.fb4.cocktailapp.model.recipe.RecipeDao;
import org.htw_berlin.fb4.cocktailapp.model.ingredient.Ingredient;
import org.htw_berlin.fb4.cocktailapp.model.ingredient.IngredientDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Recipe.class, Ingredient.class}, version = 1, exportSchema = false)
public abstract class CocktailRoomDatabase extends RoomDatabase {

    public abstract RecipeDao recipeDao();

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile CocktailRoomDatabase INSTANCE;

    public abstract IngredientDao ingredientDao();

    public static CocktailRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CocktailRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CocktailRoomDatabase.class,
                            "cocktail_database")
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecutor.execute(() -> {

                RecipeDao recipeDao = INSTANCE.recipeDao();
                IngredientDao ingredientDao = INSTANCE.ingredientDao();

                ingredientDao.deleteAll();
                recipeDao.deleteAll();

                Ingredient ingredient1 = new Ingredient("Gin", "Kaufland", "/");
                ingredientDao.insert(ingredient1);

                Recipe recipe1 = new Recipe("Bumble Bee", "das", "machste alles rein", "ist gut", "das");
                recipeDao.insert(recipe1);
            });
        }
    };
}