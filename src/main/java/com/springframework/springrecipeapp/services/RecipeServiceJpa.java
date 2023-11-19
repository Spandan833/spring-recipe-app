package com.springframework.springrecipeapp.services;

import com.springframework.springrecipeapp.domain.Recipe;
import com.springframework.springrecipeapp.repsositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceJpa implements RecipeService{

    private final RecipeRepository recipeRespository;

    public RecipeServiceJpa(RecipeRepository recipeRespository) {
        this.recipeRespository = recipeRespository;
    }

    @Override
    public List<Recipe> findAll() {
        return (List<Recipe>) recipeRespository.findAll();
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRespository.findById(id).get();
    }

    @Override
    public void delete(Recipe recipe) {
        recipeRespository.delete(recipe);
    }

    @Override
    public void deleteById(Long id) {
        recipeRespository.deleteById(id);
    }
}
