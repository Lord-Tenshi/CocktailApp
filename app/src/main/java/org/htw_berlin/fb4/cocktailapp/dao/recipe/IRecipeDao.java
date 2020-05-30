package org.htw_berlin.fb4.cocktailapp.dao.recipe;

import java.util.ArrayList;

public interface IRecipeDao {
    /**
     * Returns a list of recipes.
     * @return String[]
     */
    ArrayList<Recipe> getRecipes(String recipeName);

    /**
     * Add a recipe with all it data
     * @param text
     * @return boolean if action was successful=1
     */
    boolean addRecipe(String[] text);

    /**
     * Removes a recipe with
     * @param name
     * @return boolean if action was successful=1
     */
    void removeRecipe(String name);

    /**
     *
     * @param text
     * @return
     */
    boolean updateRecipe(String[] text);
}
