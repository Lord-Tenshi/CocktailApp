package org.htw_berlin.fb4.cocktailapp.view;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MyAdapter extends FragmentStatePagerAdapter {

    private Context myContext;
    int totalTabs;
    private String[] tabTitles = new String[]{"Cocktails", "Suche"};

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                RecipeFragment recipeFragment = new RecipeFragment();
                return recipeFragment;

            case 1:
                SearchFragment searchFragment = new SearchFragment();
                return searchFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return totalTabs;
    }

}