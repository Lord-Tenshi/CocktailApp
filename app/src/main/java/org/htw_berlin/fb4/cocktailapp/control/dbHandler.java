package org.htw_berlin.fb4.cocktailapp.control;

import java.util.ArrayList;

public interface dbHandler {

    /**
     * Returns a list of recipes.
     * @return String[]
     */
    ArrayList<String> getRecipe(String recipe);

    /**
     * Add a recipe with all it's data.
     * @param text
     * @return boolean if action was successful=1
     */
    boolean addRecipe(String[] text);

    /**
     * Removes a recipe by name.
     * @param name
     * @return boolean if action was successful=1
     */
    void removeRecipe(String name);

    /**
     * Updates the data of a recipe by name.
     * @param text
     * @return
     */
    boolean updateRecipe(String[] text, String name);

    /**
     *
     * @param name
     * @return
     */
    String[] getIngredient(String name);

    /**
     *
     * @param text
     * @return
     */
    boolean addIngredient(String[] text);

    /**
     *
     * @param name
     * @return
     */
    boolean removeIngredient(String name);

}
