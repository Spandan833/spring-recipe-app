package com.springframework.springrecipeapp.services;

import com.springframework.springrecipeapp.commands.IngredientCommand;
import com.springframework.springrecipeapp.coverters.IngredientToIngredientCommand;
import com.springframework.springrecipeapp.coverters.UnitOfMeasureToUnitOfMeasureCommand;
import com.springframework.springrecipeapp.domain.Ingredient;
import com.springframework.springrecipeapp.domain.Recipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class IngredientServiceImplTest {

    IngredientService ingredientService;
    @Mock
    RecipeService recipeService;

    IngredientToIngredientCommand converter;

    private final Long ID1 = 1L;
    private final Long ID2 = 2L;
    private final Long ID3 = 3L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        ingredientService = new IngredientServiceImpl(recipeService,converter);
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        Recipe recipe = new Recipe();
        recipe.setId(ID1);
        recipe.addIngredient(Ingredient.builder().id(1L).build());
        recipe.addIngredient(Ingredient.builder().id(2L).build());
        recipe.addIngredient(Ingredient.builder().id(3L).build());

        Mockito.when(recipeService.findById(ID1)).thenReturn(recipe);
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(ID1,2L);

        Assertions.assertEquals(ingredientCommand.getId(),2L);
    }
}