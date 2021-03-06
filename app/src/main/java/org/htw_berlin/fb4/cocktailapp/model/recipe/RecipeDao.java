package org.htw_berlin.fb4.cocktailapp.model.recipe;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;


@Dao
public interface RecipeDao {

    /**
     * Returns a list of recipes.
     * @return List<Recipe>
     */
    @Transaction
    @Query("SELECT * FROM recipe")
    LiveData<List<Recipe>> getAllLiveRecipes();

    /**
     * Returns a list of recipes.
     * @return List<Recipe>
     */
    @Transaction
    @Query("SELECT * FROM recipe")
    List<Recipe> getAllRecipes();

    /**
     * Add a recipe with all it data
     * @param recipe
     */
    @Insert
    void insert(Recipe recipe);

    /**
     * Deletes a certain recipe
     * @param recipe
     *
     */
    @Delete
    void delete(Recipe recipe);

    /**
     * Deletes all the data in the table
     * (for debugging purposes)
     */
    @Query("DELETE FROM recipe")
    void deleteAll();


}
