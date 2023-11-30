package com.springframework.springrecipeapp.services;

import com.springframework.springrecipeapp.domain.Recipe;
import com.springframework.springrecipeapp.repsositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceJpa implements RecipeService{

    private final RecipeRepository recipeRespository;

    public RecipeServiceJpa(RecipeRepository recipeRespository) {
        this.recipeRespository = recipeRespository;
    }

    @Override
    public Set<Recipe> findAll() {
        log.debug("I m in the service");
        Set<Recipe> recipes = new HashSet<>();
        recipeRespository.findAll().forEach(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
       Optional<Recipe> recipeOptional = recipeRespository.findById(id);
       if(!recipeOptional.isPresent()){
           throw new RuntimeException("Recipe not found");
       }
       return recipeOptional.get();
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
