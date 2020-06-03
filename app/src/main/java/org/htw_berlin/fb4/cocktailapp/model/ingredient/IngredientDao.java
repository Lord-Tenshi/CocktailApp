package org.htw_berlin.fb4.cocktailapp.model.ingredient;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IngredientDao {

    /**
     *
     * @return
     */
     @Query("SELECT * FROM ingredient")
     LiveData<List<Ingredient>> getIngredients();

    /**
     *
     * @param ingredient
     * @return
     */
    @Insert
    void insert(Ingredient ingredient);

    /**
     *
     * @param ingredient
     * @return
     */
    @Delete
    void delete(Ingredient ingredient);

    @Query("DELETE FROM ingredient")
    void deleteAll();
}
