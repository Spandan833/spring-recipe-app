package com.springframework.springrecipeapp.contollers;

import com.springframework.springrecipeapp.commands.IngredientCommand;
import com.springframework.springrecipeapp.domain.Recipe;
import com.springframework.springrecipeapp.services.IngredientService;
import com.springframework.springrecipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IngredientControllerTest {
    @Mock
    IngredientService ingredientService;
    @Mock
    RecipeService recipeService;

    IngredientController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        controller = new IngredientController(recipeService,ingredientService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getIngredients() throws Exception {
        Recipe recipe = Recipe.builder().id(1L).description("").build();
        Mockito.when(recipeService.findById(anyLong())).thenReturn(recipe);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredients"))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"));
    }

    @Test
    void getIngredient() throws Exception{
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(3L);
        Mockito.when(ingredientService.findByRecipeIdAndIngredientId(anyLong(),anyLong())).thenReturn(ingredientCommand);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredients/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"));
    }
}