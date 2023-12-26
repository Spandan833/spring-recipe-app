package com.springframework.springrecipeapp.contollers;

import com.springframework.springrecipeapp.commands.RecipeCommand;
import com.springframework.springrecipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static java.lang.Long.parseLong;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/show")
    public String getRecipeById(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findById(parseLong(id)));
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(parseLong(id)));
        return "recipe/recipeform";
    }


    @PostMapping("/recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand saveCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:recipe/"+saveCommand.getId()+"/show";
    }

    @GetMapping("recipe/{id}/delete")
    public String delete(@PathVariable String id){
        recipeService.deleteById(parseLong(id));
        return "redirect:/";
    }
}
