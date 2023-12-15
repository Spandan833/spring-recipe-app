package com.springframework.springrecipeapp.services;

import com.springframework.springrecipeapp.commands.RecipeCommand;
import com.springframework.springrecipeapp.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
public interface RecipeService {
    Set<Recipe> findAll();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    void delete(Recipe recipe);

    void deleteById(Long Id);

    RecipeCommand findCommandById(long id);
}
