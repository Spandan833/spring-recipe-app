package com.springframework.springrecipeapp.contollers;

import com.springframework.springrecipeapp.domain.Category;
import com.springframework.springrecipeapp.domain.UnitOfMeasure;
import com.springframework.springrecipeapp.repsositories.CategoryRepository;
import com.springframework.springrecipeapp.repsositories.UnitOfMeasureRepository;
import com.springframework.springrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService){
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String getHome(Model model){
        model.addAttribute("recipes",recipeService.findAll());
        return "index.html";
    }
}
