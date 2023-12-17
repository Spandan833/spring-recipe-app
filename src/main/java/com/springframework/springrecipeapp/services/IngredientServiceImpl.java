package com.springframework.springrecipeapp.services;

import com.springframework.springrecipeapp.commands.IngredientCommand;
import com.springframework.springrecipeapp.coverters.IngredientToIngredientCommand;
import com.springframework.springrecipeapp.coverters.RecipeToRecipeCommand;
import com.springframework.springrecipeapp.domain.Ingredient;
import com.springframework.springrecipeapp.domain.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final RecipeService recipeService;
    private final IngredientToIngredientCommand converter;

    @Autowired
    public IngredientServiceImpl(RecipeService recipeService, IngredientToIngredientCommand converter) {
        this.recipeService = recipeService;
        this.converter = converter;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Recipe ingredientRecipe = recipeService.findById(recipeId);
        if(ingredientRecipe == null){
            log.error("Recipe not found");
        }
        Optional<Ingredient> requiredIngredient = ingredientRecipe.getIngredients()
                .stream().filter(ingredient -> ingredient.getId().equals(ingredientId))
                .findAny();

        if(!requiredIngredient.isPresent()){
            log.error("Ingredient was not found");
        }
        return this.converter.convert(requiredIngredient.get());
    }
}
