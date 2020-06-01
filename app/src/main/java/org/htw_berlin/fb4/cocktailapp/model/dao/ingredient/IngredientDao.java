package org.htw_berlin.fb4.cocktailapp.model.dao.ingredient;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IngredientDao {
    /**
     *
     *
     * @return
     */
     @Query("SELECT * FROM ingredient WHERE Ingredient.name = name")
     LiveData<List<Ingredient>> getIngredients();

    /**
     *
     * @param ingredient
     * @return
     */
    @Insert
    boolean insertIngredient(Ingredient ingredient);

    /**
     *
     * @param ingredient
     * @return
     */
    @Delete
    boolean deleteIngredient(Ingredient ingredient);
}
