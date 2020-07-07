package org.htw_berlin.fb4.cocktailapp.model.RecipeIngredientCrossRef;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import org.htw_berlin.fb4.cocktailapp.model.ingredient.Ingredient;
import org.htw_berlin.fb4.cocktailapp.model.recipe.Recipe;

import java.util.List;

public class RecipeWithIngredients{
    @Embedded public Recipe recipe;
    @Relation(
            parentColumn = "RecipeName",
            entityColumn = "IngredientName",
            associateBy = @Junction(RecipeIngredientCrossRef.class)
    )
    public List<Ingredient> ingredients;
}
