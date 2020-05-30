package org.htw_berlin.fb4.cocktailapp.dao.ingredient;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Dao;

import org.htw_berlin.fb4.cocktailapp.model.DbContentProvider;

@Dao
public class IngredientDao extends DbContentProvider implements IIngredientDao {
    private Cursor cursor;
    private ContentValues initialValues;

    public IngredientDao(SQLiteDatabase db) {
        super(db);
    }

    @Override
    protected <T> T cursorToEntity(Cursor cursor) {
        return null;
    }

    @Override
    public String[] getIngredient(String name) {
        return new String[0];
    }

    @Override
    public boolean addIngredient(String[] text) {
        return false;
    }

    @Override
    public boolean removeIngredient(String name) {
        return false;
    }
}
