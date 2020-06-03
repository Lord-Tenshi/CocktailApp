package org.htw_berlin.fb4.cocktailapp.model.recipe;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * @ Entity - You must annotate the class as an entity and supply a table name if not class name.
 * @ PrimaryKey - You must identify the primary key.
 * @ ColumnInfo - You must supply the column name if it is different from the variable name.
 */

@Entity(tableName = "recipe")
public class Recipe implements Serializable, Parcelable {


    @NonNull
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

    private Recipe(Parcel in) {
        this.name = in.readString();
        this.tools = in.readString();
        this.instruction = in.readString();
        this.description = in.readString();
        this.picture = in.readString();
    }

    public String getName() {
        return name;
    }

    public String getTools() {
        return tools;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }

        public Recipe createFormelParcel(Parcel in) {
            return new Recipe(in);
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(tools);
        dest.writeString(instruction);
        dest.writeString(description);
        dest.writeString(picture);
    }

    @Override
    public String toString(){
        return "Cockstail: " + this.name + "\nTools" + this.tools + "\nInstructions: " + this.instruction +"\nDescriptions: " + this.description;
    }
}

