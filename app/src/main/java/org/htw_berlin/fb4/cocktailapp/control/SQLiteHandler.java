package org.htw_berlin.fb4.cocktailapp.control;

import java.util.ArrayList;


public class SQLiteHandler implements dbHandler {


    @Override
    public ArrayList<String> getRecipe(String recipe) {
        //todo decice on how to do query(cursor?)
        //helper.getDb().rawQuery(helper.getRecipeQuery(), new String[] {recipe});

        return new ArrayList<String>();
    }


    @Override
    public boolean addRecipe(String[] text) {
        return false;
    }

    @Override
    public void removeRecipe(String name) {
    }

    @Override
    public boolean updateRecipe(String[] text, String name) {
        return false;
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
