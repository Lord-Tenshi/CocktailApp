package org.htw_berlin.fb4.cocktailapp.dao.ingredient;


public interface IIngredientDao {
    /**
     *
     * @param name
     * @return
     */
     Ingredient fetchIngredient(String name);

    /**
     *
     * @param text
     * @return
     */
    boolean insertIngredient(String[] text);

    /**
     *
     * @param name
     * @return
     */
    boolean deleteIngredient(String name);
}
