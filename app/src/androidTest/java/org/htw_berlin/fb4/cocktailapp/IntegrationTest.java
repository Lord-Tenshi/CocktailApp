package org.htw_berlin.fb4.cocktailapp;

import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.htw_berlin.fb4.cocktailapp.view.recipeView.AddNewRecipeActivity;
import org.junit.Rule;

import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.htw_berlin.fb4.cocktailapp.UITestUtil.atPosition;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class IntegrationTest {
    @Rule
    public ActivityTestRule<AddNewRecipeActivity> activityRule
            = new ActivityTestRule<>(AddNewRecipeActivity.class);

    public void addRecipe() {
        String name = "White Russian";
        String tools = "none";
        String instruction = "do this and that";
        String description = "is tasty";
        String picture = "none";

        onView(withId(R.id.editTextCocktailName))
                .perform(typeText(name), closeSoftKeyboard());

        onView(withId(R.id.editTextTools))
                .perform(typeText(tools), closeSoftKeyboard());

        onView(withId(R.id.editTextInstructions))
                .perform(typeText(instruction), closeSoftKeyboard());

        onView(withId(R.id.editTextDescription))
                .perform(typeText(description), closeSoftKeyboard());

        onView(withId(R.id.cocktailPicture))
                .perform(typeText(picture), closeSoftKeyboard());

        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.recipeRecyclerview))
                .check(matches(atPosition(1, withText(name))));
    }

}


