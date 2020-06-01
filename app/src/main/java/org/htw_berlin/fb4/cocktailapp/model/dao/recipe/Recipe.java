package org.htw_berlin.fb4.cocktailapp.model.dao.recipe;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipe")
public class Recipe {



    @PrimaryKey
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "tools")
    private String tools;

    @ColumnInfo(name = "instruction")
    private String instruction;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "picture")
    private String picture;

    public Recipe(String name, String tools, String instruction, String description, String picture) {
        this.name = name;
        this.tools = tools;
        this.instruction = instruction;
        this.description = description;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }


}
