package org.htw_berlin.fb4.cocktailapp.view.recipeView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.htw_berlin.fb4.cocktailapp.R;
import org.htw_berlin.fb4.cocktailapp.model.recipe.Recipe;
import org.htw_berlin.fb4.cocktailapp.model.recipe.RecipeViewModel;
import org.htw_berlin.fb4.cocktailapp.view.recyclerViewSelection.RecipeItemDetailsLookup;
import org.htw_berlin.fb4.cocktailapp.view.recyclerViewSelection.RecipeKeyProvider;
import org.htw_berlin.fb4.cocktailapp.view.recyclerViewSelection.RecipeSelectionObserver;

import static org.htw_berlin.fb4.cocktailapp.view.recipeView.AddNewRecipeActivity.EXTRA_REPLY;


public class RecipeActivity extends AppCompatActivity {

    /**
     * Activity that shows a recyclerview with recipes.
     */

    public static final int NEW_RECIPE_ACTIVITY_REQUEST_CODE = 1;
    private RecipeViewModel mRecipeViewModel;
    private SelectionTracker mSelectionTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        RecyclerView mRecyclerView = findViewById(R.id.recipeRecyclerview);
        final RecipeListAdapter adapter = new RecipeListAdapter(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        // Get a new or existing ViewModel from the ViewModelProvider.
        mRecipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);

        SelectionTracker.Builder<Recipe> builder = new SelectionTracker.Builder<Recipe>(
                "String-items-selection",
                mRecyclerView,
                new RecipeKeyProvider<>(1, mRecipeViewModel.getRecipes()),
                new RecipeItemDetailsLookup(mRecyclerView),
                StorageStrategy.createParcelableStorage(Recipe.class));

                builder.withSelectionPredicate(SelectionPredicates.<Recipe>createSelectAnything());
                mSelectionTracker = builder.build();

        adapter.setSelectionTracker(mSelectionTracker);

        mSelectionTracker.addObserver(new RecipeSelectionObserver(mSelectionTracker));

        if (savedInstanceState != null){
            mSelectionTracker.onRestoreInstanceState(savedInstanceState);
        }

        // Add an observer on the LiveData returned by getRecipes
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        // Update the cached copy of the recipes in the adapter.
        mRecipeViewModel.getLiveRecipes().observe(this, adapter::setRecipes);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(RecipeActivity.this, AddNewRecipeActivity.class);
            startActivityForResult(intent, NEW_RECIPE_ACTIVITY_REQUEST_CODE);
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_RECIPE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle dataSet = data.getExtras();
            Recipe recipe = (Recipe) dataSet.getParcelable(EXTRA_REPLY);
            mRecipeViewModel.insert(recipe);
            Log.d("RecipeActivity: ", "Received data from NewRecipeActivity!");
        } else {
            Log.d("RecipeActivity: ", "Didn't receive data from NewRecipeActivity!");
        }
    }
}
