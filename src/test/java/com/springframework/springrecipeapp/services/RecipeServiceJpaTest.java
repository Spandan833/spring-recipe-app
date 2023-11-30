package com.springframework.springrecipeapp.services;

import com.springframework.springrecipeapp.domain.Recipe;
import com.springframework.springrecipeapp.repsositories.RecipeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceJpaTest {

    RecipeServiceJpa recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        recipeService = new RecipeServiceJpa(recipeRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.findAll();

        assertEquals(recipes.size(), recipeData.size());
        verify(recipeRepository,times(1)).findAll();
    }
    @Test
    void findById() {
        Recipe recipe = Recipe.builder().id(1L).description("Kadhai Chicken").build();
        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        Recipe returnedRecipe = recipeService.findById(1L);

        Assertions.assertNotNull(returnedRecipe);
        Assertions.assertEquals(recipe.getId(),returnedRecipe.getId());
        Mockito.verify(recipeRepository,times(1)).findById(eq(1L));
        Mockito.verify(recipeRepository, never()).findAll();
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void dummy(){

    }
}