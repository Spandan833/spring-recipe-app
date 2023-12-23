package com.springframework.springrecipeapp.coverters;

import com.springframework.springrecipeapp.commands.RecipeCommand;
import com.springframework.springrecipeapp.domain.Recipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeCommandToRecipeTest {
    RecipeCommandToRecipe converter;
    final Long ID = 3L;
    final String DESCRIPTION = "Paneer Tikka";
    final Integer COOK_TIME = 30;
    final Integer SERVINGS = 2;
    final String URL = "www.cookme.com";
    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(new NotesCommandToNotes(),
                                    new CategoryCommandToCategory(),
                                    new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));
    }

    @Test
    void RecipeCommandNull_Convert_RecipeIsNull(){
        RecipeCommand recipeCommand = null;

        Recipe recipe = converter.convert(recipeCommand);

        Assertions.assertNull(recipe);
    }


    @Test
    void RecipeCommand_Convert_RecipeObjectPropertiesMatch() {
        RecipeCommand recipeCommand = RecipeCommand.builder().id(ID)
                .description(DESCRIPTION).cookTime(COOK_TIME)
                .servings(SERVINGS).url(URL).build();

        Recipe recipe = converter.convert(recipeCommand);

        Assertions.assertNotNull(recipe);
        Assertions.assertEquals(ID, recipe.getId());
        Assertions.assertEquals(DESCRIPTION, recipe.getDescription());
        Assertions.assertEquals(COOK_TIME,recipe.getCookTime());
        Assertions.assertEquals(SERVINGS,recipe.getServings());
        Assertions.assertEquals(URL,recipe.getUrl());
    }
}