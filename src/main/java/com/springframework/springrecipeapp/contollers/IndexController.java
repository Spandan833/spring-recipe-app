package com.springframework.springrecipeapp.contollers;

import com.springframework.springrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService){

        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String getHome(Model model){
        model.addAttribute("recipes",recipeService.findAll());
        return "index.html";
    }
}
