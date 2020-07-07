package org.htw_berlin.fb4.cocktailapp;


import org.htw_berlin.fb4.cocktailapp.model.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;


public class RecipeConnectionsHandlerTest {

    List<Recipe> recipeList;
    Recipe recipe1;
    Recipe recipe2;

    //TODO: 01.07.2020 finish tests
    public void createStuff(){
        recipeList = new ArrayList<Recipe>();
        recipe1 = new Recipe("Bumble bee", "none", "do this and that", "good stuff", "path somewhere");
        recipe2 = new Recipe("White Russian", "none", "do this and that", "good stuff", "path somewhere");
        recipeList.add(recipe1);
        recipeList.add(recipe2);

    }

}
