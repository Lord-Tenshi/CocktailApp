package org.htw_berlin.fb4.cocktailapp.model.dao.ingredient;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredient")
public class Ingredient {

    @PrimaryKey
    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "buyable_from")
    public String buyableFrom;

    @ColumnInfo(name = "picture")
    public String picture;
}
