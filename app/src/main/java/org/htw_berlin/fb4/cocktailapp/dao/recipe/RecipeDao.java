package org.htw_berlin.fb4.cocktailapp.dao.recipe;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.htw_berlin.fb4.cocktailapp.model.DbContentProvider;

import java.util.ArrayList;

public class RecipeDao extends DbContentProvider implements IRecipeDao {

    private Cursor cursor;
    private ContentValues initialValues;

    public RecipeDao(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public ArrayList<Recipe> getRecipes(String recipeName) {
        return null;
    }

    @Override
    public boolean addRecipe(String[] text) {
        return false;
    }

    @Override
    public void removeRecipe(String name) { }

    @Override
    public boolean updateRecipe(String[] text) {
        return false;
    }

    @Override
    protected <T> T cursorToEntity(Cursor cursor) {
        return null;
    }
}
