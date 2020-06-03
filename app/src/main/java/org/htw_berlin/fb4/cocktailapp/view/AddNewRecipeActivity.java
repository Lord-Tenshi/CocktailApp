package org.htw_berlin.fb4.cocktailapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.htw_berlin.fb4.cocktailapp.R;
import org.htw_berlin.fb4.cocktailapp.model.ingredient.Ingredient;
import org.htw_berlin.fb4.cocktailapp.model.ingredient.IngredientViewModel;
import org.htw_berlin.fb4.cocktailapp.model.recipe.Recipe;

import java.util.List;

/**
 * Activity for entering a recipe.
 */

public class AddNewRecipeActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.recipelistsql.REPLY";
    private IngredientViewModel mIngredientViewModel;

    private EditText mEditCocktailName;
    private EditText mEditTools;
    private EditText mEditInstructions;
    private EditText mEditDescription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newrecipe);

        RecyclerView recyclerVIew = findViewById(R.id.ingredientRecyclerView);
        final IngredientListAdapter adapter = new IngredientListAdapter(this);
        recyclerVIew.setAdapter(adapter);
        recyclerVIew.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mIngredientViewModel = new ViewModelProvider(this).get(IngredientViewModel.class);

        // Add an observer on the LiveData returned by getRecipes
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mIngredientViewModel.getAllIngredients().observe(this, new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(@Nullable final List<Ingredient> ingredient) {
                // Update the cached copy of the words in the adapter.
                adapter.setIngredients(ingredient);
            }
        });

        mEditCocktailName = findViewById(R.id.editTextCocktailName);
        mEditTools = findViewById(R.id.editTextTools);
        mEditInstructions = findViewById(R.id.editTextInstructions);
        mEditDescription = findViewById(R.id.editTextDescription);

        final Button mAddButton = findViewById(R.id.addButton);
        mAddButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent replyIntent = new Intent();
                Recipe mRecipe;

                if (TextUtils.isEmpty(mEditCocktailName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String name = mEditCocktailName.getText().toString();
                    String tools = mEditTools.getText().toString();
                    String instructions = mEditInstructions.getText().toString();
                    String description = mEditDescription.getText().toString();

                    mRecipe = new Recipe(name, tools, instructions, description, "");
                    replyIntent.putExtra(EXTRA_REPLY, (Parcelable) mRecipe);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });


    }

}

