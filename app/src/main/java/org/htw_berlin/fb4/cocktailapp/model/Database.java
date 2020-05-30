package org.htw_berlin.fb4.cocktailapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {


    //Database names for creation
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Recipe.db";

    DbSchema schema = new DbSchema();
    //get x recipe query
    //private String recipeQuery = "SELECT * FROM " + TABLE_NAME_RECIPE + "WHERE " + RECIPE_NAME + "= ?";




    Database(Context ctx, SQLiteDatabase db){
        super(ctx,null, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(schema.CREATE_RECIPE_TABLE);
        db.execSQL(schema.CREATE_INGREDIENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
