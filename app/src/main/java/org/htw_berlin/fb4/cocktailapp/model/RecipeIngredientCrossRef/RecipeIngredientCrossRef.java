package org.htw_berlin.fb4.cocktailapp.model.RecipeIngredientCrossRef;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"RecipeName", "IngredientName"})
public class RecipeIngredientCrossRef {

    @ColumnInfo
    public String RecipeName;

    @ColumnInfo
    public String IngredientName;
}
