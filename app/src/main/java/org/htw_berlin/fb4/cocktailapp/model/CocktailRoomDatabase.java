package org.htw_berlin.fb4.cocktailapp.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import org.htw_berlin.fb4.cocktailapp.model.dao.ingredient.Ingredient;
import org.htw_berlin.fb4.cocktailapp.model.dao.ingredient.IngredientDao;
import org.htw_berlin.fb4.cocktailapp.model.dao.recipe.Recipe;
import org.htw_berlin.fb4.cocktailapp.model.dao.recipe.RecipeDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities ={Recipe.class, Ingredient.class}, version = 1, exportSchema = false)
public abstract class CocktailRoomDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    private static CocktailRoomDatabase INSTANCE;

    public abstract RecipeDao recipeDao();
    public abstract IngredientDao ingredientDao();

    public static CocktailRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (CocktailRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CocktailRoomDatabase.class,
                            "cocktail_datanase")
                            .addCallback(sRoomDatabseCallback).build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabseCallback = new RoomDatabase.Callback(){
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);

            databaseWriteExecutor.execute(() ->{
                RecipeDao dao = INSTANCE.recipeDao();


                Recipe recipe1 = new Recipe("Bumble Bee", "", "machste alles rein", "ist gut", "");
                dao.insert(recipe1);
            });
        }
    };
}
