package org.htw_berlin.fb4.cocktailapp.model.ingredient;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import org.htw_berlin.fb4.cocktailapp.model.RecipeIngredientCrossRef.IngredientWithRecipes;

import java.util.List;

@Dao
public interface IngredientDao {
    /**
     * Returns a list of ingredients.
     * @return LiveData<List<Ingredient>>
     */
    @Transaction
     @Query("SELECT * FROM ingredient")
     LiveData<List<Ingredient>> getIngredients();

    /**
     * Add an ingredient with all it data
     * @param ingredient
     */
    @Insert
    void insert(Ingredient ingredient);

    /**
     * Deletes a certain ingredient
     * @param ingredient
     *
     */
    @Delete
    void delete(Ingredient ingredient);

    /**
     * Deletes all the data in the table
     * (for debugging purposes)
     */
    @Query("DELETE FROM ingredient")
    void deleteAll();
}
