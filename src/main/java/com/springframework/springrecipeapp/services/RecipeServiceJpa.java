package com.springframework.springrecipeapp.services;

import com.springframework.springrecipeapp.domain.Recipe;
import com.springframework.springrecipeapp.repsositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RecipeServiceJpa implements RecipeService{

    private final RecipeRepository recipeRespository;

    public RecipeServiceJpa(RecipeRepository recipeRespository) {
        this.recipeRespository = recipeRespository;
    }

    @Override
    public List<Recipe> findAll() {
        log.debug("I m in the service");
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
