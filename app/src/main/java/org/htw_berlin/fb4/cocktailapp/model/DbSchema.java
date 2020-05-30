package org.htw_berlin.fb4.cocktailapp.model;

public class DbSchema {

    //Recipe table names
    final String TABLE_NAME_RECIPE = "Recipe";
    final String RECIPE_NAME = "Recipe_Name";
    final String TOOLS = "Tools";
    final String DESCRIPTION = "Description";
    final String INSTRUCTIONS = "Instructions";
    final String RECIPE_PICTURE = "Recipe_Picture";

    //Ingredient table names
    final String TABLE_NAME_INGREDIENT = "Ingredient";
    final String INGREDIENT_NAME = "Ingredient_Name";
    final String INGREDIENT_PICTURE = "Ingredient_Picture";
    final String BUYABLE_FROM = "Buyable_From";

    //Create table queries
    public final String CREATE_RECIPE_TABLE = "CREATE TABLE " + TABLE_NAME_RECIPE + "("
            + RECIPE_NAME + " TEXT PRIMARY KEY," + DESCRIPTION + " TEXT,"
            + INSTRUCTIONS + " TEXT," + TOOLS + " TEXT,"
            + RECIPE_PICTURE + " TEXT,"
            + "FOREIGN KEY(RECIPE_NAME) REFERENCES "+ TABLE_NAME_INGREDIENT +"(INGREDIENT_NAME)"+ ")";

    public final String CREATE_INGREDIENT_TABLE = "CREATE TABLE " + TABLE_NAME_INGREDIENT + "("
            + INGREDIENT_NAME + " TEXT PRIMARY KEY," + RECIPE_PICTURE + " TEXT,"
            + BUYABLE_FROM + "TEXT," + INGREDIENT_PICTURE + "TEXT,"
            + "FOREIGN KEY(INGREDIENT_NAME) REFERENCES "+ TABLE_NAME_RECIPE +"(RECIPE_NAME)"+ ")";
}
