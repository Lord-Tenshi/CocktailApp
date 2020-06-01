package org.htw_berlin.fb4.cocktailapp.model.dao.recipe;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface RecipeDao {
    /**
     * Returns a list of recipes.
     * @return
     */
     @Query("SELECT * FROM recipe")
     LiveData<List<Recipe>> getRecipes();

    /**
     * Add a recipe with all it data
     * @param recipe
     * @return boolean if action was successful=1
     */
    @Insert
    void insert(Recipe recipe);

    /**
     * Removes a recipe with
     * @param recipe
     * @return boolean if action was successful=1
     */
    @Delete
    void delete(Recipe recipe);

    /**
     *
     * @param name
     * @return recipe
     */
    @Query("SELECT * FROM recipe WHERE recipe.name = name")
    Recipe getRecipe(String name);

    /**
     *
     * @param recipe
     * @return
     */
    @Update
    boolean updateRecipe(Recipe recipe);
}
