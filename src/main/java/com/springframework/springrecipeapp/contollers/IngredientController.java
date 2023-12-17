package com.springframework.springrecipeapp.contollers;

import com.springframework.springrecipeapp.services.IngredientService;
import com.springframework.springrecipeapp.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.lang.Long.parseLong;

@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredients")
    public String getIngredients(@PathVariable String recipeId, Model model){
        model.addAttribute("recipe", recipeService.findById(parseLong(recipeId)));
        return "recipe/ingredient/list";
    }

    @RequestMapping("recipe/{recipeId}/ingredients/{ingredientId}/show")
    public String getIngredient(@PathVariable String recipeId,@PathVariable String ingredientId, Model model){
        Long recipeIdLong = parseLong(recipeId);
        Long ingredientIdLong = parseLong(ingredientId);
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeIdLong,ingredientIdLong));
        return "recipe/ingredient/show";
    }

}
