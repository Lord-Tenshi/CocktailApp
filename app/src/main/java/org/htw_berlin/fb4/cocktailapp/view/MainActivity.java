package org.htw_berlin.fb4.cocktailapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.htw_berlin.fb4.cocktailapp.R;
import org.htw_berlin.fb4.cocktailapp.model.recipe.Recipe;
import org.htw_berlin.fb4.cocktailapp.model.recipe.RecipeViewModel;

import static org.htw_berlin.fb4.cocktailapp.view.AddNewRecipeActivity.EXTRA_REPLY;


public class MainActivity extends AppCompatActivity {

    /**
     * Activity that shows a recyclerview with recipes.
     */

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private RecipeViewModel mRecipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final RecipeListAdapter adapter = new RecipeListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mRecipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);

        // Add an observer on the LiveData returned by getRecipes
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        // Update the cached copy of the recipes in the adapter.
        mRecipeViewModel.getAllRecipes().observe(this, adapter::setRecipes);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddNewRecipeActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle dataSet = data.getExtras();
            Recipe recipe = (Recipe) dataSet.getParcelable(EXTRA_REPLY);
            mRecipeViewModel.insert(recipe);
            Log.d("RecipeActivity: ", "Received data from NewRecipeActivity!");
        } else {
            Log.d("RecipeActivity: ", "Didn't receive data from NewRecipeActivity!");
        }
    }
}
