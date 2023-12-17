package com.springframework.springrecipeapp.services;

import com.springframework.springrecipeapp.commands.IngredientCommand;
import org.springframework.stereotype.Service;

@Service
public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
